package ${package}.ui.model.table;

import io.cyborgcode.roa.ui.components.table.annotations.TableInfo;
import lombok.*;
import org.openqa.selenium.support.FindBy;

//TODO: Adapt your table models according to the table ui elements in your app
//@TableInfo(
//        tableContainerLocator = @FindBy(css = "table"),
//        rowsLocator = @FindBy(css = "tr"),
//        headerRowLocator = @FindBy(css = "tr"))
@Setter
@NoArgsConstructor
@Getter
public class ExampleTableModelAI {

    // TODO: Add table cells using @TableCellLocator
//    @TableCellLocator(cellLocator = @FindBy(tagName = "td"), headerCellLocator = @FindBy(tagName = "th"))
//    private TableCell row;
}
