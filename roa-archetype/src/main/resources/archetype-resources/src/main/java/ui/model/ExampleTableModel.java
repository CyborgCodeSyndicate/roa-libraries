package ${package}.ui.model;

import io.cyborgcode.roa.ui.annotations.InsertionElement;
#set( $ui = $uiComponents.toUpperCase() )
#if( $ui.contains("INPUT") )
import ${package}.ui.elements.InputFields;
#end
#if( $ui.contains("SELECT") )
import ${package}.ui.elements.SelectFields;
#end
import lombok.*;

/**
 * Example model for automatic UI form filling.
 * <p>
 * Fields with @InsertionElement are auto-filled when calling
 * uiService.getForm().insert(model).
 * </p>
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ExampleTableModel {

    /**
     * Example text input field.
     * <p>
     * Maps this model field to the USERNAME UI element.
     * When you call {@code uiService.getForm().insert(model)}, this value will be
     * automatically inserted into the USERNAME input field.
     * </p>
     */
#if( $ui.contains("INPUT") )
    @InsertionElement(
            locatorClass = InputFields.class,
            elementEnum = "USERNAME",
            order = 1
    )
#end
    private String exampleText;

    /**
     * Example dropdown/select field.
     * <p>
     * Maps this model field to the GENERIC_SELECT UI element.
     * </p>
     */
#if( $ui.contains("SELECT") )
    @InsertionElement(
            locatorClass = SelectFields.class,
            elementEnum = "GENERIC_SELECT",
            order = 2
    )
#end
    private String exampleSelection;

    /**
     * Additional field not mapped to any UI element.
     * <p>
     * You can have fields that aren't annotated with {@code @InsertionElement}.
     * These can be used for data storage or other purposes.
     * </p>
     */
    private String additionalInfo;
}
