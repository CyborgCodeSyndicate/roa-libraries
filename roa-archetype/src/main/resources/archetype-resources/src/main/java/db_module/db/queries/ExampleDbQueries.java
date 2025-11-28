package ${package}.db_module.db.queries;

import io.cyborgcode.roa.db.query.DbQuery;

/**
 * Example database query definitions.
 *
 * <p>Replace these example entries with real SQL queries relevant to your application.
 * This file demonstrates how to define {@link DbQuery} enums for use in
 * DatabaseService operations.</p>
 */
public enum ExampleDbQueries implements DbQuery<ExampleDbQueries> {

   /**
    * Simple example query to demonstrate usage.
    */
   SIMPLE_QUERY("SELECT 1 AS example");

   private final String query;

   ExampleDbQueries(String query) {
      this.query = query;
   }

   @Override
   public String query() {
      return query;
   }

   @Override
   public ExampleDbQueries enumImpl() {
      return this;
   }
}
