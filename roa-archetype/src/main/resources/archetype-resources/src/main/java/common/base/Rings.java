package ${package}.common.base;

#set($mods = $modules.toUpperCase())

#if($mods.contains("API"))
import io.cyborgcode.roa.api.service.fluent.RestServiceFluent;
#end
#if($mods.contains("DB"))
import io.cyborgcode.roa.db.service.fluent.DatabaseServiceFluent;
#end
import ${package}.common.service.CustomService;
#if($mods.contains("UI"))
import ${package}.ui.AppUiService;
#end
import lombok.experimental.UtilityClass;

@UtilityClass
public class Rings {

#if($mods.contains("UI"))
    public static final Class<AppUiService> RING_OF_UI = AppUiService.class;
#end
#if($mods.contains("API"))
    public static final Class<RestServiceFluent> RING_OF_API = RestServiceFluent.class;
#end
#if($mods.contains("DB"))
    public static final Class<DatabaseServiceFluent> RING_OF_DB = DatabaseServiceFluent.class;
#end

    // EXAMPLE: Add your own ring(s) by registering your custom fluent/service facade here.
    // TODO: Replace CustomService with your implementation, or delete this line if you don't need custom rings.
    public static final Class<CustomService> RING_OF_CUSTOM = CustomService.class;
}
