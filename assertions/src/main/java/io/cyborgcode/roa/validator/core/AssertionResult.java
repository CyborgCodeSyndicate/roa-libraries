package io.cyborgcode.roa.validator.core;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * Represents the result of an assertion evaluation.
 *
 * <p>This class stores details about an assertion's outcome, including whether it passed,
 * the expected and actual values, and an optional description. It also supports soft assertions,
 * allowing validations to continue even when failures occur.
 *
 * <p>The expected and actual values in the assertion are represented by the generic type {@code T},
 * which allows this class to handle various data types in validation checks.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Getter
@AllArgsConstructor
@Pandora(
      description = "Immutable outcome of evaluating a single Assertion. "
            + "Contains pass/fail status, a human-readable description, expected vs actual values, "
            + "and whether the check is soft (collected) or hard (fails immediately).",
      tags = {"assertion"},
      creation = CreationKind.CONSTRUCTOR
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/api-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "assertion-result")
      }
)
public final class AssertionResult<T> {

   /**
    * Indicates whether the assertion passed ({@code true}) or failed ({@code false}).
    */
   private final boolean passed;

   /**
    * A brief description of the assertion being performed.
    */
   @NonNull
   private final String description;

   /**
    * The expected value in the assertion.
    */
   private final Object expectedValue;

   /**
    * The actual value retrieved during validation.
    */
   private final T actualValue;

   /**
    * Specifies if the assertion is a soft assertion.
    */
   private boolean soft;


   /**
    * Returns a formatted string representing the assertion outcome.
    *
    * @return A descriptive summary of the assertion result.
    */
   @Pandora(
         description = "Formats this AssertionResult into a single human-readable line used in logs and Allure steps."
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/api-usage.json"
   )
   @Override
   public String toString() {
      return passed
            ?
            String.format("✔ Validation passed: %s (Expected: %s, Actual: %s)", description, expectedValue,
                  actualValue)
            : String.format("✘ Validation failed: %s (Expected: %s, Actual: %s)", description, expectedValue,
            actualValue);
   }

}
