package ${package}.db;

import io.cyborgcode.roa.db.annotations.DB;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DB
@DisplayName("Getting started DB test class")
public class GettingStartedDbTestAI extends BaseQuest {

    @Test
    void exampleDBTest(Quest quest) {
    }
}
