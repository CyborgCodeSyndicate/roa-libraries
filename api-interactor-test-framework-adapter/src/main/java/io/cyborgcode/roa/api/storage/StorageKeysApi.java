package io.cyborgcode.roa.api.storage;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.api.pandora.AvailableOptionsApiAdapterRules;

/**
 * Defines storage keys for API-related data.
 *
 * <p>This enum provides keys used for storing API authentication credentials
 * and other related data within the framework's storage system.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Storage keys for API-related data in quest storage (API namespace, username, password).",
      tags = {"api", "storage"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/api-usage.json",
      availableOptionsRule = AvailableOptionsApiAdapterRules.AvailableStorageKeysApiOptions.class,
      meta = {
         @PandoraOptions.Meta(key = "type", value = "api-storage-key")
      }
)
public enum StorageKeysApi {

   /**
    * Key for API-related storage.
    */
   API,

   /**
    * Key for storing the authenticated username.
    */
   USERNAME,

   /**
    * Key for storing the authenticated password.
    */
   PASSWORD
}
