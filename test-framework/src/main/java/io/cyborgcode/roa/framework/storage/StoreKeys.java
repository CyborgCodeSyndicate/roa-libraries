package io.cyborgcode.roa.framework.storage;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;
import lombok.Getter;

/**
 * Defines keys used for storing and retrieving global test execution data.
 *
 * <p>This enum provides unique identifiers for managing test execution contexts,
 * metadata, and framework-level consumers within the storage system.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Getter
@AiCompass(
      description = "Global store keys used by the framework to keep execution-wide objects "
            + "(quest instance, quest consumers, start time, HTML fragments, hook parameters).",
      tags = {"framework", "storage"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "store-keys"),
         @AiCompassOptions.Meta(key = "scope", value = "global-execution")
      },
      enumType = StoreKeys.class
)
public enum StoreKeys {

   /**
    * Key for storing the current {@code SuperQuest} instance.
    */
   QUEST,

   /**
    * Key for storing a list of consumers that modify or interact with the quest instance.
    */
   QUEST_CONSUMERS,

   /**
    * Key for storing the start time of the test execution.
    */
   START_TIME,

   /**
    * Key for storing the rendered HTML output or fragments for use in reporting or visualization.
    */
   HTML,

   /**
    * Key for storing hook-related parameters, such as setup and teardown data,
    * to be shared between extensions and test logic.
    */
   HOOKS_PARAMS;

}
