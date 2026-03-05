package io.cyborgcode.roa.ui.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents session information for a logged-in user.
 *
 * <p>This class stores authentication-related data, including browser cookies and
 * local storage content, to enable session restoration without requiring re-login.
 * It is primarily used in {@link BaseLoginClientCore} for caching and restoring user sessions.
 *
 * <p>The type parameter {@code C} represents the cookie type used by a specific framework adapter
 * (e.g., {@code Set<Cookie>} for Selenium, {@code List<Cookie>} for Playwright).
 *
 * @param <C> The type used to represent cookies for the specific UI framework.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AllArgsConstructor
@Getter
public class SessionInfo<C> {

   /**
    * The cookies associated with the authenticated session.
    */
   private C cookies;

   /**
    * The JSON representation of local storage data associated with the session.
    */
   private String localStorage;

}
