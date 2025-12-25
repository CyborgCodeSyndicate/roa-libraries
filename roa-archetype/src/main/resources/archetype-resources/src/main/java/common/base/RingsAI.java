package ${package}.common.base;

#set( $mods = $modules.toUpperCase() )

#if($mods.contains("API"))
import io.cyborgcode.roa.api.service.fluent.RestServiceFluent;
#end
#if($mods.contains("DB"))
import io.cyborgcode.roa.db.service.fluent.DatabaseServiceFluent;
#end
import io.cyborgcode.roa.framework.quest.Quest;
import ${package}.common.service.CustomService;
#if($mods.contains("UI"))
import io.cyborgcode.roa.ui.service.fluent.UiServiceFluent;
#end
import lombok.experimental.UtilityClass;

@UtilityClass
public class RingsAI {

#if($mods.contains("API"))
    public static final Class<RestServiceFluent> RING_OF_API = RestServiceFluent.class;
#end

#if($mods.contains("DB"))
    public static final Class<DatabaseServiceFluent> RING_OF_DB = DatabaseServiceFluent.class;
#end

#if($mods.contains("UI"))
    public static final Class<UiServiceFluent> RING_OF_UI = UiServiceFluent.class;
#end
    public static final Class<CustomService> RING_OF_CUSTOM = CustomService.class;

}
