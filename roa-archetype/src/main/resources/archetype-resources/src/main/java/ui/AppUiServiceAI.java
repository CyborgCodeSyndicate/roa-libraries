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

public class AppUiServiceAI extends UiServiceFluent<AppUiServiceAI> {

    public AppUiServiceAI(SmartWebDriver driver, SuperQuest quest) {
        super(driver);
        this.quest = quest;
        postQuestSetupInitialization();
    }

#set( $ui = $uiComponents.toUpperCase() )
#if( $ui.contains("INPUT") )
    public InputServiceFluent<AppUiServiceAI> input() {
        return getInputField();
    }
#end

#if( $ui.contains("TABLE") )
    public TableServiceFluent<AppUiServiceAI> table() {
        return getTable();
    }
#end

#if( $ui.contains("BUTTON") )
    public ButtonServiceFluent<AppUiServiceAI> button() {
        return getButtonField();
    }
#end

#if( $ui.contains("SELECT") )
    public SelectServiceFluent<AppUiServiceAI> select() {
        return getSelectField();
    }
#end

    public InterceptorServiceFluent<AppUiServiceAI> interceptor() {
        return getInterceptor();
    }

    public InsertionServiceFluent<AppUiServiceAI> insertion() {
        return getInsertionService();
    }

    public NavigationServiceFluent<AppUiServiceAI> browser() {
        return getNavigation();
    }

    public ValidationServiceFluent<AppUiServiceAI> validate() {
        return getValidation();
    }

}
