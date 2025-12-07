package ${package}.ui.functions;

import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.openqa.selenium.By;

/**
 * Shared UI functions.
 */
public enum SharedUi {

    /**
     * Example function.
     */
    EXAMPLE_FUNCTION((driver, by) -> {});

    private final BiConsumer<SmartWebDriver, By> function;

    SharedUi(BiConsumer<SmartWebDriver, By> function) {
        this.function = function;
    }
}
