package io.cyborgcode.roa.framework.extension.mock;

import io.cyborgcode.roa.framework.parameters.PreQuestJourney;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

public class MockPreQuestJourney<T extends Enum<T>> implements PreQuestJourney<T> {

   public final AtomicBoolean invoked = new AtomicBoolean(false);

   @Override
   public BiConsumer<SuperQuest, Object[]> journey() {
      return (sq, args) -> invoked.set(true);
   }

   @Override
   public T enumImpl() {
      return null;
   }
}
