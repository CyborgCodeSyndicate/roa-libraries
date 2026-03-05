package io.cyborgcode.roa.ui.playwright.components.table.service;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.components.table.model.TableCell;
import io.cyborgcode.roa.ui.components.table.model.TableCellLocatorInfo;
import io.cyborgcode.roa.ui.components.table.model.TableLocators;
import io.cyborgcode.roa.ui.components.table.registry.TableServiceRegistry;
import io.cyborgcode.roa.ui.components.table.service.Table;
import io.cyborgcode.roa.ui.components.table.service.TableImplCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.table.annotations.TableCellLocator;
import io.cyborgcode.roa.ui.playwright.components.table.annotations.TableInfo;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Base implementation of the {@link Table} interface for Playwright, providing core functionalities
 * for reading, filtering, sorting, and inserting values into table elements in the UI.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings("java:S3011")
public abstract class TableImpl extends TableImplCore<PwElement, PwBy, Page> {


   protected TableImpl(Page driver) {
      super(driver);
   }

   protected TableImpl(Page driver, TableServiceRegistry<PwElement> serviceRegistry) {
      super(driver, serviceRegistry);
   }

   @Override
   protected PwElement getTableContainer(PwBy tableContainerSelector) {
      return (PwElement) tableContainerSelector.on(driver);
   }

   @Override
   protected String getTextFromElement(PwElement element) {
      return element.textContent();
   }

   @Override
   protected List<PwElement> locateElements(PwElement element, PwBy locator) {
      return locator.within(element).allElements();
   }

   @Override
   protected List<PwElement> getRows(PwElement tableContainer, PwBy tableRowsSelector, String section) {
      tableRowsSelector.within(tableContainer).first().waitFor();
      return tableRowsSelector.within(tableContainer).allElements();
   }

   @Override
   protected PwElement getHeaderRow(PwElement tableContainer, PwBy headerRowSelector, String tableSection) {
      return headerRowSelector.within(tableContainer);
   }


   @Override
   protected TableCell<PwElement> buildTableCell(PwElement container, PwBy cellSelector, PwBy textSelector) {
      final PwElement cellElement = (cellSelector == null)
            ? container
            : cellSelector.within(container);

      final String text;
      if (textSelector == null) {
         text = cellElement.textContent();
      } else {
         text = textSelector.within(cellElement).textContent();
      }
      return new TableCell<>(cellElement, text);
   }

   @Override
   protected TableLocators<PwBy> getTableLocators(Class<?> clazz) {
      TableInfo annotation = clazz.getAnnotation(TableInfo.class);
      if (annotation == null) {
         throw new IllegalArgumentException(
               "Your class: " + clazz.getSimpleName() + " is missing @TableInfo annotation for the table container");
      }
      return new TableLocators<>(
            PwBy.css(annotation.tableContainerSelector()),
            PwBy.css(annotation.rowsSelector()),
            PwBy.css(annotation.headerRowSelector())
      );
   }

   @Override
   protected Class<? extends Annotation> tableCellLocatorAnnotation() {
      return TableCellLocator.class;
   }

   @Override
   protected TableCellLocatorInfo<PwBy> populateInfoFromAnnotation(Annotation annotation) {
      TableCellLocator tableCellLocator = (TableCellLocator) annotation;
      return new TableCellLocatorInfo<>(PwBy.css(tableCellLocator.cellSelector()),
            tableCellLocator.tableSection(), PwBy.css(tableCellLocator.cellTextSelector()),
            PwBy.css(tableCellLocator.headerCellSelector()));
   }
}
