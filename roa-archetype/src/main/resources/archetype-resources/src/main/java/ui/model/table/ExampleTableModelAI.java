package ${package}.ui.model.table;

import io.cyborgcode.roa.ui.components.table.annotations.TableInfo;
import lombok.*;
import org.openqa.selenium.support.FindBy;

@TableInfo(
        tableContainerLocator = @FindBy(css = "table"),
        rowsLocator = @FindBy(css = "tr"),
        headerRowLocator = @FindBy(css = "tr"))
@Setter
@NoArgsConstructor
@Getter
public class ExampleTableModelAI {
    // TODO: Add table cells using @TableCellLocator
}
