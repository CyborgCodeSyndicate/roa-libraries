package io.cyborgcode.roa.framework.quest.mock;

import io.cyborgcode.roa.framework.annotation.TestService;
import io.cyborgcode.roa.framework.chain.FluentService;

@TestService("MockWorld")
public class MockFluentService extends FluentService {

   public String mockField = "mockValue";
}
