package ${package}.ui;

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

import static io.cyborgcode.roa.ui.config.UiConfigHolder.getUiConfig;
import static ${package}.common.base.Rings.RING_OF_UI;
import static ${package}.common.base.Rings.RING_OF_CUSTOM;

// TODO: EXAMPLE TEST — delete this class after reviewing.
// This test is only an example of using ROA UI capabilities
@UI
@DisplayName("EXAMPLE - Getting started UI test (delete me)")
public class GettingStartedUiTestBasic extends BaseQuest {

    @Test
    @Regression
    void exampleUITest(Quest quest) {

        quest.use(RING_OF_UI)
                .browser().navigate(getUiConfig().baseUrl())
#if( $ui.contains("BUTTON") )
                // .button().click(ButtonFields.LOGIN_BUTTON)
#end
#if( $ui.contains("INPUT") )
                // .input().insert(InputFields.USERNAME, "example")
                // .input().insert(InputFields.PASSWORD, "example")
#end
#if( $ui.contains("SELECT") )
                // .select().selectOption(SelectFields.GENERIC_SELECT, "example")
#end
                .complete();
    }

    @Test
    @Regression
    void customFlowDemonstration(Quest quest) {
        // Example:
        // quest.use(RING_OF_CUSTOM)
        //    .exampleFlow()
        //    .drop()
        //    .use(RING_OF_UI)
        //    .browser().navigate(getUiConfig().baseUrl())
        //    .complete();
    }
}
