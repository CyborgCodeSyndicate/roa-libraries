package io.cyborgcode.roa.ui.playwright.authentication;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.Cookie;
import io.cyborgcode.roa.ui.authentication.BaseLoginClientCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.SuperUiServiceFluentCore;
import java.util.List;

/**
 * Abstract base class for Playwright-based UI authentication clients.
 *
 * <p>This class provides the Playwright-specific implementations for capturing and restoring
 * browser session data (cookies and local storage) through the {@link com.microsoft.playwright.BrowserContext}
 * and {@link Page} APIs. Subclasses must implement {@link #loginImpl} to define the actual login
 * interaction and {@link #successfulLoginSelector()} to provide a CSS selector that indicates
 * a successful login.
 *
 * <p>Unlike the Selenium adapter, Playwright captures cookies as plain data objects, so the
 * original session does not need to remain alive for caching purposes. The
 * {@link #markDriverAsKept(SuperUiServiceFluentCore)} method is therefore a no-op.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class BaseLoginClient extends BaseLoginClientCore<List<Cookie>, PwBy, Page, PwElement> {

   /**
    * JavaScript expression to capture local storage as a JSON string.
    */
   private static final String GET_LOCAL_STORAGE = "JSON.stringify(window.localStorage)";

   /**
    * JavaScript function to restore local storage from a JSON string argument.
    */
   private static final String UPDATE_LOCAL_STORAGE =
         "json => { let data = JSON.parse(json); for (let key in data) {"
               + " window.localStorage.setItem(key, data[key]); } }";


   /**
    * Waits for the element identified by {@link #successfulLoginSelector()} to appear on the page,
    * indicating a successful login.
    *
    * @param uiService The UI service used for browser interactions.
    */
   @Override
   protected void waitForSuccessfulLogin(SuperUiServiceFluentCore<PwBy, Page, PwElement, ?> uiService) {
      getPage(uiService).waitForSelector(successfulLoginSelector());
   }

   /**
    * Retrieves the current URL from the Playwright page.
    *
    * @param uiService The UI service used for browser interactions.
    * @return The current browser URL.
    */
   @Override
   protected String getCurrentUrl(SuperUiServiceFluentCore<PwBy, Page, PwElement, ?> uiService) {
      return getPage(uiService).url();
   }

   /**
    * Captures all cookies from the current browser context.
    *
    * @param uiService The UI service used for browser interactions.
    * @return A list of cookies from the current browser context.
    */
   @Override
   protected List<Cookie> captureCookies(SuperUiServiceFluentCore<PwBy, Page, PwElement, ?> uiService) {
      return getPage(uiService).context().cookies();
   }

   /**
    * Captures local storage data from the current page as a JSON string.
    *
    * @param uiService The UI service used for browser interactions.
    * @return The JSON representation of local storage data.
    */
   @Override
   protected String captureLocalStorage(SuperUiServiceFluentCore<PwBy, Page, PwElement, ?> uiService) {
      return (String) getPage(uiService).evaluate(GET_LOCAL_STORAGE);
   }

   /**
    * Clears all cookies from the current browser context.
    *
    * @param uiService The UI service used for browser interactions.
    */
   @Override
   protected void clearCookies(SuperUiServiceFluentCore<PwBy, Page, PwElement, ?> uiService) {
      getPage(uiService).context().clearCookies();
   }

   /**
    * Restores previously captured cookies into the current browser context.
    *
    * @param uiService The UI service used for browser interactions.
    * @param cookies   The list of cookies to restore.
    */
   @Override
   protected void restoreCookies(SuperUiServiceFluentCore<PwBy, Page, PwElement, ?> uiService, List<Cookie> cookies) {
      getPage(uiService).context().addCookies(cookies);
   }

   /**
    * Restores local storage data into the current page by evaluating JavaScript.
    *
    * @param uiService        The UI service used for browser interactions.
    * @param localStorageJson The JSON representation of local storage data to restore.
    */
   @Override
   protected void restoreLocalStorage(SuperUiServiceFluentCore<PwBy, Page, PwElement, ?> uiService, String localStorageJson) {
      getPage(uiService).evaluate(UPDATE_LOCAL_STORAGE, localStorageJson);
   }

   /**
    * Navigates the Playwright page to the specified URL.
    *
    * @param uiService The UI service used for browser interactions.
    * @param url       The URL to navigate to.
    */
   @Override
   protected void navigateTo(SuperUiServiceFluentCore<PwBy, Page, PwElement, ?> uiService, String url) {
      getPage(uiService).navigate(url);
   }

   /**
    * No-op for Playwright.
    *
    * <p>Unlike Selenium, Playwright captures cookies as plain data objects through the
    * {@link com.microsoft.playwright.BrowserContext} API, so the original session does not
    * need to remain alive for other sessions to reuse the cached credentials.
    *
    * @param uiService The UI service used for browser interactions.
    */
   @Override
   protected void markDriverAsKept(SuperUiServiceFluentCore<PwBy, Page, PwElement, ?> uiService) {
      // No-op: Playwright cookies are captured as data; session does not need to stay alive.
   }


   /**
    * Provides a CSS selector for an element that indicates a successful login.
    *
    * <p>This method should return a selector that identifies an element which only
    * appears after a successful login, such as a dashboard element or a user profile menu.
    *
    * @return A CSS selector string for the post-login verification element.
    */
   protected abstract String successfulLoginSelector();


   /**
    * Extracts the Playwright {@link Page} from the UI service.
    *
    * @param uiService The UI service whose driver is a Playwright {@link Page}.
    * @return The Playwright {@link Page} instance.
    */
   private Page getPage(SuperUiServiceFluentCore<PwBy, Page, PwElement, ?> uiService) {
      return uiService.getDriver();
   }

}
