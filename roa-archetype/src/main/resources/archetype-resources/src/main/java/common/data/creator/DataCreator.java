package ${package}.common.data.creator;

import io.cyborgcode.roa.framework.parameters.DataForge;
import io.cyborgcode.roa.framework.parameters.Late;

/**
 * Registry of reusable test data factories.
 *
 * <p>Each enum constant represents a named data model that can be referenced from
 * ROA annotations such as {@code @Craft} or {@code @Journey}. The corresponding
 * {@link Late} supplier is implemented in {@link DataCreatorFunctions} and should
 * return domain objects or primitive data structures relevant to your application.</p>
 *
 * <p>This file provides generic examples only. Replace or extend the entries
 * based on your application's needs.</p>
 */
public enum DataCreator implements DataForge<DataCreator> {

   EXAMPLE_MODEL(DataCreatorFunctions::createExampleModel),
   EXAMPLE_TABLE_MODEL(DataCreatorFunctions::createExampleTableModel);


   public static final class Data {

      public static final String EXAMPLE_MODEL = "EXAMPLE_MODEL";
      public static final String EXAMPLE_TABLE_MODEL = "EXAMPLE_TABLE_MODEL";

      private Data() {}
   }

   private final Late<Object> createDataFunction;

   DataCreator(final Late<Object> createDataFunction) {
      this.createDataFunction = createDataFunction;
   }

   @Override
   public Late<Object> dataCreator() {
      return createDataFunction;
   }

   @Override
   public DataCreator enumImpl() {
      return this;
   }
}
