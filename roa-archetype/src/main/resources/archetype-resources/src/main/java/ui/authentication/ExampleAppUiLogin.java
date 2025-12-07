package ${package}.ui.authentication;

import io.cyborgcode.roa.ui.authentication.BaseLoginClient;
import io.cyborgcode.roa.ui.service.fluent.UiServiceFluent;
import org.openqa.selenium.By;

/**
 * Example UI login workflow used with the {@code @AuthenticateViaUi} annotation.
 *
 * <p>Replace the UI flow here with real steps required to authenticate
 * in your application (enter username/password, click login, etc.).</p>
 */
public class ExampleAppUiLogin extends BaseLoginClient {

    @Override
    protected <T extends UiServiceFluent<?>> void loginImpl(
            T uiService,
            String username,
            String password
    ) {
        // TODO: Replace with real UI login logic
        // Example:
        // uiService.getNavigation().navigate("https://example.com/login");
        // uiService.getInputField().insert("usernameLocator", username);
        // uiService.getInputField().insert("passwordLocator", password);
        // uiService.getButtonField().click("loginButton");
    }

    @Override
    protected By successfulLoginElementLocator() {
        // TODO: Replace with an actual locator that appears after successful login
//        return By.cssSelector("body");
        return null;
    }
}
