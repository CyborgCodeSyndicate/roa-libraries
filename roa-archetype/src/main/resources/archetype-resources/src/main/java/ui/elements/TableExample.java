package ${package}.ui.elements;

import ${package}.ui.model.table.ExampleTableModel;
import io.cyborgcode.roa.ui.service.tables.DefaultTableTypes;
import io.cyborgcode.roa.ui.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.service.tables.TableElement;

import java.util.function.Consumer;

/**
 * Defines the table schemas available in the application.
 * <p>
 * This registry maps table models (row representations) to their optional specific component types
 * and synchronization logic, allowing for strongly-typed table interactions.
 * </p>
 */
public enum TableExample implements TableElement<TableExample> {

    /**
     * A sample table definition mapping the UI table to the {@link ExampleTableModel} row class.
     */
    EXAMPLE_TABLE_MODEL(ExampleTableModel.class);

    /**
     * Constants referencing the enum names for use in @Craft annotation.
     */
    public static final class Data {

        public static final String EXAMPLE_TABLE_MODEL = "EXAMPLE_TABLE_MODEL";

        private Data() {
        }

    }

    private final Class<?> rowRepresentationClass;
    private final TableComponentType tableType;
    private final Consumer<SmartWebDriver> before;
    private final Consumer<SmartWebDriver> after;

    <T> TableExample(final Class<T> rowRepresentationClass) {
        this(rowRepresentationClass, null, smartWebDriver -> {
        }, smartWebDriver -> {
        });
    }

    /**
     * Configures the table definition with optional component table type and synchronization hooks.
     */
    <T> TableExample(final Class<T> rowRepresentationClass, TableComponentType tableType,
                     Consumer<SmartWebDriver> before, Consumer<SmartWebDriver> after) {
        this.rowRepresentationClass = rowRepresentationClass;
        this.tableType = tableType;
        this.before = before;
        this.after = after;
    }

    @Override
    public <T extends TableComponentType> T tableType() {
        if (tableType != null) {
            return (T) tableType;
        } else {
            return TableElement.super.tableType();
        }
    }

    @Override
    public <T> Class<T> rowsRepresentationClass() {
        return (Class<T>) rowRepresentationClass;
    }

    @Override
    public TableExample enumImpl() {
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