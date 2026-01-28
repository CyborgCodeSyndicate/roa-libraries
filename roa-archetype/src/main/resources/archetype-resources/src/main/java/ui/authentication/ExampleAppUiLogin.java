package ${package}.ui.authentication;

import io.cyborgcode.roa.ui.authentication.BaseLoginClient;
import io.cyborgcode.roa.ui.service.fluent.UiServiceFluent;
import ${package}.ui.elements.ButtonFields;
import ${package}.ui.elements.InputFields;
import org.openqa.selenium.By;

// TODO implement login logic on ui side and return indicator element for successful login
public class ExampleAppUiLogin extends BaseLoginClient {

    @Override
    protected <T extends UiServiceFluent<?>> void loginImpl(
            T uiService,
            String username,
            String password
    ) {
        // Example:
        //      uiService.getNavigation().navigate("https://example.com/login")
        //         .getInputField().insert(InputFields.USERNAME, username)
        //         .getInputField().insert(InputFields.PASSWORD, password)
        //         .getButtonField().click(ButtonFields.LOGIN_BUTTON);
    }

    @Override
    protected By successfulLoginElementLocator() {
        // Example:
        // return By.cssSelector("body");
        return null;
    }
}
