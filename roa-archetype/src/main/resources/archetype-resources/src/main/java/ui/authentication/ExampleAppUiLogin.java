package ${package}.ui.authentication;

import io.cyborgcode.roa.ui.authentication.BaseLoginClient;
import io.cyborgcode.roa.ui.service.fluent.UiServiceFluent;
import org.openqa.selenium.By;

/**
 * Example UI login workflow used with the {@code @AuthenticateViaUi} annotation.
 * <p>
 * This class defines HOW to log in via the UI.
 * </p>
 */
public class ExampleAppUiLogin extends BaseLoginClient {

    @Override
    protected <T extends UiServiceFluent<?>> void loginImpl(
            T uiService,
            String username,
            String password
    ) {
        // Replace with your login steps
        // Example:
        // uiService.getNavigation().navigate("https://example.com/login");
        // uiService.getInputField().insert(InputFields.USERNAME, username);
        // uiService.getInputField().insert(InputFields.PASSWORD, password);
        // uiService.getButtonField().click(ButtonFields.LOGIN_BUTTON);
    }

    @Override
    protected By successfulLoginElementLocator() {
        // Replace with the element that appears after successful login
        return By.cssSelector("body");
    }
}
