package ${package}.ui;

import io.qameta.allure.Description;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.ui.annotations.UI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

import static ${package}.common.base.Rings.RING_OF_UI;
import static ${package}.common.base.Rings.RING_OF_CUSTOM;
import static io.cyborgcode.roa.ui.config.UiConfigHolder.getUiConfig;

/**
 * Simple UI test example.
 * 
 * Replace elements and interactions with your own.
 */
@UI
public class GettingStartedUiTestBasic extends BaseQuest {

    @Test
    @Regression
    @Description("Basic UI flow")
    void exampleUITest(Quest quest) {

        // Navigate and interact with elements
        // Configure baseUrl in config.properties
        
        quest.use(RING_OF_UI)
                .getNavigation().navigate(getUiConfig().baseUrl())
#if( $ui.contains("BUTTON") )
                .getButtonField().click(ButtonFields.LOGIN_BUTTON)
#end
#if( $ui.contains("INPUT") )
                .getInputField().insert(InputFields.USERNAME, "example")
#end
#if( $ui.contains("SELECT") )
                // .getSelectField().selectOption(SelectFields.GENERIC_SELECT, "example")
#end
                .complete();
    }

    @Test
    @Regression
    void customFlowDemonstration(Quest quest) {

        /**
         * Example:
         *
         * <p>TODO: implement your custom flow here</p>
         */
//        quest.use(RING_OF_CUSTOM)
//                // .performExampleFlow(order) add custom flow here
//                .complete();
    }
}
