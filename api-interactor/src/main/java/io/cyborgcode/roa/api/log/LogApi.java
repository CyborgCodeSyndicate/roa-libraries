package io.cyborgcode.roa.api.log;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.utilities.logging.LogCore;

/**
 * Provides logging utilities for API-related operations.
 *
 * <p>This class serves as the centralized logger for API interactions, offering
 * multiple log levels such as informational, warnings, debugging, validation, and step-based logging.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Central API logger facade for ROA. "
            + "Use it to log API flow steps, validation messages and debug information.",
      tags = {"api", "logging"},
      creation = CreationKind.SINGLETON
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/api-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "api-logger")
      }
)
public final class LogApi extends LogCore {

   private static LogApi instance;

   private LogApi() {
      super("ROA.API", "API");
   }

   /**
    * Logs an informational message.
    *
    * @param message The message to log.
    * @param args    Optional arguments to format the message.
    */
   @AiCompass(
         description = "Log an informational API message."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public static void info(@AiCompass(
                                 description = "Log message template (may contain placeholders like {})."
                           )
                           String message,
                           @AiCompass(
                                 description = "Optional arguments used to format the message template."
                           )
                           Object... args) {
      getInstance().infoLog(message, args);
   }

   /**
    * Logs a warning message.
    *
    * @param message The warning message.
    * @param args    Optional arguments to format the message.
    */
   @AiCompass(
         description = "Log a warning API message."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public static void warn(@AiCompass(
                                 description = "Log message template (may contain placeholders like {})."
                           )
                           String message,
                           @AiCompass(
                                 description = "Optional arguments used to format the message template."
                           )
                           Object... args) {
      getInstance().warnLog(message, args);
   }

   /**
    * Logs an error message.
    *
    * @param message The error message.
    * @param args    Optional arguments to format the message.
    */
   @AiCompass(
         description = "Log an error API message."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public static void error(@AiCompass(
                                  description = "Log message template (may contain placeholders like {})."
                            )
                            String message,
                            @AiCompass(
                                  description = "Optional arguments used to format the message template."
                            )
                            Object... args) {
      getInstance().errorLog(message, args);
   }

   /**
    * Logs a debug message.
    *
    * @param message The debug message.
    * @param args    Optional arguments to format the message.
    */
   @AiCompass(
         description = "Log a debug API message."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public static void debug(@AiCompass(
                                  description = "Log message template (may contain placeholders like {})."
                            )
                            String message,
                            @AiCompass(
                                  description = "Optional arguments used to format the message template."
                            )
                            Object... args) {
      getInstance().debugLog(message, args);
   }

   /**
    * Logs a trace-level message.
    *
    * @param message The trace message.
    * @param args    Optional arguments to format the message.
    */
   @AiCompass(
         description = "Log a trace-level API message."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public static void trace(@AiCompass(
                                  description = "Log message template (may contain placeholders like {})."
                            )
                            String message,
                            @AiCompass(
                                  description = "Optional arguments used to format the message template."
                            )
                            Object... args) {
      getInstance().traceLog(message, args);
   }

   /**
    * Logs a step message to track API flow.
    *
    * @param message The step description.
    * @param args    Optional arguments to format the message.
    */
   @AiCompass(
         description = "Log an API flow step message."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public static void step(@AiCompass(
                                 description = "Step message template describing the current API flow action."
                           )
                           String message,
                           @AiCompass(
                                 description = "Optional arguments used to format the step message."
                           )
                           Object... args) {
      getInstance().stepLog(message, args);
   }

   /**
    * Logs a validation message for API assertions.
    *
    * @param message The validation message.
    * @param args    Optional arguments to format the message.
    */
   @AiCompass(
         description = "Log an API validation/assertion message."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public static void validation(@AiCompass(
                                       description = "Validation message template "
                                             + "describing what is being asserted/validated."
                                 )
                                 String message,
                                 @AiCompass(
                                       description = "Optional arguments used to format the validation message."
                                 )
                                 Object... args) {
      getInstance().validationLog(message, args);
   }

   /**
    * Logs extended details when debugging is enabled.
    *
    * @param message The extended log message.
    * @param args    Optional arguments to format the message.
    */
   @AiCompass(
         description = "Log an extended (verbose) debug message."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public static void extended(@AiCompass(
                                     description = "Extended debug message template (usually verbose details)."
                               )
                               String message,
                               @AiCompass(
                                     description = "Optional arguments used to format the extended message."
                               )
                               Object... args) {
      getInstance().extendedLog(message, args);
   }

   /**
    * Extends the logging functionality using a custom log core instance.
    *
    * @param instance The custom log instance to extend.
    * @param <T>      A subclass of {@link LogCore}.
    */
   public static <T extends LogCore> void extend(final T instance) {
      LogApi.instance = (LogApi) instance;
   }

   /**
    * Retrieves the singleton instance of {@code LogApi}.
    *
    * @return The singleton instance of {@code LogApi}.
    */
   private static LogApi getInstance() {
      if (instance == null) {
         instance = new LogApi();
      }
      return instance;
   }

}
