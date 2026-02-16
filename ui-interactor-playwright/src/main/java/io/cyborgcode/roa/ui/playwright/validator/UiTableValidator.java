package io.cyborgcode.roa.ui.playwright.validator;

import io.cyborgcode.roa.validator.core.Assertion;
import io.cyborgcode.roa.validator.core.AssertionResult;
import java.util.List;

/**
 * Interface for validating tables in UI automation.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface UiTableValidator {

   /**
    * Validates a given table object against a set of assertions.
    *
    * @param object     The table object to be validated.
    * @param assertions The assertions to apply.
    * @param <T>        The type of the expected assertion results.
    * @return A list of {@link AssertionResult} containing the validation outcomes.
    */
   <T> List<AssertionResult<T>> validateTable(Object object, Assertion... assertions);
}
