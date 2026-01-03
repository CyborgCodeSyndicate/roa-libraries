package ${package}.ui;

import io.cyborgcode.roa.framework.quest.SuperQuest;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.service.fluent.ButtonServiceFluent;
import io.cyborgcode.roa.ui.service.fluent.InputServiceFluent;
import io.cyborgcode.roa.ui.service.fluent.InsertionServiceFluent;
import io.cyborgcode.roa.ui.service.fluent.InterceptorServiceFluent;
import io.cyborgcode.roa.ui.service.fluent.NavigationServiceFluent;
import io.cyborgcode.roa.ui.service.fluent.SelectServiceFluent;
import io.cyborgcode.roa.ui.service.fluent.UiServiceFluent;
import io.cyborgcode.roa.ui.service.fluent.ValidationServiceFluent;
import io.cyborgcode.roa.ui.service.tables.TableServiceFluent;

public class AppUiService extends UiServiceFluent<AppUiService> {

    public AppUiService(SmartWebDriver driver, SuperQuest quest) {
        super(driver);
        this.quest = quest;
        postQuestSetupInitialization();
    }

#set( $ui = $uiComponents.toUpperCase() )
#if( $ui.contains("INPUT") )
    public InputServiceFluent<AppUiService> input() {
        return getInputField();
    }
#end

#if( $ui.contains("TABLE") )
    public TableServiceFluent<AppUiService> table() {
        return getTable();
    }
#end

#if( $ui.contains("BUTTON") )
    public ButtonServiceFluent<AppUiService> button() {
        return getButtonField();
    }
#end

#if( $ui.contains("SELECT") )
    public SelectServiceFluent<AppUiService> select() {
        return getSelectField();
    }
#end

    public InterceptorServiceFluent<AppUiService> interceptor() {
        return getInterceptor();
    }

    public InsertionServiceFluent<AppUiService> insertion() {
        return getInsertionService();
    }

    public NavigationServiceFluent<AppUiService> browser() {
        return getNavigation();
    }

    public ValidationServiceFluent<AppUiService> validate() {
        return getValidation();
    }

}
