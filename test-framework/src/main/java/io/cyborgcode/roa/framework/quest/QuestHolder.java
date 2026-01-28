package io.cyborgcode.roa.framework.quest;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;

/**
 * Provides thread-local storage for the current test execution context.
 *
 * <p>This class holds a {@code SuperQuest} instance for the current thread,
 * allowing test components to access the active test context during execution.
 * It ensures that each thread's execution context remains isolated.
 *
 * <p>Use {@code set(...)} to assign the current test context, {@code get()} to retrieve it,
 * and {@code clear()} to remove it when the test execution is complete.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Thread-local holder for the current SuperQuest (active test execution context). "
            + "Used by the framework and rings to access the current quest within the executing thread.",
      tags = {"framework", "quest", "thread-local"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "quest-holder"),
         @PandoraOptions.Meta(key = "scope", value = "thread")
      }
)
public class QuestHolder {

   private static final ThreadLocal<SuperQuest> THREAD_LOCAL_QUEST = new ThreadLocal<>();


   private QuestHolder() {
   }

   /**
    * Sets the current test execution context.
    *
    * @param quest the {@code SuperQuest} instance representing the current test context.
    */
   @Pandora(description = "Bind the given SuperQuest to the current thread so framework components can access it.")
   public static void set(
         @Pandora(description = "The SuperQuest instance to bind to the current thread.")
         SuperQuest quest
   ) {
      THREAD_LOCAL_QUEST.set(quest);
   }

   /**
    * Retrieves the current test execution context.
    *
    * @return the {@code SuperQuest} instance associated with the current thread, or {@code null} if not set.
    */
   @Pandora(description = "Get the SuperQuest bound to the current thread (or null if not set).")
   public static SuperQuest get() {
      return THREAD_LOCAL_QUEST.get();
   }

   /**
    * Clears the current test execution context from thread-local storage.
    */
   @Pandora(description = "Remove the SuperQuest from the current thread to avoid leaking context between tests.")
   public static void clear() {
      THREAD_LOCAL_QUEST.remove();
   }

}
