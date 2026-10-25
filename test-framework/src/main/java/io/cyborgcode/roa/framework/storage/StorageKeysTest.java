package io.cyborgcode.roa.framework.storage;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;

/**
 * Defines keys used for organizing test data within the storage system.
 *
 * <p>This enum provides logical identifiers for various data groups used during a test execution.
 * These keys help segregate and retrieve test data accurately within the storage context.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Storage namespaces/keys used by the framework test runtime. "
            + "These enum constants identify logical groups inside Storage "
            + "(arguments, pre-arguments, static data, hooks).",
      tags = {"framework", "storage"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "storage-keys"),
         @AiCompassOptions.Meta(key = "scope", value = "test-runtime")
      },
      enumType = StorageKeysTest.class
)
public enum StorageKeysTest {

   /**
    * Key for storing argument-related test data.
    */
   ARGUMENTS,

   /**
    * Key for storing pre-execution argument data.
    */
   PRE_ARGUMENTS,

   /**
    * Key for storing static test data.
    */
   STATIC_DATA,
   HOOKS,

}
