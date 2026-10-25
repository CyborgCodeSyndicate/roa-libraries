package io.cyborgcode.roa.validator.core;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;

/**
 * Defines a contract for specifying the target of an assertion.
 *
 * <p>This interface is implemented by enums that categorize assertions
 * for different validation contexts such as API responses, database queries,
 * and UI elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Marker interface for assertion targets (e.g. STATUS, BODY, HEADER). "
            + "Implemented by enums that define where an assertion is applied.",
      tags = {"assertion"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "assertion-target")
      },
      implementationsOf = AssertionTarget.class
)
public interface AssertionTarget<T extends Enum<T>> {

   /**
    * Retrieves the specific assertion target.
    *
    * @return The enum representing the assertion target.
    */
   T target();

}
