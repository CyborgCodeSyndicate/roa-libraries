package io.cyborgcode.roa.framework.quest;

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
    * @param ringType    the class type of the test service.
    * @param artifactType the class type of the artifact to retrieve.
    * @param <T>          the type of the test service.
    * @param <K>          the type of the artifact.
    * @return the artifact instance retrieved from the underlying quest.
    */
   @Override
   public <T extends FluentService, K> K artifact(Class<T> ringType, Class<K> artifactType) {
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
   public void registerRing(Class<? extends FluentService> ringType, FluentService ring) {
      original.registerRing(ringType, ring);
   }


   /**
    * Retrieves a registered test service from the underlying quest without modifying the context.
    *
    * <p>This method delegates the retrieval to the underlying {@code Quest} instance,
    * returning the requested test service instance.
    *
    * @param ringType the class type of the test service to retrieve.
    * @param <T>       the type of the test service.
    * @return the corresponding test service instance.
    */
   @Override
   public <T extends FluentService> T cast(Class<T> ringType) {
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
   public void removeRing(Class<? extends FluentService> ringType) {
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
   public CustomSoftAssertion getSoftAssertions() {
      return original.getSoftAssertions();
   }


   public Quest getOriginal() {
      return original;
   }

}
