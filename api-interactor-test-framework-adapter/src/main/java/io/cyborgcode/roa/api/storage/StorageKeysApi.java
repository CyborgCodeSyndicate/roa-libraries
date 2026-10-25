package io.cyborgcode.roa.api.storage;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;

/**
 * Defines storage keys for API-related data.
 *
 * <p>This enum provides keys used for storing API authentication credentials
 * and other related data within the framework's storage system.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Storage keys for API-related data in quest storage (API namespace, username, password).",
      tags = {"api", "storage"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "api-storage-key")
      },
      enumType = StorageKeysApi.class
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
