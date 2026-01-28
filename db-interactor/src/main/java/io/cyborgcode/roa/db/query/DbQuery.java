package io.cyborgcode.roa.db.query;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.db.config.DatabaseConfiguration;
import io.cyborgcode.roa.db.config.DbConfig;
import io.cyborgcode.roa.db.config.DbConfigHolder;
import io.cyborgcode.roa.db.pandora.AvailableOptionsRules;

import static io.cyborgcode.roa.db.config.DbConfigHolder.getDbConfig;

/**
 * Represents a database query and provides configuration details.
 *
 * <p>This interface defines a structured way to execute database queries,
 * retrieve their configurations, and apply parameterized values dynamically.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Semantic representation of a database query. "
            + "Implementations define executable SQL queries that can be selected, "
            + "parameterized, and executed within database test flows.",
      tags = {"db", "query"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableDbQueries.class,
      meta = {
         @PandoraOptions.Meta(key = "type", value = "db-query")
      }
)
public interface DbQuery<T extends Enum<T>> {

   /**
    * Retrieves the raw query string.
    *
    * @return The SQL query as a {@code String}.
    */
   @Pandora(
         description = "Returns the raw SQL query string associated with this database query."
   )
   String query();

   /**
    * Provides the database configuration associated with this query.
    *
    * <p>The configuration is fetched from {@link DbConfigHolder}, ensuring
    * consistency across database queries.
    *
    * @return The {@link DatabaseConfiguration} for the query.
    */
   default DatabaseConfiguration config() {
      DbConfig dbConfig = getDbConfig();
      return DatabaseConfiguration.builder()
            .dbType(dbConfig.type())
            .host(dbConfig.host())
            .port(dbConfig.port())
            .database(dbConfig.name())
            .dbUser(dbConfig.username())
            .dbPassword(dbConfig.password())
            .fullConnectionString(dbConfig.fullConnectionString())
            .build();
   }

   /**
    * Retrieves the enum representation of the query implementation.
    *
    * @return The query's enum representation.
    */
   @Pandora(
         description = "Returns the enum constant identifying this database query implementation."
   )
   T enumImpl();

   /**
    * Applies a parameter to the query dynamically.
    *
    * <p>This method replaces placeholders within the query with actual values,
    * allowing parameterized SQL execution.
    *
    * @param name  The name of the parameter to replace.
    * @param value The value to assign to the parameter.
    * @return A new {@code DbQuery} instance with the applied parameter.
    */
   @Pandora(
         description = "Creates a parameterized variant of this database query by "
               + "replacing a named placeholder with the provided value."
   )
   default DbQuery<T> withParam(String name, Object value) {
      return new ParametrizedQuery<>(this).withParam(name, value);
   }
}
