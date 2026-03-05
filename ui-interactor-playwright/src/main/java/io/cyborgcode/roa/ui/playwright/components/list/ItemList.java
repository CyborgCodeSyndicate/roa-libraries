package io.cyborgcode.roa.ui.playwright.components.list;

import io.cyborgcode.roa.ui.components.list.ItemListCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific list component interface.
 *
 * <p>Binds the core generic {@link ItemListCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ItemList extends ItemListCore<PwElement, PwBy> {

}
