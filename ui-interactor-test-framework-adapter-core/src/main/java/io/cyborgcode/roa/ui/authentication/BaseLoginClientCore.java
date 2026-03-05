package io.cyborgcode.roa.ui.authentication;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.exceptions.AuthenticationUiException;
import io.cyborgcode.roa.ui.service.fluent.SuperUiServiceFluentCore;
import io.cyborgcode.roa.ui.service.fluent.UiServiceFluentCore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;

/**
 * Abstract base class for implementing UI-based authentication clients.
 *
 * <p>This class provides a mechanism for logging in through a UI service, caching session data,
 * and restoring sessions using stored cookies and local storage data. It contains all the
 * framework-agnostic caching and synchronization logic. Framework-specific operations
 * (cookie handling, navigation, login verification) are delegated to abstract template methods
 * that must be implemented by subclasses for each UI framework (Selenium, Playwright, etc.).
 *
 * <p>The authentication flow consists of:
 * <ul>
 *     <li>Performing login through UI interaction.</li>
 *     <li>Waiting for a successful login indication.</li>
 *     <li>Caching session cookies and local storage data for reuse.</li>
 *     <li>Restoring an existing session if caching is enabled.</li>
 * </ul>
 *
 * @param <C> The type used to represent cookies for the specific UI framework.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class BaseLoginClientCore<C, L, D, E extends BaseUiElement> implements LoginClient<L, D, E> {

   /**
    * A thread-safe map to store user sessions based on login credentials.
    */
   private static final Map<LoginKey, SessionInfo<?>> userLoginMap = new ConcurrentHashMap<>();

   /**
    * Stores the URL obtained after a successful login for each session.
    *
    * <p>This allows the system to directly navigate to the appropriate URL when session caching is used.
    * The map key is a {@link LoginKey} containing the username, password, and login type information.
    */
   private static final Map<LoginKey, String> urlAfterLoggingMap = new ConcurrentHashMap<>();

   /**
    * Cleanup actions to execute when the launcher session closes.
    *
    * <p>Each framework adapter registers its own cleanup logic (e.g., quitting a WebDriver,
    * closing a Playwright session) via {@link #markDriverAsKept(SuperUiServiceFluentCore)}.
    */
   @Getter
   @SuppressFBWarnings("MS_EXPOSE_REP")
   private static final List<Runnable> sessionCleanupActions = Collections.synchronizedList(new ArrayList<>());

   /**
    * Tracks which driver/page instances are marked as "kept for session".
    *
    * <p>Framework-specific extensions check this set to decide whether to close the driver
    * after a test completes. Kept drivers are only closed at the end of the full run via
    * {@link #sessionCleanupActions}.
    */
   @Getter
   @SuppressFBWarnings("MS_EXPOSE_REP")
   private static final Set<Object> keptDrivers = ConcurrentHashMap.newKeySet();


   /**
    * Logs in the user and optionally caches session data for reuse.
    *
    * @param uiService The UI service used for login interactions.
    * @param username  The username of the user.
    * @param password  The password of the user.
    * @param cache     Whether to cache the session for future reuse.
    */
   @Override
   @SuppressWarnings("unchecked")
   @SuppressFBWarnings("JLM_JSR166_UTILCONCURRENT_MONITORENTER")
   public void login(final SuperUiServiceFluentCore<L, D, E, ?> uiService, final String username,
                     final String password, final boolean cache) {
      LoginKey loginKey = new LoginKey(username, password,
            (Class<? extends BaseLoginClientCore<C, L, D, E>>) this.getClass());

      if (!cache) {
         performLoginAndCache(uiService, loginKey, username, password);
      } else {
         synchronized (userLoginMap) {
            if (userLoginMap.get(loginKey) == null) {
               performLoginAndCache(uiService, loginKey, username, password);
            } else {
               restoreSession(uiService, userLoginMap.get(loginKey), urlAfterLoggingMap.get(loginKey));
            }
         }
      }
   }


   /**
    * Retrieves cached authentication session info if available.
    *
    * @param loginKey The key representing the login credentials.
    * @return An {@link Optional} containing the session information if found.
    */
   @SuppressWarnings("unchecked")
   public Optional<SessionInfo<C>> getAuthentication(final LoginKey loginKey) {
      return Optional.ofNullable((SessionInfo<C>) userLoginMap.get(loginKey));
   }


   /**
    * Performs login and caches session cookies and local storage data.
    *
    * @param uiService The UI service used for login interactions.
    * @param loginKey  The key representing the login credentials.
    * @param username  The username of the user.
    * @param password  The password of the user.
    */
   private void performLoginAndCache(SuperUiServiceFluentCore<L, D, E, ?> uiService, LoginKey loginKey,
                                     String username, String password) {
      loginImpl(uiService, username, password);
      markDriverAsKept(uiService);

      try {
         waitForSuccessfulLogin(uiService);
      } catch (Exception e) {
         throw new AuthenticationUiException("Logging in was not successful", e);
      }

      urlAfterLoggingMap.put(loginKey, getCurrentUrl(uiService));
      C cookies = captureCookies(uiService);
      String localStorage = captureLocalStorage(uiService);
      userLoginMap.put(loginKey, new SessionInfo<>(cookies, localStorage));
   }


   /**
    * Restores a previously cached session by injecting cookies and local storage data.
    *
    * @param uiService       The UI service used for browser interactions.
    * @param sessionInfo     The stored session information.
    * @param urlAfterLogging The URL to navigate to before restoring session data.
    */
   @SuppressWarnings("unchecked")
   private void restoreSession(SuperUiServiceFluentCore<L, D, E, ?> uiService, SessionInfo<?> sessionInfo,
                               String urlAfterLogging) {
      try {
         navigateTo(uiService, urlAfterLogging);
         clearCookies(uiService);
         restoreCookies(uiService, (C) sessionInfo.getCookies());
         restoreLocalStorage(uiService, sessionInfo.getLocalStorage());
         navigateTo(uiService, urlAfterLogging);
      } catch (Exception e) {
         throw new AuthenticationUiException("Restoring session was not successful", e);
      }

      try {
         waitForSuccessfulLogin(uiService);
      } catch (Exception e) {
         throw new AuthenticationUiException("Logging in was not successful", e);
      }
   }


   // ── Template methods (framework-specific) ────────────────────────────

   /**
    * Performs the actual login operation.
    *
    * <p>This method should be implemented by subclasses to define the specific UI interactions
    * required to log in.
    *
    * @param uiService The UI service for interacting with the login page.
    * @param username  The username for authentication.
    * @param password  The password for authentication.
    * @param <T>       The type of UI service being used.
    */
   protected abstract <T extends UiServiceFluentCore<L, D, E, ?>> void loginImpl(
         T uiService, String username, String password);

   /**
    * Waits for the UI to indicate a successful login.
    *
    * <p>Selenium implementations typically use {@code ExpectedConditions.presenceOfElementLocated},
    * while Playwright implementations use {@code page.waitForSelector}.
    *
    * @param uiService The UI service used for browser interactions.
    */
   protected abstract void waitForSuccessfulLogin(SuperUiServiceFluentCore<L, D, E, ?> uiService);

   /**
    * Retrieves the current URL from the browser.
    *
    * @param uiService The UI service used for browser interactions.
    * @return The current browser URL.
    */
   protected abstract String getCurrentUrl(SuperUiServiceFluentCore<L, D, E, ?> uiService);

   /**
    * Captures cookies from the current browser session.
    *
    * @param uiService The UI service used for browser interactions.
    * @return The captured cookies.
    */
   protected abstract C captureCookies(SuperUiServiceFluentCore<L, D, E, ?> uiService);

   /**
    * Captures local storage data from the current browser session as a JSON string.
    *
    * @param uiService The UI service used for browser interactions.
    * @return The JSON representation of local storage data.
    */
   protected abstract String captureLocalStorage(SuperUiServiceFluentCore<L, D, E, ?> uiService);

   /**
    * Deletes all cookies from the current browser session.
    *
    * @param uiService The UI service used for browser interactions.
    */
   protected abstract void clearCookies(SuperUiServiceFluentCore<L, D, E, ?> uiService);

   /**
    * Restores previously captured cookies into the current browser session.
    *
    * @param uiService The UI service used for browser interactions.
    * @param cookies   The cookies to restore.
    */
   protected abstract void restoreCookies(SuperUiServiceFluentCore<L, D, E, ?> uiService, C cookies);

   /**
    * Restores local storage data into the current browser session.
    *
    * @param uiService        The UI service used for browser interactions.
    * @param localStorageJson The JSON representation of local storage data to restore.
    */
   protected abstract void restoreLocalStorage(SuperUiServiceFluentCore<L, D, E, ?> uiService,
                                               String localStorageJson);

   /**
    * Navigates the browser to the specified URL.
    *
    * @param uiService The UI service used for browser interactions.
    * @param url       The URL to navigate to.
    */
   protected abstract void navigateTo(SuperUiServiceFluentCore<L, D, E, ?> uiService, String url);

   /**
    * Marks the driver/page as "kept for session" and registers a cleanup action.
    *
    * <p>The implementation should:
    * <ol>
    *     <li>Add the driver/page to {@link #keptDrivers} so the extension does not close it after the test.</li>
    *     <li>Register a {@link Runnable} in {@link #sessionCleanupActions} to close it at end-of-run.</li>
    * </ol>
    *
    * <p>For Playwright, this may be a no-op since cookies are captured as plain data
    * and the session does not need to stay alive.
    *
    * @param uiService The UI service used for browser interactions.
    */
   protected abstract void markDriverAsKept(SuperUiServiceFluentCore<L, D, E, ?> uiService);

}
