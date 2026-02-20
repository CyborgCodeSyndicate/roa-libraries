package io.cyborgcode.roa.ui.config;

import io.cyborgcode.roa.ui.config.UiConfigCore;
import org.aeonbits.owner.ConfigCache;

/**
 * Singleton holder for managing the {@link UiConfig} instance.
 *
 * <p>This class ensures a single shared instance of the Playwright configuration throughout the application.
 * The configuration is lazily initialized using {@link ConfigCache} for efficient retrieval.
 *
 * <p>Benefits:
 * <ul>
 *     <li>Prevents multiple instantiations of the {@link UiConfig} interface.</li>
 *     <li>Ensures consistent configuration values throughout the test execution.</li>
 *     <li>Leverages OWNER library caching for improved performance.</li>
 * </ul>
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class UiConfigHolderCore {

   private UiConfigHolderCore() {
   }

   /**
    * Singleton instance of the Playwright configuration.
    */
   private static UiConfigCore config;

   /**
    * Retrieves the global {@link UiConfigCore} instance.
    *
    * <p>If the configuration has not been initialized, it will be created using {@link ConfigCache}.
    *
    * @return The singleton instance of {@link UiConfigCore}.
    */
   public static UiConfigCore getUiConfig() {
      if (config == null) {
         config = ConfigCache.getOrCreate(UiConfigCore.class);
      }
      return config;
   }

}
