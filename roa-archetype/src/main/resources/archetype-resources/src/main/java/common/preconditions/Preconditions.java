package ${package}.common.preconditions;

import io.cyborgcode.roa.framework.parameters.PreQuestJourney;
import io.cyborgcode.roa.framework.quest.SuperQuest;

import java.util.function.BiConsumer;

/**
 * Example registry of pre-test journeys.
 *
 * <p>Replace these entries with real preconditions required by
 * your application (e.g. logging in, creating test data, preparing state).</p>
 */
public enum Preconditions implements PreQuestJourney<Preconditions> {

   EXAMPLE_PRECONDITION((quest, args) ->
         PreconditionFunctions.examplePrecondition(quest, args));

   public static final class Data {
      public static final String EXAMPLE_PRECONDITION = "EXAMPLE_PRECONDITION";
      private Data() {}
   }

   private final BiConsumer<SuperQuest, Object[]> function;

   Preconditions(BiConsumer<SuperQuest, Object[]> function) {
      this.function = function;
   }

   @Override
   public BiConsumer<SuperQuest, Object[]> journey() {
      return function;
   }

   @Override
   public Preconditions enumImpl() {
      return this;
   }
}
