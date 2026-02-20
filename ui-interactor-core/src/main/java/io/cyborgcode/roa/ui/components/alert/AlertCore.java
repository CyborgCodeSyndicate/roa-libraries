package io.cyborgcode.roa.ui.components.alert;

/**
 * Defines operations for interacting with alert UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface AlertCore<E> {

   String getValue(E container);

   boolean isVisible(E container);

}
