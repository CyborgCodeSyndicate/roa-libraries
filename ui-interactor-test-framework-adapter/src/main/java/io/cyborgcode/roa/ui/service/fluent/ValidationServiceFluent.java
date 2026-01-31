package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import javax.swing.text.html.HTML;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

/**
 * Provides fluent validation methods for verifying text presence in UI fields.
 *
 * <p>This class enables performing both soft and hard assertions on text validation inside HTML elements.
 * It ensures that expected text appears as intended in UI components, improving automated UI verification.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings("java:S5960")
@Pandora(
      description = "Fluent UI service for generic validations: text presence checks with hard/soft assertions.",
      tags = {"ui", "fluent", "validation"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "fluent-service")
      }
)
public class ValidationServiceFluent<T extends UiServiceFluent<?>> {

   private final T uiServiceFluent;
   private final SmartWebDriver driver;

   /**
    * Constructs a {@code ValidationServiceFluent} instance.
    *
    * @param uiServiceFluent The fluent UI service instance.
    * @param webDriver       The instance of {@link SmartWebDriver} used for validation.
    */
   public ValidationServiceFluent(T uiServiceFluent, SmartWebDriver webDriver) {
      this.uiServiceFluent = uiServiceFluent;
      driver = webDriver;
   }

   /**
    * Validates that the specified text is present within a given HTML tag.
    * This method performs a hard assertion by default.
    *
    * @param tag  The {@link HTML.Tag} representing the HTML element to validate.
    * @param text The text expected to be present within the specified tag.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified text is present within the given HTML tag (hard assertion).",
         tags = {"ui", "validation"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateTextInField(
         @Pandora(
               description = "HTML tag to search within."
         ) HTML.Tag tag,
         @Pandora(
               description = "Expected text to be present."
         ) String text) {
      return validateTextInField(tag, text, false);
   }

   /**
    * Validates that the specified text is present within a given HTML tag.
    * Supports both hard and soft assertions based on the provided flag.
    *
    * @param tag  The {@link HTML.Tag} representing the HTML element to validate.
    * @param text The text expected to be present within the specified tag.
    * @param soft A boolean indicating whether the validation should be performed softly.
    *             If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified text is present within the given HTML tag, "
               + "optionally using a soft assertion.",
         tags = {"ui", "validation"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateTextInField(
         @Pandora(
               description = "HTML tag to search within."
         ) HTML.Tag tag,
         @Pandora(
               description = "Expected text to be present."
         ) String text,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft) {
      By selector = By.xpath("//" + tag.toString() + "[contains(text(),'" + text + "')]");
      String description = String.format("Validate text: '%s' is present in tag: '%s'", text, tag);
      String errorMessage = String.format("Missing text: %s in tag: %s", text, tag);
      boolean condition = elementIsPresentAfterTime(selector, 2);
      validateTrue(condition, description, soft, errorMessage);
      return uiServiceFluent;
   }

   /**
    * Waits for an element to be present within a specified timeout duration.
    *
    * @param locator The {@link By} locator of the element.
    * @param seconds The number of seconds to wait before checking.
    * @return {@code true} if the element is found within the given time, otherwise {@code false}.
    */
   private boolean elementIsPresentAfterTime(By locator, int seconds) {
      driver.waitUntilElementIsShown(locator, seconds);
      return driver.checkNoException(() -> driver.findSmartElement(locator));
   }


   private void validateTrue(boolean condition, String description, boolean soft, String errorMessage) {
      if (!soft) {
         uiServiceFluent.validate(() -> Assertions.assertThat(condition)
               .as(description)
               .withFailMessage(errorMessage)
               .isTrue());
      } else {
         uiServiceFluent.validate(softAssertions -> softAssertions.assertThat(condition)
               .as(description)
               .withFailMessage(errorMessage)
               .isTrue());
      }
   }

}
