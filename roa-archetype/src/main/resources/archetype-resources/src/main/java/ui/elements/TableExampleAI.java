package ${package}.ui.elements;

import ${package}.ui.model.table.ExampleTableModel;
import io.cyborgcode.roa.ui.service.tables.TableElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import java.util.function.Consumer;

public enum TableExampleAI implements TableElement<TableExampleAI> {

    // TODO: Define your table elements
    EXAMPLE_TABLE(ExampleTableModel.class);

    private final Class<?> rowRepresentationClass;
    private final Consumer<SmartWebDriver> before;
    private final Consumer<SmartWebDriver> after;

    TableExampleAI(final Class<?> rowRepresentationClass) {
        this(rowRepresentationClass, d -> {}, d -> {});
    }

    TableExampleAI(final Class<?> rowRepresentationClass,
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
    public TableExampleAI enumImpl() {
        return this;
    }

    @Override
    public Consumer<SmartWebDriver> before() { return before; }

    @Override
    public Consumer<SmartWebDriver> after() { return after; }
}
