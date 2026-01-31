package io.cyborgcode.roa.ui.service.fluent;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.qameta.allure.Allure;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;

/**
 * A fluent service class for handling navigation-related actions in a web automation framework.
 *
 * <p>Provides methods to navigate between pages, handle browser tabs/windows, interact with frames, and manage alerts.
 *
 * <p>The generic type {@code T} represents the UI service fluent implementation that extends {@link UiServiceFluent},
 * allowing method chaining for seamless interaction.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
@Pandora(
      description = "Fluent UI service for browser navigation: URL navigation, history, tabs/windows, "
            + "frames, and alerts.",
      tags = {"ui", "fluent", "browser"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "fluent-service")
      }
)
public class NavigationServiceFluent<T extends UiServiceFluent<?>> {

   private final T uiServiceFluent;
   private final SmartWebDriver driver;

   /**
    * Constructs a new {@code NavigationServiceFluent} instance.
    *
    * @param uiServiceFluent The parent fluent UI service instance.
    * @param webDriver       The smart web driver used for navigation actions.
    */
   public NavigationServiceFluent(T uiServiceFluent, SmartWebDriver webDriver) {
      this.uiServiceFluent = uiServiceFluent;
      this.driver = webDriver;
   }

   /**
    * Navigates to the specified URL and maximizes the browser window.
    *
    * @param url The URL to navigate to.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Navigate to a URL and maximize the browser window.",
         tags = {"ui", "browser"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T navigate(
         @Pandora(
               description = "Destination URL to navigate to."
         ) String url) {
      Allure.step("[UI - Navigation] Navigate to the URL and maximize the browser window");

      driver.manage().window().maximize();
      driver.get(url);
      return uiServiceFluent;
   }

   /**
    * Navigates back in the browser history.
    *
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Navigate back in the browser history.",
         tags = {"ui", "browser"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T back() {
      Allure.step("[UI - Navigation] Navigate back in the browser history");

      driver.navigate().back();
      return uiServiceFluent;
   }

   /**
    * Navigates forward in the browser history.
    *
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Navigate forward in the browser history.",
         tags = {"ui", "browser"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T forward() {
      Allure.step("[UI - Navigation] Navigate forward in the browser history");

      driver.navigate().forward();
      return uiServiceFluent;
   }

   /**
    * Refreshes the current page.
    *
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Refresh the current page.",
         tags = {"ui", "browser"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T refresh() {
      Allure.step("[UI - Navigation] Refresh the current page");

      driver.navigate().refresh();
      return uiServiceFluent;
   }

   /**
    * Switches to a newly opened browser tab.
    *
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Switch to a newly opened browser tab.",
         tags = {"ui", "browser"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T switchToNewTab() {
      Allure.step("[UI - Navigation] Switch to a newly opened browser tab");

      String currentHandle = driver.getWindowHandle();
      for (String handle : driver.getWindowHandles()) {
         if (!handle.equals(currentHandle)) {
            driver.switchTo().window(handle);
            break;
         }
      }
      return uiServiceFluent;
   }

   /**
    * Switches to a browser window with the specified title.
    *
    * @param windowTitle The title of the target window.
    * @return The fluent UI service instance.
    * @throws NoSuchWindowException if no window with the given title is found.
    */
   @Pandora(
         description = "Switch to a browser window with the specified title.",
         tags = {"ui", "browser"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T switchToWindow(@Pandora(description = "Target window title to switch to.") String windowTitle) {
      Allure.step("[UI - Navigation] Switch to the browser window with the title: " + windowTitle);

      for (String handle : driver.getWindowHandles()) {
         driver.switchTo().window(handle);
         if (Objects.equals(driver.getTitle(), windowTitle)) {
            return uiServiceFluent;
         }
      }
      throw new NoSuchWindowException("No window found with title: " + windowTitle);
   }

   /**
    * Closes the current browser tab and switches to another available tab if present.
    *
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Close the current tab and switch to the next available tab, if any.",
         tags = {"ui", "browser"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T closeCurrentTab() {
      Allure.step("[UI - Navigation] Close the current browser tab");

      driver.close();
      if (!driver.getWindowHandles().isEmpty()) {
         driver.switchTo().window(driver.getWindowHandles().iterator().next());
      }
      return uiServiceFluent;
   }

   /**
    * Switches to an iframe using its index.
    *
    * @param index The index of the iframe.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Switch to an iframe using its index.",
         tags = {"ui", "browser"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T switchToFrameByIndex(@Pandora(description = "Index of the iframe to switch to.") int index) {
      Allure.step("[UI - Navigation] Switch to the iframe using index: " + index);

      driver.switchTo().frame(index);
      return uiServiceFluent;
   }

   /**
    * Switches to an iframe using its name or ID.
    *
    * @param nameOrId The name or ID of the iframe.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Switch to an iframe using its name or ID.",
         tags = {"ui", "browser"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T switchToFrameByNameOrId(@Pandora(description = "Name or ID of the iframe.") String nameOrId) {
      Allure.step("[UI - Navigation] Switch to the iframe using name or ID: " + nameOrId);

      driver.switchTo().frame(nameOrId);
      return uiServiceFluent;
   }

   /**
    * Switches to the parent frame of the current iframe.
    *
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Switch to the parent frame.",
         tags = {"ui", "browser"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T switchToParentFrame() {
      Allure.step("[UI - Navigation] Switch to the parent frame");

      driver.switchTo().parentFrame();
      return uiServiceFluent;
   }

   /**
    * Switches back to the default content from an iframe.
    *
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Switch back to the default content from an iframe.",
         tags = {"ui", "browser"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T switchToDefaultContent() {
      Allure.step("[UI - Navigation] Switch back to the default content from the iframe");

      driver.switchTo().defaultContent();
      return uiServiceFluent;
   }

   /**
    * Accepts an alert pop-up.
    *
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Accept the currently displayed alert pop-up.",
         tags = {"ui", "browser", "alert"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T acceptAlert() {
      Allure.step("[UI - Navigation] Accept the alert pop-up");

      driver.switchTo().alert().accept();
      return uiServiceFluent;
   }

   /**
    * Dismisses an alert pop-up.
    *
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Dismiss the currently displayed alert pop-up.",
         tags = {"ui", "browser", "alert"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T dismissAlert() {
      Allure.step("[UI - Navigation] Dismiss the alert pop-up");

      driver.switchTo().alert().dismiss();
      return uiServiceFluent;
   }

   /**
    * Retrieves the text of an alert pop-up.
    *
    * @return The text of the alert.
    */
   private String getAlertText() {
      return driver.switchTo().alert().getText();
   }

   /**
    * Validates that the alert text matches the expected value.
    *
    * @param expected The expected alert text.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the alert text equals the expected value (hard assertion).",
         tags = {"ui", "browser", "alert"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateAlertText(@Pandora(description = "Expected alert text.") String expected) {
      Allure.step("[UI - Navigation] Validate that the alert text matches the expected value");

      return validateAlertText(expected, false);
   }

   /**
    * Validates that the alert text matches the expected value, with an optional soft assertion.
    *
    * @param expected The expected alert text.
    * @param soft     If {@code true}, the validation will be performed softly.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the alert text equals the expected value, optionally using "
               + "a soft assertion.",
         tags = {"ui", "browser", "alert"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateAlertText(
         @Pandora(
               description = "Expected alert text."
         ) String expected,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft) {
      Allure.step("[UI - Navigation] Validate alert text with expected value: " + expected);

      String alertText = getAlertText();
      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(alertText).as("Validating Alert text")
                     .isEqualTo(expected));
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(alertText).as("Validating Alert text")
                     .isEqualTo(expected));
      }
   }

   /**
    * Opens a new browser tab using JavaScript and switches to it.
    *
    * @return The fluent UI service instance.
    */
   @SuppressFBWarnings(value = "BC_UNCONFIRMED_CAST")
   @Pandora(
         description = "Open a new browser tab using JavaScript and switch to it.",
         tags = {"ui", "browser"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T openNewTab() {
      Allure.step("[UI - Navigation] Open a new browser tab using JavaScript and switch to it");

      ((JavascriptExecutor) driver).executeScript("window.open();");
      return switchToNewTab();
   }

}
