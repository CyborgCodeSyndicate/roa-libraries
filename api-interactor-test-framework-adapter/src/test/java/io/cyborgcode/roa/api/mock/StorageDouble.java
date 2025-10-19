package io.cyborgcode.roa.api.mock;

import io.cyborgcode.roa.framework.storage.Storage;
import java.util.Map;

public class StorageDouble extends Storage {

   public Map<Object, Object> subStorage = new java.util.HashMap<>();

   @Override
   public Storage sub(Enum<?> key) {
      return this;
   }

   @Override
   public <T> void put(Enum<?> key, T value) {
      subStorage.put(key, value);
   }
}