package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.service.tables.TableElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import java.util.function.Consumer;

public enum TablesAI implements TableElement<TablesAI> {

    ;

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
