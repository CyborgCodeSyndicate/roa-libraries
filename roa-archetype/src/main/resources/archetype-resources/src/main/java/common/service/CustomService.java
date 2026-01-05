package ${package}.common.service;
#set($mods = $modules.toUpperCase())
import io.cyborgcode.roa.framework.annotation.Ring;
import io.cyborgcode.roa.framework.chain.FluentService;
import static ${package}.common.base.Rings.*;
#if($mods.contains("API"))
import ${package}.api.ExampleEndpoints;
#end
#if($mods.contains("DB"))
import ${package}.db.queries.ExampleDbQueries;
#end
#if($mods.contains("UI"))
import static io.cyborgcode.roa.ui.config.UiConfigHolder.getUiConfig;
#end

// TODO: Replace this sample service with your real workflow services (rename class + ring name, add your flows).
@Ring("Custom")
public class CustomService extends FluentService {

    public CustomService exampleFlow() {
#if($mods.contains("UI"))
        // Example - UI
        // quest.use(RING_OF_UI).browser().navigate(getUiConfig().baseUrl());
#end
#if($mods.contains("API"))
        // Example - API
        // quest.use(RING_OF_API).request(ExampleEndpoints.EXAMPLE_POST);
#end
#if($mods.contains("DB"))
        // Example - DB
        // quest.use(RING_OF_DB).query(ExampleDbQueries.SIMPLE_QUERY);
#end
        return this;
    }
}
