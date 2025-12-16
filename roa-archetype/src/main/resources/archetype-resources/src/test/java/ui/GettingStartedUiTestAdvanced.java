package ${package}.ui;

import io.cyborgcode.roa.ui.annotations.AuthenticateViaUi;
import io.cyborgcode.roa.framework.annotation.Craft;
import io.cyborgcode.roa.framework.annotation.Journey;
import io.cyborgcode.roa.framework.annotation.JourneyData;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.ui.annotations.UI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Description;

#set( $ui = $uiComponents.toUpperCase() )
#if( $ui.contains("BUTTON") )
import ${package}.ui.elements.ButtonFields;
#end
#if( $ui.contains("INPUT") )
import ${package}.ui.elements.InputFields;
#end
#if( $ui.contains("SELECT") )
import ${package}.ui.elements.SelectFields;
#end
import ${package}.ui.authentication.ExampleCredentials;
import ${package}.ui.authentication.ExampleAppUiLogin;
import ${package}.ui.interceptor.RequestsInterceptor;
import ${package}.ui.model.ExampleTableModel;
import ${package}.common.data.creator.DataCreator;
import ${package}.common.preconditions.Preconditions;
import static ${package}.common.base.Rings.RING_OF_CUSTOM;

import java.util.List;

import static io.cyborgcode.roa.ui.config.UiConfigHolder.getUiConfig;
import static ${package}.common.base.Rings .*;

/**
 * UI test demonstrating authentication, data injection, and element interactions.
 *
 * <p>This class shows how to:
 * <ul>
 *   <li>Authenticate automatically using {@code @AuthenticateViaUi}</li>
 *   <li>Inject test data models using {@code @Craft}</li>
 *   <li>Chain multiple UI interactions fluently</li>
 * </ul>
 */
@UI
@DisplayName("Getting started UI test class")
public class GettingStartedUiTestAdvanced extends BaseQuest {

    @Test
    @Regression
    @Description("Using automatic UI authentication through @AuthenticateViaUi and combining journeys/preconditions" +
            " and crafted data")
    @AuthenticateViaUi(credentials = ExampleCredentials.class, type = ExampleAppUiLogin.class)
    @Journey("" // Add preconditions: value = Preconditions.Data.EXAMPLE_PRECONDITION
    )
    void exampleUITest(Quest quest,
                       @Craft(model = DataCreator.Data.EXAMPLE_TABLE_MODEL) ExampleTableModel model) {

        // Login is handled automatically by @AuthenticateViaUi
        // Data is injected via @Craft
        
        quest.use(RING_OF_UI)
                .getNavigation().navigate(getUiConfig().baseUrl())
#if( $ui.contains("BUTTON") )
                .getButtonField().click(ButtonFields.LOGIN_BUTTON)
#end
#if( $ui.contains("INPUT") )
                .getInputField().insert(InputFields.USERNAME, "example")
#end
#if( $ui.contains("SELECT") )
                // .getSelectField().selectOption(SelectFields.GENERIC_SELECT, model.getExampleSelection())
#end
                // Validate table or other elements:
                // .getTable().readTable(Tables.ITEMS)
                // .getTable().validate(...) 
                .complete();
    }

    @Test
    @Regression
    void customFlowDemonstration(Quest quest) {

        /**
         * Example of integrated custom flow execution.
         */
//        quest.use(RING_OF_CUSTOM)
//                // .exampleFlow()
//                .complete();
    }
}
