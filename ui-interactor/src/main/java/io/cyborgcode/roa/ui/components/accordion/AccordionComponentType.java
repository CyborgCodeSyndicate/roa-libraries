package io.cyborgcode.roa.ui.components.accordion;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

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
@Pandora(
      description = "Component type marker for Accordion components; enums implement this to declare "
            + "available accordion types.",
      tags = {"ui", "ui-component-type", "accordion"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableAccordionComponentTypes.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface AccordionComponentType extends ComponentType {
}
