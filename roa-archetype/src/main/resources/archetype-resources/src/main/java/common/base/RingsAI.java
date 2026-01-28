package ${package}.common.base;

#set($mods = $modules.toUpperCase())

#if($mods.contains("API"))
import io.cyborgcode.roa.api.service.fluent.RestServiceFluent;
#end
#if($mods.contains("DB"))
import io.cyborgcode.roa.db.service.fluent.DatabaseServiceFluent;
#end
#if($mods.contains("UI"))
import ${package}.ui.AppUiService;
#end
import lombok.experimental.UtilityClass;

@UtilityClass
public class RingsAI {

#if($mods.contains("UI"))
    public static final Class<AppUiService> RING_OF_UI = AppUiService.class;
#end
#if($mods.contains("API"))
    public static final Class<RestServiceFluent> RING_OF_API = RestServiceFluent.class;
#end
#if($mods.contains("DB"))
    public static final Class<DatabaseServiceFluent> RING_OF_DB = DatabaseServiceFluent.class;
#end
}
