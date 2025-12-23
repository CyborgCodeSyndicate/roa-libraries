package ${package}.api;

import io.cyborgcode.roa.api.annotations.API;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@API
@DisplayName("Getting started API test class")
public class GettingStartedApiTestAI extends BaseQuest {

    @Test
    void exampleAPITest(Quest quest) {
    }
}
