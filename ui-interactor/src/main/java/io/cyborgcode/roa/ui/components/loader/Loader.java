package io.cyborgcode.roa.ui.components.loader;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;

/**
 * Represents a loader or spinner element in a UI, providing methods to check its visibility
 * and to wait for it to appear or disappear.
 *
 * <p>Typical usage scenarios include verifying if a loader is displayed on the screen
 * and waiting until it finishes loading (is removed). Implementations are expected
 * to handle the actual waiting logic, using container or locator references, potentially
 * with explicit or implicit waits.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Interface for interacting with loader/spinner UI components. Provides methods to "
            + "check loader visibility and wait for loading states.",
      tags = {"ui", "component-contract", "loader"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component")
      }
)
public interface Loader {

   /**
    * Checks if the loader is visible within the specified container.
    *
    * @param container a {@link SmartWebElement} representing the container holding the loader.
    * @return true if the loader is visible, false otherwise.
    */
   @Pandora(
         description = "Checks if the loader is currently visible within the specified container.",
         tags = {"component-contract", "loader"}
   )
   boolean isVisible(SmartWebElement container);

   /**
    * Checks if the loader identified by the given locator is visible.
    *
    * @param loaderLocator a {@link By} locator used to find the loader element.
    * @return true if the loader is visible, false otherwise.
    */
   @Pandora(
         description = "Checks if the loader is currently visible using the specified locator.",
         tags = {"component-contract", "loader"}
   )
   boolean isVisible(By loaderLocator);

   /**
    * Waits for the loader in the specified container to become visible within a given timeout.
    *
    * @param container    the container element holding the loader.
    * @param secondsShown the maximum number of seconds to wait for the loader to appear.
    * @throws RuntimeException if the loader is not shown within the specified timeout.
    */
   @Pandora(
         description = "Waits for the loader to become visible within the specified container and timeout.",
         tags = {"component-contract", "loader"}
   )
   void waitToBeShown(SmartWebElement container, int secondsShown);

   /**
    * Waits for a loader (implicitly identified) to become visible within a given timeout.
    *
    * @param secondsShown the maximum number of seconds to wait for the loader to appear.
    * @throws RuntimeException if the loader is not shown within the specified timeout.
    */
   @Pandora(
         description = "Waits for a loader to become visible on the page within the specified timeout.",
         tags = {"component-contract", "loader"}
   )
   void waitToBeShown(int secondsShown);

   /**
    * Waits for the loader identified by the given locator to become visible within a given timeout.
    *
    * @param loaderLocator the {@link By} locator used to find the loader element.
    * @param secondsShown  the maximum number of seconds to wait for the loader to appear.
    * @throws RuntimeException if the loader is not shown within the specified timeout.
    */
   @Pandora(
         description = "Waits for the loader identified by the given locator to become visible within "
               + "the specified timeout.",
         tags = {"component-contract", "loader"}
   )
   void waitToBeShown(By loaderLocator, int secondsShown);

   /**
    * Waits for the loader in the specified container to be removed within a given timeout.
    *
    * @param container      the container element holding the loader.
    * @param secondsRemoved the maximum number of seconds to wait for the loader to disappear.
    * @throws RuntimeException if the loader is not removed within the specified timeout.
    */
   @Pandora(
         description = "Waits for the loader within the specified container to be removed within the given timeout.",
         tags = {"component-contract", "loader"}
   )
   void waitToBeRemoved(SmartWebElement container, int secondsRemoved);

   /**
    * Waits for a loader (implicitly identified) to be removed within a given timeout.
    *
    * @param secondsRemoved the maximum number of seconds to wait for the loader to disappear.
    * @throws RuntimeException if the loader is not removed within the specified timeout.
    */
   @Pandora(
         description = "Waits for a loader on the page to be removed within the specified timeout.",
         tags = {"component-contract", "loader"}
   )
   void waitToBeRemoved(int secondsRemoved);

   /**
    * Waits for the loader identified by the given locator to be removed within a given timeout.
    *
    * @param loaderLocator  the {@link By} locator used to find the loader element.
    * @param secondsRemoved the maximum number of seconds to wait for the loader to disappear.
    * @throws RuntimeException if the loader is not removed within the specified timeout.
    */
   @Pandora(
         description = "Waits for the loader identified by the given locator to be removed within "
               + "the specified timeout.",
         tags = {"component-contract", "loader"}
   )
   void waitToBeRemoved(By loaderLocator, int secondsRemoved);
}

