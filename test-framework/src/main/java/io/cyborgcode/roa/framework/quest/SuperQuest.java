package io.cyborgcode.roa.framework.quest;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.assertion.CustomSoftAssertion;
import io.cyborgcode.roa.framework.chain.FluentService;
import io.cyborgcode.roa.framework.storage.Storage;
import lombok.experimental.Delegate;

/**
 * Provides an enhanced test execution context by delegating core operations to an underlying {@code Quest} instance.
 *
 * <p>The {@code SuperQuest} class wraps a {@code Quest} instance, delegating methods such as registering or removing
 * test services, retrieving artifacts, accessing storage, and managing soft assertions. This ensures that all
 * functionality available in {@code Quest} is seamlessly accessible, while also enabling extended behaviors in the
 * test execution flow.
 *
 * <p>Methods in this class mirror those in {@code Quest} and provide consistent documentation for interacting with
 * the test execution context.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Wrapper quest that decorates an underlying Quest (original) "
            + "and delegates core operations to it. Used as the active execution context during a run,"
            + " while still allowing access to the original Quest instance.",
      tags = {"framework", "quest"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/general-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "super-quest"),
         @PandoraOptions.Meta(key = "role", value = "wrapper-delegate")
      }
)
public final class SuperQuest extends Quest {

   /**
    * The underlying {@code Quest} instance that holds the original test execution context.
    */
   @Delegate
   private final Quest original;


   /**
    * Constructs a new {@code SuperQuest} by wrapping the provided {@code Quest}.
    *
    * @param quest the original {@code Quest} instance to be wrapped.
    */
   public SuperQuest(Quest quest) {
      this.original = quest;
   }


   /**
    * Retrieves an artifact from the underlying quest.
    *
    * <p>This method delegates the call to the underlying {@code Quest} instance,
    * extracting an instance of the specified artifact type from the corresponding test service.
    *
    * @param ringType     the class type of the test service.
    * @param artifactType the class type of the artifact to retrieve.
    * @param <T>          the type of the test service.
    * @param <K>          the type of the artifact.
    * @return the artifact instance retrieved from the underlying quest.
    */
   @Override
   @Pandora(
         description = "Retrieve an artifact instance exposed by a registered ring/service."
               + " Delegates to the wrapped Quest."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   public <T extends FluentService, K> K artifact(@Pandora(description = "Ring/service class from which "
                                                        + "the artifact should be extracted.")
                                                  Class<T> ringType,
                                                  @Pandora(description = "Expected artifact class to "
                                                        + "retrieve from the ring/service.")
                                                  Class<K> artifactType) {
      return original.artifact(ringType, artifactType);
   }


   /**
    * Registers a test service (ring) into the underlying quest.
    *
    * <p>This method delegates the registration of the test service to the underlying {@code Quest} instance,
    * ensuring that the test execution context is aware of the new service.
    *
    * @param ringType the class type of the test service.
    * @param ring     the instance of the test service.
    */
   @Override
   @Pandora(
         description = "Register a ring/service into the active "
               + "quest context so it can be used in the fluent test flow."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   public void registerRing(@Pandora(description = "Ring/service class used as the registration key.")
                            Class<? extends FluentService> ringType,
                            @Pandora(description = "Concrete ring/service instance to register in the quest context.")
                            FluentService ring) {
      original.registerRing(ringType, ring);
   }


   /**
    * Retrieves a registered test service from the underlying quest without modifying the context.
    *
    * <p>This method delegates the retrieval to the underlying {@code Quest} instance,
    * returning the requested test service instance.
    *
    * @param ringType the class type of the test service to retrieve.
    * @param <T>      the type of the test service.
    * @return the corresponding test service instance.
    */
   @Override
   @Pandora(
         description = "Retrieve a registered ring/service instance by its class without changing the quest state."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   public <T extends FluentService> T cast(@Pandora(description = "Ring/service class to fetch from the quest context.")
                                           Class<T> ringType) {
      return super.cast(ringType);
   }


   /**
    * Removes a test service from the underlying quest.
    *
    * <p>This method delegates the removal operation to the underlying {@code Quest} instance,
    * ensuring that the specified test service is no longer part of the execution context.
    *
    * @param ringType the class type of the test service to remove.
    */
   @Override
   @Pandora(
         description = "Remove a previously registered ring/service from the quest context."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   public void removeRing(@Pandora(description = "Ring/service class identifying which service should be removed.")
                             Class<? extends FluentService> ringType) {
      original.removeRing(ringType);
   }


   /**
    * Provides access to the storage instance from the underlying quest.
    *
    * <p>The storage instance is used for managing temporary test data during execution.
    *
    * @return the {@link Storage} instance for the underlying quest.
    */
   @Override
   @Pandora(
         description = "Access the quest storage used for storing and retrieving data/artifacts during execution."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   public Storage getStorage() {
      return original.getStorage();
   }


   /**
    * Provides access to the soft assertion handler from the underlying quest.
    *
    * <p>The soft assertion handler collects and verifies soft assertions during test execution.
    *
    * @return the {@link CustomSoftAssertion} instance for the underlying quest.
    */
   @Override
   @Pandora(
         description = "Access the quest soft assertion aggregator used for soft validations across the flow."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   public CustomSoftAssertion getSoftAssertions() {
      return original.getSoftAssertions();
   }

   @Pandora(
         description = "Return the wrapped original Quest instance (the underlying execution context)."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   public Quest getOriginal() {
      return original;
   }

}
