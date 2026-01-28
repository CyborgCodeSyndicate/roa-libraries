package io.cyborgcode.roa.db.validator;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.validator.core.AssertionTarget;

/**
 * Defines assertion targets for database query validation.
 *
 * <p>This enum categorizes different aspects of a database query result that can be validated,
 * ensuring structured assertions for database testing.
 */
@Pandora(
      description = "Enumeration defining the possible assertion targets for database query validation, "
            + "such as validating query results, row counts, or column structures.",
      tags = {"db", "assertion"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "db-assertion-target")
      }
)
public enum DbAssertionTarget implements AssertionTarget<DbAssertionTarget> {

   /**
    * Validates the query result content.
    */
   @Pandora(
         description = "Assertion target representing the full query result content."
   )
   QUERY_RESULT,

   /**
    * Validates the number of rows returned by the query.
    */
   @Pandora(
         description = "Assertion target representing the number of rows returned by the query."
   )
   NUMBER_ROWS,

   /**
    * Validates the column structure of the result set.
    */
   @Pandora(
         description = "Assertion target representing the column structure of the query result."
   )
   COLUMNS;

   /**
    * Retrieves the specific assertion target.
    *
    * @return The enum representing the assertion target.
    */
   @Override
   public DbAssertionTarget target() {
      return this;
   }
}
