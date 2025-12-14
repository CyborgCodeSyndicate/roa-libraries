package io.cyborgcode.roa.validator.core;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.validator.pandora.AvailableOptionsRules;

/**
 * Defines a contract for specifying the target of an assertion.
 *
 * <p>This interface is implemented by enums that categorize assertions
 * for different validation contexts such as API responses, database queries,
 * and UI elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Marker interface for assertion targets (e.g. STATUS, BODY, HEADER). "
            + "Implemented by enums that define where an assertion is applied.",
      tags = {"assertion"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableAssertionTargets.class,
      meta = {
         @PandoraOptions.Meta(key = "type", value = "assertion-target")
      }
)
public interface AssertionTarget<T extends Enum<T>> {

   /**
    * Retrieves the specific assertion target.
    *
    * @return The enum representing the assertion target.
    */
   T target();

}
