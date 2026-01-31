package io.cyborgcode.roa.framework.quest;


import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.annotation.Ring;
import io.cyborgcode.roa.framework.assertion.CustomSoftAssertion;
import io.cyborgcode.roa.framework.chain.FluentService;
import io.cyborgcode.roa.framework.log.LogQuest;
import io.cyborgcode.roa.framework.storage.Storage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.cyborgcode.utilities.reflections.ReflectionUtil.getFieldValues;

/**
 * Manages the execution flow and data storage for test scenarios.
 *
 * <p>This class acts as the central controller for executing test operations,
 * managing service interactions, and storing data during a test run.
 * It allows transitioning between different testing contexts (rings) and
 * ensures test completion with proper validations.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Core RoA quest object used in tests. Holds rings (fluent services), "
            + "shared storage and soft assertions for a single test run.",
      tags = {"framework"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/general-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "quest")
      }
)
public class Quest {

   /**
    * Stores registered test services, mapping their types to instances.
    */
   private final Map<Class<? extends FluentService>, FluentService> rings = new HashMap<>();

   /**
    * Storage instance for temporarily holding test data within a test execution.
    */
   private final Storage storage;

   /**
    * Handles soft assertions for validation checks during the test execution.
    */
   private final CustomSoftAssertion softAssertions = new CustomSoftAssertion();

   /**
    * Initializes a new {@code Quest} instance with a fresh storage object.
    */
   public Quest() {
      this.storage = new Storage();
   }

   /**
    * Transitions the test execution context into the specified ring (test service).
    *
    * <p>This method retrieves a registered service by its class type and allows the test execution
    * to proceed within that context.
    *
    * @param ring The class type of the desired test service.
    * @param <T>  The type of the fluent service.
    * @return The corresponding fluent service instance.
    * @throws IllegalArgumentException If the specified ring is not registered.
    */
   @SuppressWarnings("unchecked")
   @Pandora(
         description = "Switch the active ring (fluent service) for the current quest."
               + " Typical usage: quest.use(RING_OF_API) or quest.use(RING_OF_CUSTOM).",
         tags = {"framework"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   public <T extends FluentService> T use(
         @Pandora(
               description = "Fluent service ring class to activate, "
                     + "e.g. RestServiceFluent.class or a custom @Ring service."
         )
         Class<T> ring) {
      Optional<Class<? extends FluentService>> match =
            rings.keySet().stream().filter(ring::isAssignableFrom).findFirst();
      if (match.isEmpty()) {
         throw new IllegalArgumentException("Ring not initialized: " + ring.getName());
      }
      Ring ringName = ring.getAnnotation(Ring.class);

      String message = ringName == null
            ? "The quest has used the ring: '" + ring.getName() + "'"
            : "The quest has used the ring: '" + ringName.value() + "'";

      LogQuest.info(message);
      return (T) rings.get(match.get());
   }

   /**
    * Retrieves a registered fluent service without modifying the execution context.
    *
    * @param ringType The class type of the service to retrieve.
    * @param <T>       The type of the fluent service.
    * @return The corresponding service instance.
    * @throws IllegalArgumentException If the specified ring is not registered.
    */
   @SuppressWarnings("unchecked")
   protected <T extends FluentService> T cast(Class<T> ringType) {
      Optional<Class<? extends FluentService>> match =
            rings.keySet().stream().filter(ringType::isAssignableFrom).findFirst();
      if (match.isEmpty()) {
         throw new IllegalArgumentException("Ring not initialized: " + ringType.getName());
      }
      return (T) rings.get(match.get());
   }

   /**
    * Marks the completion of the test execution.
    *
    * <p>This method logs the completion, clears the test execution state, and verifies all
    * soft assertions collected during the test execution.
    */
   @Pandora(
         description = "Finish the quest: log completion, clear the quest from "
               + "QuestHolder and assert all collected soft assertions. Call this at the end of the test flow.",
         tags = {"framework"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   public void complete() {
      LogQuest.info("The quest has reached his end");
      QuestHolder.clear();
      softAssertions.assertAll();
   }

   /**
    * Retrieves an artifact associated with a specific test service.
    *
    * <p>This method extracts an instance of the specified artifact type from the
    * requested test service.
    *
    * @param ringType    The class type of the test service.
    * @param artifactType The class type of the artifact to retrieve.
    * @param <T>          The type of the test service.
    * @param <K>          The type of the artifact.
    * @return The retrieved artifact instance.
    * @throws IllegalArgumentException If input parameters are null.
    * @throws IllegalStateException    If the requested ring is not available.
    */
   protected <T extends FluentService, K> K artifact(Class<T> ringType, Class<K> artifactType) {
      if (ringType == null || artifactType == null) {
         throw new IllegalArgumentException("Parameters ringType and artifactType must not be null.");
      }

      T ring = cast(ringType);
      if (ring == null) {
         throw new IllegalStateException(
               "Could not retrieve an instance of the specified ringType: " + ringType.getName());
      }

      List<K> fieldValues = getFieldValues(ring, artifactType);
      if (fieldValues.size() > 1) {
         LogQuest.warn(
               "There is more than one artifact from type: {} inside class: {}. The first one will be taken: {}",
               artifactType, ringType, fieldValues.get(0));
      }
      return fieldValues.get(0);
   }

   /**
    * Registers a new test service into the current execution context.
    *
    * @param ringType The class type of the test service.
    * @param ring     The instance of the test service.
    */
   protected void registerRing(Class<? extends FluentService> ringType, FluentService ring) {
      rings.put(ringType, ring);
   }

   /**
    * Removes a test service from the current execution context.
    *
    * @param ringType The class type of the test service to remove.
    */
   protected void removeRing(Class<? extends FluentService> ringType) {
      rings.remove(ringType);
   }

   /**
    * Provides access to the storage instance for managing temporary test data.
    *
    * @return The storage instance.
    */
   protected Storage getStorage() {
      return storage;
   }

   /**
    * Provides access to the soft assertion handler for managing test validations.
    *
    * @return The soft assertion handler.
    */
   protected CustomSoftAssertion getSoftAssertions() {
      return softAssertions;
   }

}

