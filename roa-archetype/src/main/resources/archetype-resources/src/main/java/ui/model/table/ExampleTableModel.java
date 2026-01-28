package ${package}.ui.model.table;

import io.cyborgcode.roa.ui.components.table.annotations.TableCellLocator;
import io.cyborgcode.roa.ui.components.table.annotations.TableInfo;
import io.cyborgcode.roa.ui.components.table.model.TableCell;
import lombok.*;
import org.openqa.selenium.support.FindBy;


// TODO: Replace this example row model with your real table row representation(s).
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
