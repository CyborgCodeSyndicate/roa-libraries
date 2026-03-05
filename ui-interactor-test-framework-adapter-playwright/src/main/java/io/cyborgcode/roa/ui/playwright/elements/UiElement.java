package io.cyborgcode.roa.ui.playwright.elements;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.elements.UiElementCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Represents a base interface for Playwright UI elements.
 *
 * <p>This interface binds the generic {@link UiElementCore} to Playwright-specific types:
 * {@link PwBy} as the locator type and {@link Page} as the driver type.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings("java:S1452")
public interface UiElement extends UiElementCore<PwBy, Page> {

}
