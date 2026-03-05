package io.cyborgcode.roa.ui.playwright.service.tables;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.playwright.components.table.service.TableImpl;

/**
 * Default implementation of a table component extending {@link TableImpl}.
 *
 * <p>This class is annotated with {@code @ImplementationOfType("DEFAULT")}, indicating that it serves as the
 * default implementation of a table in the Playwright framework. Currently, it does not add additional
 * functionality beyond what is provided by {@link TableImpl}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@ImplementationOfType("DEFAULT")
public class DefaultTable extends TableImpl {

   /**
    * Constructs an instance of {@code DefaultTable} using the provided Playwright Page.
    *
    * @param page The Playwright {@link Page} instance used for interacting with the table.
    */
   public DefaultTable(final Page page) {
      super(page);
   }

}
