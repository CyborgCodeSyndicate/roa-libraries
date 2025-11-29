package ${package}.db.extractors;

/**
 * Example JsonPath extractors for database query results.
 *
 * <p>This enum demonstrates how to define reusable JsonPath expressions
 * for extracting values from DB query responses returned as JSON.</p>
 *
 * <p>Replace these examples with JsonPaths matching your application's schema.</p>
 */
public enum DbResponsesJsonPaths {

   /**
    * Example: extract the "value" field from the first row.
    */
   PRODUCT_BY_ID("$[?(@.id == %d)].product"),

   /**
    * Example: filter rows by "id".
    */
   LOCATION_BY_ID("$[?(@.id == %d)].location");

   private final String jsonPath;

   DbResponsesJsonPaths(String jsonPath) {
      this.jsonPath = jsonPath;
   }

   /**
    * Returns the formatted JsonPath expression.
    *
    * @param args optional formatting arguments
    * @return the formatted JsonPath
    */
   public String getJsonPath(Object... args) {
      if (args != null && args.length > 0) {
         return String.format(jsonPath, args);
      }
      return jsonPath;
   }
}
