package io.cyborgcode.roa.framework.storage;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;

/**
 * Defines keys used for organizing test data within the storage system.
 *
 * <p>This enum provides logical identifiers for various data groups used during a test execution.
 * These keys help segregate and retrieve test data accurately within the storage context.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Storage namespaces/keys used by the framework test runtime. "
            + "These enum constants identify logical groups inside Storage "
            + "(arguments, pre-arguments, static data, hooks).",
      tags = {"framework", "storage"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "storage-keys"),
         @PandoraOptions.Meta(key = "scope", value = "test-runtime")
      }
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
