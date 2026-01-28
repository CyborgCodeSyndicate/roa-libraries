package io.cyborgcode.roa.framework.parameters;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import java.util.function.BiConsumer;

/**
 * Defines a contract for pre-test setup operations that need to be executed before a test method runs.
 *
 * <p>Implementations of this interface should define specific preconditions that must be fulfilled
 * before the test begins. These preconditions are executed as part of the {@code @PreQuest} setup
 * and typically involve initializing test data or configuring the test environment dynamically.
 *
 * <p>Each implementation is expected to define a unique execution logic that is invoked through
 * the {@link #journey()} method, which accepts the {@code SuperQuest} instance and additional parameters.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Pre-test journey (precondition) contract. "
            + "Implementations define setup logic that runs before the test body, "
            + "usually triggered by @Journey/@PreQuest and identified via an enum constant.",
      tags = {"framework", "precondition"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "pre-quest-journey")
      }
)
public interface PreQuestJourney<T extends Enum<T>> {

   /**
    * Defines the precondition execution logic.
    *
    * <p>This method accepts a {@link SuperQuest} instance and an array of objects representing
    * dynamic parameters required for precondition execution. The implementation should
    * process these inputs accordingly and modify the test state as needed.
    *
    * @return A {@link BiConsumer} that takes a {@link SuperQuest} instance and an array of parameters.
    */
   @Pandora(
         description = "Executable precondition logic. The framework invokes this "
               + "consumer with the current SuperQuest and the resolved journey arguments "
               + "(e.g., @JourneyData models, dynamic values), "
               + "allowing the journey to set up state and store artifacts."
   )
   BiConsumer<SuperQuest, Object[]> journey();

   /**
    * Returns the enumeration representation of the implementing precondition.
    *
    * <p>Each implementation should provide an enum representation to facilitate
    * easy lookup and execution of preconditions.
    *
    * @return An {@code Enum} representing the current precondition implementation.
    */
   @Pandora(
         description = "Enum constant that identifies this journey implementation. "
               + "Used for reflective discovery and lookup when executing @Journey/@PreQuest."
   )
   T enumImpl();

}
