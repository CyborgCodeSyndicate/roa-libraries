package io.cyborgcode.roa.ui.components.table.model;

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
public class TableCell<C> {

   /**
    * The {@link C} representing the table cell.
    */
   private C element;

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
