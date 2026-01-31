package io.cyborgcode.roa.ui.components.table.annotations;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.openqa.selenium.support.FindBy;

/**
 * Defines locators for identifying a specific cell within a table.
 * This annotation is applied to fields in a row model class to specify how
 * table cells are located in the UI.
 *
 * <p>It provides three locators:
 * <ul>
 *   <li>{@code cellLocator} - Identifies the cell in a row.</li>
 *   <li>{@code headerCellLocator} - Identifies the corresponding column header.</li>
 *   <li>{@code cellTextLocator} - (Optional) Identifies the text element inside the cell.</li>
 * </ul>
 *
 * <p>The table framework processes this annotation at runtime to extract
 * and interact with table data dynamically.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Annotation defining locators for identifying a specific table cell and related elements.",
      tags = {"ui", "annotation", "table-cell-locator"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "annotation")
      }
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableCellLocator {

   /**
    * Locator for identifying the cell within a table row.
    *
    * @return the {@link FindBy} annotation specifying the cell locator.
    */
   @Pandora(
         description = "Locator for identifying the cell within a table row.",
         tags = {"annotation", "table-cell-locator"}
   )
   FindBy cellLocator();

   /**
    * (Optional) Specifies the table section if the table consists of multiple sections.
    *
    * @return the name of the table section, default is an empty string.
    */
   @Pandora(
         description = "Optional table section name when a table consists of multiple sections.",
         tags = {"annotation", "table-cell-locator"}
   )
   String tableSection() default "";

   /**
    * (Optional) Locator for extracting the text content inside the cell.
    * Defaults to the current element.
    *
    * @return the {@link FindBy} annotation specifying the text locator.
    */
   @Pandora(
         description = "Optional locator for extracting text content inside the cell (defaults to current element).",
         tags = {"annotation", "table-cell-locator"}
   )
   FindBy cellTextLocator() default @FindBy(xpath = ".");

   /**
    * Locator for identifying the corresponding column header.
    *
    * @return the {@link FindBy} annotation specifying the header locator.
    */
   @Pandora(
         description = "Locator for identifying the corresponding column header.",
         tags = {"annotation", "table-cell-locator"}
   )
   FindBy headerCellLocator() default @FindBy(xpath = ".");

}
