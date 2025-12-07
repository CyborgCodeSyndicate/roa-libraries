package ${package}.ui;

import io.qameta.allure.Description;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.ui.annotations.UI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ${package}.ui.elements.ButtonFields;
import ${package}.ui.elements.InputFields;
import ${package}.ui.elements.SelectFields;

import static ${package}.common.base.Rings.RING_OF_UI;
import static ${package}.common.base.Rings.RING_OF_CUSTOM;
import static io.cyborgcode.roa.ui.config.UiConfigHolder.getUiConfig;

/**
 * Getting started UI test.
 *
 * <p>Minimal example showing raw-value interactions. Replace locators and flows with your app.</p>
 */
@UI
public class GettingStartedUiTestBasic extends BaseQuest {

    @Test
    @Regression
    @Description("Basic UI flow")
    void exampleUITest(Quest quest) {

        /**
         * Example:
         *
         * <p>TODO: implement your UI test here</p>
         */
//        quest.use(RING_OF_UI)
//                .getNavigation().navigate(getUiConfig().baseUrl())
//                .getButtonField().click(ButtonFields.GENERIC_BUTTON)
//                .getInputField().insert(InputFields.GENERIC_INPUT, "example")
//                .getSelectField().selectOption(SelectFields.GENERIC_SELECT, "example")
//                .complete();
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
