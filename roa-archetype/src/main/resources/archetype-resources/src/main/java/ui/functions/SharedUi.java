package ${package}.ui.functions;

import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.openqa.selenium.By;

/**
 * These functions integrate with ROA's {@link ContextConsumer} pattern, allowing them
 * to be passed as {@code Consumer<SmartWebDriver>} to element hooks, ensuring proper
 * synchronization with dynamic UI behavior.
 * </p>
 */
public enum SharedUi {

    WAIT_FOR_TIMEOUT((driver, by) -> SharedUiFunctions.waitForTimeout(driver));

    private final BiConsumer<SmartWebDriver, By> function;

    SharedUi(BiConsumer<SmartWebDriver, By> function) {
        this.function = function;
    }
}
