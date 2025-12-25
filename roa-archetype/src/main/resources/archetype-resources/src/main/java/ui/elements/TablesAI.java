package ${package}.ui.elements;

import ${package}.ui.model.table.ExampleTableModel;
import io.cyborgcode.roa.ui.service.tables.TableElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import java.util.function.Consumer;

public enum TablesAI implements TableElement<TablesAI> {

    // TODO: Define your tables
    EXAMPLE_TABLE(ExampleTableModel.class);

    private final Class<?> rowRepresentationClass;
    private final Consumer<SmartWebDriver> before;
    private final Consumer<SmartWebDriver> after;

    TablesAI(final Class<?> rowRepresentationClass) {
        this(rowRepresentationClass, d -> {}, d -> {});
    }

    TablesAI(final Class<?> rowRepresentationClass,
                   Consumer<SmartWebDriver> before, Consumer<SmartWebDriver> after) {
        this.rowRepresentationClass = rowRepresentationClass;
        this.before = before;
        this.after = after;
    }

    @Override
    public <T> Class<T> rowsRepresentationClass() {
        return (Class<T>) rowRepresentationClass;
    }

    @Override
    public TablesAI enumImpl() {
        return this;
    }

    @Override
    public Consumer<SmartWebDriver> before() { return before; }

    @Override
    public Consumer<SmartWebDriver> after() { return after; }
}
