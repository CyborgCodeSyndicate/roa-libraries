package io.cyborgcode.roa.framework.retry;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A concrete implementation of {@link RetryCondition} for flexible retry logic.
 *
 * <p>This class defines how a particular retry condition is formed by pairing
 * a {@link Function} (producing the value to be tested) with a {@link Predicate}
 * (evaluating whether the produced value meets the desired condition).
 *
 * <p>Instances of this class are typically created with references to the actual
 * service or context that will be invoked repeatedly, along with the success
 * criteria that must be satisfied for the retry to end.
 *
 * @param <T> The type of value produced by the {@code function} and evaluated by the {@code condition}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Concrete RetryCondition implementation that pairs a "
            + "value-producing function with a success predicate. "
            + "Used by retry utilities to repeatedly execute an operation "
            + "until the predicate passes or a timeout occurs.",
      tags = {"framework", "retry"},
      creation = CreationKind.CONSTRUCTOR
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "retry-condition-impl"),
         @PandoraOptions.Meta(key = "implements", value = "RetryCondition")
      }
)
public class RetryConditionImpl<T> implements RetryCondition<T> {

   private final Function<Object, T> function;
   private final Predicate<T> condition;

   /**
    * Constructs a new {@code RetryConditionImpl} with the specified function and condition.
    *
    * @param function  The function that produces a value of type {@code T} for evaluation.
    * @param condition The predicate that tests the produced value for success.
    */
   public RetryConditionImpl(final Function<Object, T> function, final Predicate<T> condition) {
      this.function = function;
      this.condition = condition;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   @Pandora(
         description = "Returns the function executed on each retry attempt "
               + "to produce the value that will be evaluated."
   )
   public Function<Object, T> function() {
      return function;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   @Pandora(
         description = "Returns the predicate that decides whether the latest "
               + "produced value is acceptable (stop retry) or not (keep retrying)."
   )
   public Predicate<T> condition() {
      return condition;
   }

}
