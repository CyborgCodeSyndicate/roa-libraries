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
    void createExampleUsingRawValues(Quest quest) {
        quest.use(RING_OF_UI)
                .browser().navigate(getUiConfig().baseUrl())
                .input().insert(InputFields.GENERIC_INPUT, "example")
                .button().click(ButtonFields.GENERIC_BUTTON)
                .select().selectOption(SelectFields.GENERIC_SELECT, "Option 1")
                .complete();
    }

    @Test
    @Regression
    void customFlowDemonstration(Quest quest) {

        quest.use(RING_OF_CUSTOM)
                // .performExampleFlow(order) add custom flow here
                .complete();
    }
}
