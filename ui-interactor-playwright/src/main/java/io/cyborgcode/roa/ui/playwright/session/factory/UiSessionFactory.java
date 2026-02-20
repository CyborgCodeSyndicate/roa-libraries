package io.cyborgcode.roa.ui.playwright.session.factory;

import io.cyborgcode.roa.ui.exceptions.UiInteractionException;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.session.UISession;
import io.cyborgcode.roa.ui.playwright.session.base.BrowserProvider;
import io.cyborgcode.roa.ui.playwright.session.config.SessionConfig;
import io.cyborgcode.roa.ui.playwright.session.providers.ChromiumBrowserProvider;
import io.cyborgcode.roa.ui.playwright.session.providers.FirefoxBrowserProvider;
import io.cyborgcode.roa.ui.playwright.session.providers.WebKitBrowserProvider;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Factory class for creating {@link UISession} instances.
 *
 * <p>This factory manages registered {@link BrowserProvider} instances and creates
 * {@link UISession} objects based on the specified browser type and configuration.
 *
 * <p>By default, the factory registers providers for Chromium, Firefox, and WebKit.
 * Additional providers can be registered dynamically using {@link #registerProvider(BrowserProvider)}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class UiSessionFactory {

   private static final Map<String, BrowserProvider> BROWSER_PROVIDERS = new ConcurrentHashMap<>();

   static {
      registerProvider(new ChromiumBrowserProvider());
      registerProvider(new FirefoxBrowserProvider());
      registerProvider(new WebKitBrowserProvider());
   }

   private UiSessionFactory() {
   }

   /**
    * Registers a {@link BrowserProvider} for use in session creation.
    *
    * @param provider The browser provider to register.
    */
   public static void registerProvider(final BrowserProvider provider) {
      BROWSER_PROVIDERS.put(provider.getBrowserType().trim().toUpperCase(), provider);
      LogUi.info("Registered browser provider: '{}'", provider.getBrowserType());
   }

   /**
    * Creates a new {@link UISession} for the specified browser type using the given configuration.
    *
    * @param type   The browser type (e.g., "CHROMIUM", "FIREFOX", "WEBKIT").
    * @param config The session configuration.
    * @return A fully initialized {@link UISession}.
    * @throws IllegalArgumentException If no provider is registered for the given browser type.
    * @throws UiInteractionException   If session creation fails.
    */
   public static UISession createSession(final String type, final SessionConfig config) {
      final BrowserProvider provider = Optional.ofNullable(BROWSER_PROVIDERS.get(type.trim().toUpperCase()))
            .orElseThrow(() -> new IllegalArgumentException("No browser provider registered for type: " + type));

      try {
         return new UiSessionCreator().createSession(config, provider);
      } catch (Exception e) {
         throw new UiInteractionException("Failed to create UISession for browser type: " + type, e);
      }
   }

   /**
    * Creates a new {@link UISession} for the specified browser type using default configuration.
    *
    * @param type The browser type (e.g., "CHROMIUM", "FIREFOX", "WEBKIT").
    * @return A fully initialized {@link UISession}.
    */
   public static UISession createSession(final String type) {
      return createSession(type, SessionConfig.builder().build());
   }

}
