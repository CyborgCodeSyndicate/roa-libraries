package ${package}.ui;

import io.cyborgcode.roa.framework.annotation.Description;
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
import static io.cyborgcode.roa.ui.config.UiConfigHolder.getUiConfig;

/**
 * Getting started UI test (basic template).
 *
 * <p>Minimal example showing raw-value interactions. Replace locators and flows with your app.</p>
 */
@UI
@DisplayName("Template UI Tests � Getting Started")
public class GettingStartedUiTestBasic extends BaseQuest {

   @Test
   @Regression
   @Description("Basic UI flow using raw test values � customize for your app.")
   void createExampleUsingRawValues(Quest quest) {
      quest.use(RING_OF_UI)
            .browser().navigate(getUiConfig().baseUrl())
            .input().insert(InputFields.EXAMPLE_INPUT, "example")
            .button().click(ButtonFields.EXAMPLE_BUTTON)
            .select().selectOption(SelectFields.EXAMPLE_DROPDOWN, "Option 1")
            .complete();
   }
}
