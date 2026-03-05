package io.cyborgcode.roa.ui.playwright.util.strategy;


import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.util.strategy.StrategyGeneratorService;
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
public class StrategyGenerator {

   private static final StrategyGeneratorService<PwElement> STRATEGY_GENERATOR_SERVICE = new StrategyGeneratorService<>();

   private StrategyGenerator() {
   }

   /**
    * Selects and returns a random element from the given list.
    *
    * @param elements The list of {@link PwElement} from which an element is to be selected.
    * @return A randomly selected {@link PwElement} from the list.
    * @throws IllegalArgumentException if the list is null or empty.
    */
   public static PwElement getRandomElementFromElements(List<PwElement> elements) {
      return STRATEGY_GENERATOR_SERVICE.getRandomElementFromElements(elements);
   }

   /**
    * Selects and returns the first element from the given list.
    *
    * @param elements The list of {@link PwElement} from which the first element is to be selected.
    * @return The first {@link PwElement} in the list.
    * @throws IllegalArgumentException if the list is null or empty.
    */
   public static PwElement getFirstElementFromElements(List<PwElement> elements) {
      return STRATEGY_GENERATOR_SERVICE.getFirstElementFromElements(elements);
   }

   /**
    * Selects and returns the last element from the given list.
    *
    * @param elements The list of {@link PwElement} from which the last element is to be selected.
    * @return The last {@link PwElement} in the list.
    * @throws IllegalArgumentException if the list is null or empty.
    */
   public static PwElement getLastElementFromElements(List<PwElement> elements) {
      return STRATEGY_GENERATOR_SERVICE.getFirstElementFromElements(elements);
   }
}
