package io.cyborgcode.roa.ui.authentication;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.service.fluent.SuperUiServiceFluentCore;

/**
 * Represents a UI-based authentication client.
 *
 * <p>Implementing classes are responsible for handling user login processes within the UI.
 * This interface defines a method for performing login operations, which may include
 * caching session details for reuse.
 *
 * <p>This interface is commonly implemented by {@link BaseLoginClientCore}, which provides
 * session management capabilities, including local storage and cookie persistence.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface LoginClient<L, D, E extends BaseUiElement> {

   /**
    * Logs in a user through a UI interaction.
    *
    * @param uiService The UI service used to interact with the login page.
    * @param username  The username of the user.
    * @param password  The password of the user.
    * @param cache     Whether to cache session data for reuse.
    */
   void login(SuperUiServiceFluentCore<L, D, E, ?> uiService, String username, String password, boolean cache);

}
