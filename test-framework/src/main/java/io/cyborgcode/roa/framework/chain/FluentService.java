package io.cyborgcode.roa.framework.chain;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.log.LogQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import io.cyborgcode.roa.framework.retry.RetryCondition;
import io.cyborgcode.roa.validator.core.AssertionResult;
import io.cyborgcode.utilities.reflections.RetryUtils;
import io.qameta.allure.Allure;
import java.time.Duration;
import java.util.List;
import org.assertj.core.api.Assertions;

/**
 * Provides a base implementation for fluent service interactions.
 *
 * <p>This class serves as a foundation for fluent service execution,
 * offering retry mechanisms, validation handling, and structured
 * test execution flow.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Base implementation for all fluent Ring services. "
            + "Holds the current SuperQuest context, provides 'drop()' to return back to the Quest chain, "
            + "and offers shared helpers for retries and assertion-result validation (soft vs hard).",
      tags = {"framework"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/general-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "fluent-service-base"),
         @PandoraOptions.Meta(key = "role", value = "ring-foundation")
      }
)
public class FluentService implements FluentChain {

   /**
    * The {@code SuperQuest} instance managing the test execution context.
    */
   protected SuperQuest quest;

   /**
    * Retrieves the original quest instance for further execution.
    *
    * @return The original {@code Quest} instance.
    */
   @Pandora(
         description = "Drop the currently active Ring (fluent service) and return to the original Quest chain "
               + "so you can switch rings or finish the flow."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   @Override
   public Quest drop() {
      LogQuest.info("The quest has dropped the ring.");
      return quest.getOriginal();
   }

   /**
    * Executes a retry mechanism until a specified condition is met.
    *
    * @param retryCondition The retry condition to be checked.
    * @param maxWait        The maximum duration to wait before giving up.
    * @param retryInterval  The interval between retries.
    * @param service        The service instance used in the retry condition.
    * @param <T>            The type used in the retry condition function.
    * @return The current {@code FluentService} instance for method chaining.
    */
   @Pandora(
         description = "Execute a retry loop until the provided RetryCondition is satisfied or maxWait is reached. "
               + "Evaluates the condition by invoking retryCondition.function() against the given service instance."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   protected <T> FluentService retryUntil(@Pandora(description = "RetryCondition that defines how to compute "
                                                + "a value from the service and how to evaluate success.")
                                          RetryCondition<T> retryCondition,
                                          @Pandora(description = "Maximum total duration to "
                                                + "keep retrying before failing.")
                                          Duration maxWait,
                                          @Pandora(description = "Delay between retry attempts.")
                                          Duration retryInterval,
                                          @Pandora(description = "Service instance passed into "
                                                + "retryCondition.function().apply(service). "
                                                + "Typically the current Ring/RestService.")
                                          Object service) {
      RetryUtils.retryUntil(maxWait, retryInterval, () -> retryCondition.function().apply(service),
            retryCondition.condition());
      return this;
   }

   /**
    * Assigns a {@code SuperQuest} instance to this service.
    *
    * @param quest The {@code SuperQuest} instance to be assigned.
    */
   @Pandora(
         description = "Attach the current SuperQuest execution context to this fluent service. "
               + "Called by the framework during Quest->Ring initialization."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   protected void setQuest(@Pandora(description = "Active SuperQuest for the current test run, "
         + "providing storage and soft-assertions.") final SuperQuest quest) {
      this.quest = quest;
   }

   /**
    * Performs validation on a list of assertion results.
    *
    * <p>This method logs, reports, and evaluates assertions, supporting both
    * soft and hard validations.
    *
    * @param assertionResults The list of assertion results to be validated.
    */
   @Pandora(
         description = "Evaluate AssertionResult items produced by validators. "
               + "Each result is logged and reported to Allure; soft "
               + "assertions are collected in quest soft-assertions, "
               + "hard assertions fail immediately."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   @SuppressWarnings("java:S5960")
   protected void validation(List<AssertionResult<Object>> assertionResults) {
      assertionResults.forEach(assertionResult -> {
         String message = assertionResult.toString();
         LogQuest.validation(message);
         Allure.step(message);

         boolean isPassed = assertionResult.isPassed();
         if (assertionResult.isSoft()) {
            quest.getSoftAssertions()
                  .assertThat(isPassed)
                  .as(message)
                  .isTrue();
         } else {
            Assertions.assertThat(isPassed)
                  .as(message)
                  .isTrue();
         }
      });
   }

   /**
    * Hook method for initializing any required setup after a quest is configured.
    *
    * <p>This method can be overridden by subclasses to provide custom setup logic.
    */
   @Pandora(
         description = "Optional lifecycle hook invoked after the quest is attached to the service. "
               + "Override in specific Rings to run custom initialization once the SuperQuest context is available."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   protected void postQuestSetupInitialization() {
      //can override for specific services
   }

}
