package io.cyborgcode.roa.ui.util.strategy;

import java.security.SecureRandom;
import java.util.List;

/**
 * Utility class for applying different selection strategies to a list of elements.
 *
 * <p>This class provides methods to retrieve elements based on predefined selection strategies,
 * such as random selection, first element selection, and last element selection.
 *
 * <p>These methods are primarily used in UI components that allow multiple selections,
 * enabling automation frameworks to define element selection dynamically.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class StrategyGeneratorService<E> {

   private static final SecureRandom RANDOM = new SecureRandom();

   /**
    * Selects and returns a random element from the given list.
    *
    * @param elements The list of {@link E} from which an element is to be selected.
    * @return A randomly selected {@link E} from the list.
    * @throws IllegalArgumentException if the list is null or empty.
    */
   public E getRandomElementFromElements(List<E> elements) {
      validateInput(elements);
      return elements.get(RANDOM.nextInt(elements.size()));
   }

   /**
    * Selects and returns the first element from the given list.
    *
    * @param elements The list of {@link E} from which the first element is to be selected.
    * @return The first {@link E} in the list.
    * @throws IllegalArgumentException if the list is null or empty.
    */
   public E getFirstElementFromElements(List<E> elements) {
      validateInput(elements);
      return elements.get(0);
   }

   /**
    * Selects and returns the last element from the given list.
    *
    * @param elements The list of {@link E} from which the last element is to be selected.
    * @return The last {@link E} in the list.
    * @throws IllegalArgumentException if the list is null or empty.
    */
   public E getLastElementFromElements(List<E> elements) {
      validateInput(elements);
      return elements.get(elements.size() - 1);
   }

   /**
    * Validates that the input list is not null or empty.
    *
    * @param elements The list to validate.
    * @throws IllegalArgumentException if the list is null or empty.
    */
   private void validateInput(List<E> elements) {
      if (elements == null || elements.isEmpty()) {
         throw new IllegalArgumentException("Element list must not be null or empty.");
      }
   }
}
