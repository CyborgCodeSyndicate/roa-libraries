package ${package}.common.base;

#if( $modules.contains("API") )
import io.cyborgcode.roa.api.service.fluent.RestServiceFluent;
#end
#if( $modules.contains("DB") )
import io.cyborgcode.roa.db.service.fluent.DatabaseServiceFluent;
#end
import io.cyborgcode.roa.framework.quest.Quest;
import ${package}.common.service.CustomService;
#if( $modules.contains("UI") )
import ${package}.ui_module.ui.AppUiService;
#end
import lombok.experimental.UtilityClass;

/**
 * Central registry for ROA "rings" (fluent API facades) available to tests.
 * <p>
 * A ring represents the concrete fluent service implementation that backs {@link Quest#use(Class)}.
 * Tests switch between rings to access different testing capabilities:
 * </p>
 * <ul>
#if( $modules.contains("API") )
 *   <li>{@link #RING_OF_API} � REST API operations via RestAssured</li>
#end
#if( $modules.contains("DB") )
 *   <li>{@link #RING_OF_DB} � Database query and validation operations</li>
#end
#if( $modules.contains("UI") )
 *   <li>{@link #RING_OF_UI} � Selenium-based UI interactions (inputs, buttons, selects, etc.)</li>
#end
 *   <li>{@link #RING_OF_CUSTOM} � Delegate to a custom higher-level service with reusable flows</li>
 * </ul>
 * <p>
 * This indirection keeps test code expressive while cleanly separating concerns between low-level HTTP,
 * database interactions, UI operations and shared domain-specific actions.
 * </p>
 */
@UtilityClass
public class Rings {

#if( $modules.contains("API") )
   public static final Class<RestServiceFluent> RING_OF_API = RestServiceFluent.class;
#end

#if( $modules.contains("DB") )
   public static final Class<DatabaseServiceFluent> RING_OF_DB = DatabaseServiceFluent.class;
#end

#if( $modules.contains("UI") )
   public static final Class<AppUiService> RING_OF_UI = AppUiService.class;
#end
   public static final Class<CustomService> RING_OF_CUSTOM = CustomService.class;

}
