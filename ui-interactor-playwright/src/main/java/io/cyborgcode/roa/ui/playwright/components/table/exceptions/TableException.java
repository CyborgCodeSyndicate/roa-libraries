package io.cyborgcode.roa.ui.playwright.components.table.exceptions;

/**
 * Exception thrown when a table-related error occurs during UI automation.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class TableException extends RuntimeException {

   public TableException(final String message) {
      super(message);
   }

   public TableException(final String message, final Throwable cause) {
      super(message, cause);
   }
}
