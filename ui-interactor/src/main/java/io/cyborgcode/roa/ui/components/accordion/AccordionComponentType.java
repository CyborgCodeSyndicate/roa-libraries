package io.cyborgcode.roa.ui.components.accordion;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;

/**
 * Identifies a specific type of accordion component.
 *
 * <p>Enums implementing this interface represent distinct implementations
 * or frameworks for an accordion UI element. By implementing
 * {@link AccordionComponentType}, each enum value signals the
 * framework which accordion variation to use at runtime.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Component type marker for Accordion components; enums implement this to declare "
            + "available accordion types.",
      tags = {"ui", "ui-component-type", "accordion"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      },
      implementationsOf = AccordionComponentType.class
)
public interface AccordionComponentType extends ComponentType {
}
