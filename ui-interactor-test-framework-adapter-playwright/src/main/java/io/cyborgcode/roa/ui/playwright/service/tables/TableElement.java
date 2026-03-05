package io.cyborgcode.roa.ui.playwright.service.tables;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.service.tables.TableElementCore;

/**
 * Represents a Playwright-specific table element that can be interacted with in the UI.
 *
 * <p>This interface binds the generic {@link TableElementCore} to Playwright's {@link Page}
 * as the driver type.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface TableElement<K extends Enum<K>> extends TableElementCore<K, Page> {

}
