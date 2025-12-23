package ${package}.ui.authentication;

import io.cyborgcode.roa.ui.authentication.BaseLoginClient;
import io.cyborgcode.roa.ui.service.fluent.UiServiceFluent;
import org.openqa.selenium.By;

public class ExampleAppUiLoginAI extends BaseLoginClient {

    @Override
    protected <T extends UiServiceFluent<?>> void loginImpl(
            T uiService,
            String username,
            String password
    ) {
    }

    @Override
    protected By successfulLoginElementLocator() {
        return null;
    }
}
