package ${package}.ui.functions;

import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static ${package}.ui.functions.ExpectedConditionsStore .*;

public class SharedUiFunctions {

    private SharedUiFunctions() {
    }

    /**
     * Waits for 1 second. This can be used to add a delay when
     * needed in the UI.
     */
    @SuppressWarnings("unused")
    public static void waitForTimeout(SmartWebDriver driver) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Thread was interrupted during sleep", e);
        }
    }
}
