package io.cyborgcode.roa.ui.components.base;

import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;

/**
 * Serves as the base class for all UI components.
 *
 * <p>This class provides core functionality for interacting with UI elements,
 * such as buttons, alerts, checkboxes, and more, using a {@link SmartWebDriver}.
 *
 * <p>All UI component implementations should extend this class to ensure
 * standardized handling of WebDriver interactions.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class BaseComponent {

   /**
    * The smart WebDriver instance used to interact with UI elements.
    */
   protected SmartWebDriver driver;

   /**
    * Constructs a new {@code BaseComponent} with the specified SmartWebDriver.
    *
    * @param smartWebDriver The WebDriver instance used for UI interactions.
    */
   public BaseComponent(final SmartWebDriver smartWebDriver) {
      this.driver = smartWebDriver;
   }

}
