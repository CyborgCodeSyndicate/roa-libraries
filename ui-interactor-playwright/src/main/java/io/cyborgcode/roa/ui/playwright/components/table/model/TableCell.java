package io.cyborgcode.roa.ui.playwright.components.table.model;

import com.microsoft.playwright.Locator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a table cell in the UI, encapsulating both the underlying locator
 * and its textual content.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AllArgsConstructor
@Getter
@Setter
public class TableCell {

   /**
    * The {@link Locator} representing the table cell.
    */
   private Locator element;

   /**
    * The textual content of the table cell.
    */
   private String text;

   /**
    * Constructs a {@code TableCell} with only a text value.
    *
    * @param value The text content of the cell.
    */
   public TableCell(final String value) {
      this.text = value;
   }
}
