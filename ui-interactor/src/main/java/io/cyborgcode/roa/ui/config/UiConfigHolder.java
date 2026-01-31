package io.cyborgcode.roa.ui.config;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import org.aeonbits.owner.ConfigCache;

/**
 * Singleton holder for managing the {@link UiConfig} instance.
 *
 * <p>This class ensures a single shared instance of the UI configuration throughout the application.
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
@Pandora(
      description = "Singleton holder providing a lazily-initialized global UiConfig instance.",
      tags = {"ui", "config", "ui-config"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-config")
      }
)
public class UiConfigHolder {

   private UiConfigHolder() {
   }


   /**
    * Singleton instance of the UI configuration
    */
   private static UiConfig config;

   /**
    * Retrieves the global {@link UiConfig} instance.
    *
    * <p>If the configuration has not been initialized, it will be created using {@link ConfigCache}.
    *
    * @return The singleton instance of {@link UiConfig}.
    */
   @Pandora(
         description = "Retrieves the global UiConfig instance, creating it via ConfigCache if uninitialized.",
         tags = {"ui-config"}
   )
   public static UiConfig getUiConfig() {
      if (config == null) {
         config = ConfigCache.getOrCreate(UiConfig.class);
      }
      return config;
   }

}
