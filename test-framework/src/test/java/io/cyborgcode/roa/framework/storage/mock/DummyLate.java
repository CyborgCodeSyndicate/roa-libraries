package io.cyborgcode.roa.framework.storage.mock;

import io.cyborgcode.roa.framework.parameters.Late;

public class DummyLate<T> implements Late<T> {

   private final T value;

   public DummyLate(T value) {
      this.value = value;
   }

   @Override
   public T create() {
      return value;
   }
}
