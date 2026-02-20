package io.cyborgcode.roa.ui.components.base;

/**
 * Abstract base class for all UI components.
 *
 * <p>This generic class provides a reference to the driver/page instance,
 * allowing all components to interact with the browser.
 *
 * @param <D> The driver or page type (e.g., Playwright's {@code Page} or Selenium's {@code WebDriver}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class BaseComponentCore<D> {

   /**
    * The driver/page instance used to interact with UI elements.
    */
   protected final D driver;

   /**
    * Constructs a new {@code BaseComponent} with the specified driver.
    *
    * @param driver The driver/page instance used for UI interactions.
    */
   protected BaseComponentCore(final D driver) {
      this.driver = driver;
   }

   /**
    * Returns the driver/page instance.
    *
    * @return The driver/page instance.
    */
   protected D getDriver() {
      return driver;
   }
}
