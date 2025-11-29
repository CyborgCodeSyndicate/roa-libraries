package ${package}.custom;

import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import org.junit.jupiter.api.Test;

import static ${package}.common.base.Rings.RING_OF_CUSTOM;

public class CustomFlowTest extends BaseQuest {

    @Test
    @Regression
    void customFlowDemonstration(Quest quest) {

        quest.use(RING_OF_CUSTOM)
                // .performExampleFlow(order) add custom flow here
                .complete();
    }
}
