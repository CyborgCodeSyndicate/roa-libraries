package io.cyborgcode.roa.ui.playwright.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Centralized logging utility for Playwright UI operations.
 *
 * <p>Provides static methods for logging at different levels,
 * including custom levels for step-by-step execution, validation, and extended debugging.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class LogUi {

   private static final Logger LOGGER = LoggerFactory.getLogger(LogUi.class);

   private static final String STEP_PREFIX = "[STEP] ";
   private static final String VALIDATION_PREFIX = "[VALIDATION] ";
   private static final String EXTENDED_PREFIX = "[EXTENDED] ";

   private LogUi() {
   }

   /**
    * Logs an informational message.
    *
    * @param message The log message.
    * @param args    The message arguments.
    */
   public static void info(final String message, final Object... args) {
      LOGGER.info(message, args);
   }

   /**
    * Logs a warning message.
    *
    * @param message The log message.
    * @param args    The message arguments.
    */
   public static void warn(final String message, final Object... args) {
      LOGGER.warn(message, args);
   }

   /**
    * Logs an error message.
    *
    * @param message The log message.
    * @param args    The message arguments.
    */
   public static void error(final String message, final Object... args) {
      LOGGER.error(message, args);
   }

   /**
    * Logs a debug message.
    *
    * @param message The log message.
    * @param args    The message arguments.
    */
   public static void debug(final String message, final Object... args) {
      LOGGER.debug(message, args);
   }

   /**
    * Logs a trace message.
    *
    * @param message The log message.
    * @param args    The message arguments.
    */
   public static void trace(final String message, final Object... args) {
      LOGGER.trace(message, args);
   }

   /**
    * Logs a step message indicating a significant UI action.
    *
    * @param message The log message.
    * @param args    The message arguments.
    */
   public static void step(final String message, final Object... args) {
      LOGGER.info(STEP_PREFIX + message, args);
   }

   /**
    * Logs a validation message for asserting UI state.
    *
    * @param message The log message.
    * @param args    The message arguments.
    */
   public static void validation(final String message, final Object... args) {
      LOGGER.info(VALIDATION_PREFIX + message, args);
   }

   /**
    * Logs an extended debug message for detailed troubleshooting.
    *
    * @param message The log message.
    * @param args    The message arguments.
    */
   public static void extended(final String message, final Object... args) {
      LOGGER.debug(EXTENDED_PREFIX + message, args);
   }

}
