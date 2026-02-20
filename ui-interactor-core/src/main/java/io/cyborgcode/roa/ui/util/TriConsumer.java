package io.cyborgcode.roa.ui.util;

/**
 * Represents an operation that accepts three input arguments and performs an action.
 *
 * <p>This functional interface is similar to {@link java.util.function.BiConsumer}, but it
 * supports three parameters instead of two.
 *
 * @param <T> The type of the first argument.
 * @param <U> The type of the second argument.
 * @param <K> The type of the third argument.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@FunctionalInterface
public interface TriConsumer<T, U, K> {

   /**
    * Performs the operation given the specified inputs.
    *
    * @param t The first input argument.
    * @param u The second input argument.
    * @param k The third input argument.
    */
   void accept(T t, U u, K k);
}
