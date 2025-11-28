package ${package}.common.data.cleaner;

import io.cyborgcode.roa.framework.parameters.DataRipper;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import java.util.function.Consumer;

/**
 * Defines reusable cleanup (data ripping) operations for your test suite.
 *
 * <p>This enum integrates with ROA's {@code @Ripper} mechanism via {@link DataRipper}:
 * each constant maps to a function executed after a test completes,
 * allowing you to centralize teardown logic such as deleting test data
 * or resetting application state.</p>
 *
 * <p>The nested {@link Data} class exposes string keys that can be referenced
 * directly from test annotations, ensuring a clear contract between tests
 * and the cleanup logic.</p>
 *
 * <p>Feel free to add more cleanup operations based on your application's needs.</p>
 */
public enum DataCleaner implements DataRipper<DataCleaner> {

   /**
    * Example cleanup operation.
    *
    * <p>Update the implementation inside {@link DataCleanerFunctions#exampleCleanup(SuperQuest)}
    * to match your application's teardown logic.</p>
    */
   EXAMPLE_CLEANUP(DataCleanerFunctions::exampleCleanup);

   /**
    * String identifiers that can be referenced from @Ripper annotations.
    */
   public static final class Data {

      private Data() {}

      public static final String EXAMPLE_CLEANUP = "EXAMPLE_CLEANUP";
   }

   private final Consumer<SuperQuest> cleanUpFunction;

   DataCleaner(final Consumer<SuperQuest> cleanUpFunction) {
      this.cleanUpFunction = cleanUpFunction;
   }

   @Override
   public Consumer<SuperQuest> eliminate() {
      return cleanUpFunction;
   }

   @Override
   public DataCleaner enumImpl() {
      return this;
   }
}
