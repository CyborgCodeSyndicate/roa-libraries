package io.cyborgcode.roa.ui.playwright.components.table.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines selectors for identifying a specific cell within a table.
 * This annotation is applied to fields in a row model class to specify how
 * table cells are located in the UI.
 *
 * <p>It provides three selectors:
 * <ul>
 *   <li>{@code cellSelector} - Identifies the cell in a row.</li>
 *   <li>{@code headerCellSelector} - Identifies the corresponding column header.</li>
 *   <li>{@code cellTextSelector} - (Optional) Identifies the text element inside the cell.</li>
 * </ul>
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableCellLocator {

   /**
    * Selector for identifying the cell within a table row.
    *
    * @return the CSS/XPath selector specifying the cell.
    */
   String cellSelector();

   /**
    * (Optional) Specifies the table section if the table consists of multiple sections.
    *
    * @return the name of the table section, default is an empty string.
    */
   String tableSection() default "";

   /**
    * (Optional) Selector for extracting the text content inside the cell.
    * Defaults to "." (the current element itself).
    *
    * @return the CSS/XPath selector specifying the text locator.
    */
   String cellTextSelector() default "";

   /**
    * Selector for identifying the corresponding column header.
    * Defaults to empty string (not specified).
    *
    * @return the CSS/XPath selector specifying the header locator.
    */
   String headerCellSelector() default "";

}
