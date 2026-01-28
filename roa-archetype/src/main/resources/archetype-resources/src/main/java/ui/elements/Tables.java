package ${package}.ui.elements;

import ${package}.ui.model.table.ExampleTableModel;
import io.cyborgcode.roa.ui.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.service.tables.TableElement;

import java.util.function.Consumer;

// TODO: Replace EXAMPLE_TABLE_MODEL with your real table schemas and add more table entries as needed.
public enum Tables implements TableElement<Tables> {

    EXAMPLE_TABLE_MODEL(ExampleTableModel.class);

    private final Class<?> rowRepresentationClass;
    private final TableComponentType tableType;
    private final Consumer<SmartWebDriver> before;
    private final Consumer<SmartWebDriver> after;

    <T> Tables(final Class<T> rowRepresentationClass) {
        this(rowRepresentationClass, null, driver -> {}, driver -> {});
    }

    <T> Tables(final Class<T> rowRepresentationClass, TableComponentType tableType,
                     Consumer<SmartWebDriver> before, Consumer<SmartWebDriver> after) {
        this.rowRepresentationClass = rowRepresentationClass;
        this.tableType = tableType;
        this.before = before;
        this.after = after;
    }

    @Override
    public <T extends TableComponentType> T tableType() {
        return tableType != null ? (T) tableType : TableElement.super.tableType();
    }

    @Override
    public <T> Class<T> rowsRepresentationClass() {
        return (Class<T>) rowRepresentationClass;
    }

    @Override
    public Tables enumImpl() {
        return this;
    }

    @Override
    public Consumer<SmartWebDriver> before() {
        return before;
    }

    @Override
    public Consumer<SmartWebDriver> after() {
        return after;
    }

}