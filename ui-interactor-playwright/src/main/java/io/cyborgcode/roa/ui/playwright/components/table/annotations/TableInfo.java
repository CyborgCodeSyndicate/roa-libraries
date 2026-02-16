package io.cyborgcode.roa.ui.playwright.components.table.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines metadata for locating and interacting with a table in the UI.
 * This annotation is applied to a row model class to specify how the table is
 * identified and structured.
 *
 * <p>It provides three key selectors:
 * <ul>
 *   <li>{@code tableContainerSelector} - Identifies the table container element.</li>
 *   <li>{@code rowsSelector} - Identifies the rows inside the table.</li>
 *   <li>{@code headerRowSelector} - Identifies the header row.</li>
 * </ul>
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableInfo {

   /**
    * Selector for identifying the table container.
    *
    * @return the CSS/XPath selector specifying the table container.
    */
   String tableContainerSelector();

   /**
    * Selector for identifying the rows inside the table.
    *
    * @return the CSS/XPath selector specifying the rows.
    */
   String rowsSelector();

   /**
    * Selector for identifying the table's header row.
    *
    * @return the CSS/XPath selector specifying the header row.
    */
   String headerRowSelector() default "";

}
