package io.cyborgcode.roa.ui.playwright.components.base;

import com.microsoft.playwright.Page;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Base class for all Playwright UI components.
 *
 * <p>This class holds a reference to the {@link Page} instance,
 * providing all components with access to the Playwright page for performing interactions.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Getter
@AllArgsConstructor
public abstract class BaseComponent {

   /**
    * The Page instance used for UI interactions.
    */
   protected final Page page;

}
