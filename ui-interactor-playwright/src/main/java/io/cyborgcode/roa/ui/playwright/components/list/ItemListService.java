package io.cyborgcode.roa.ui.playwright.components.list;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.list.ItemListServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific list service interface.
 *
 * <p>Binds the core generic {@link ItemListServiceCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ItemListService extends ItemListServiceCore<PwElement, PwBy> {

}
