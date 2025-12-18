package ${package}.ui.model.table;

import io.cyborgcode.roa.ui.components.table.annotations.TableCellLocator;
import io.cyborgcode.roa.ui.components.table.annotations.TableInfo;
import io.cyborgcode.roa.ui.components.table.model.TableCell;
import lombok.*;
import org.openqa.selenium.support.FindBy;


/**
 * Represents a single row in the example table.
 * <p>
 * This class maps table columns to strongly-typed {@link TableCell} fields using ROA annotations.
 * It is used by the {@link TableExample} to read and validate table content in a structured way.
 * </p>
 */
@TableInfo(
        tableContainerLocator = @FindBy(css = "table#table"),
        rowsLocator = @FindBy(css = "tbody tr"),
        headerRowLocator = @FindBy(css = "thead tr"))
@Setter
@NoArgsConstructor
@Getter
public class ExampleTableModel {

    @TableCellLocator(cellLocator = @FindBy(tagName = "td"), headerCellLocator = @FindBy(tagName = "th"))
    private TableCell row;

}
