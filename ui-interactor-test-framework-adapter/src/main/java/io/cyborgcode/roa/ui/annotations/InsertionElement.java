package io.cyborgcode.roa.ui.annotations;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.selenium.UiElement;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define UI elements used for data insertion in automated tests.
 *
 * <p>This annotation is applied to fields in page objects or test components to specify
 * UI elements that should be used for inserting data. It provides details such as the locator class,
 * the specific element within that class, and the order in which elements should be processed.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Field-level annotation describing a UI element used for data insertion "
            + "(locator + element + order).",
      tags = {"ui", "insertion", "annotation"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-insertion-annotation"),
         @PandoraOptions.Meta(key = "scope", value = "field")
      }
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InsertionElement {

   /**
    * Specifies the class that defines the UI element's locator.
    *
    * @return The enum class representing UI element locators.
    */
   @Pandora(
         description = "Class that defines the locator strategy for the UI element (e.g., enum of selectors)."
   )
   Class<? extends UiElement> locatorClass();

   /**
    * Specifies the name of the UI element within the locator class.
    *
    * @return The string representing the enum constant of the UI element.
    */
   @Pandora(
         description = "Name/enum constant within the locator class that identifies the specific UI element."
   )
   String elementEnum();

   /**
    * Defines the order in which elements should be processed during data insertion.
    *
    * <p>Lower values are processed first, allowing sequential interactions with form fields.
    *
    * @return The order of the element in the insertion process.
    */
   @Pandora(
         description = "Processing order for data insertion; lower numbers are handled first."
   )
   int order();
}
