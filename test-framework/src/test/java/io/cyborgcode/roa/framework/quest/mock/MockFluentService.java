package io.cyborgcode.roa.framework.quest.mock;

import io.cyborgcode.roa.framework.annotation.Ring;
import io.cyborgcode.roa.framework.chain.FluentService;

@Ring("MockRing")
public class MockFluentService extends FluentService {

   public String mockField = "mockValue";
}
