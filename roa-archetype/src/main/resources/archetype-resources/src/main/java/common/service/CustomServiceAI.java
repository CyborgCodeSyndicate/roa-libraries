package ${package}.common.service;

import io.cyborgcode.roa.framework.annotation.Ring;
import io.cyborgcode.roa.framework.chain.FluentService;

@Ring("Custom")
public class CustomServiceAI extends FluentService {

    //TODO: Add custom methods by using other services - rings applicable to your app behaviour
    public CustomService exampleFlow() {
        System.out.println("Executing example custom flow");
        return this;
    }
}
