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
 * Example domain model demonstrating ROA automatic UI form insertion.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ExampleTableModel {

    /**
     * Example text input field.
     * Replace with a real field and element mapping.
     */
#if( $ui.contains("INPUT") )
//    @InsertionElement(
//            locatorClass = InputFields.class,
//            elementEnum = "EXAMPLE_INPUT",
//            order = 1
//    )
#end
    private String exampleText;

    /**
     * Example dropdown/select field.
     * Replace with a real field and element mapping.
     */
#if( $ui.contains("SELECT") )
//    @InsertionElement(
//            locatorClass = SelectFields.class,
//            elementEnum = "EXAMPLE_DROPDOWN",
//            order = 2
//    )
#end
    private String exampleSelection;

    /**
     * Add your own model fields below.
     * They can be plain fields or annotated with @InsertionElement
     * depending on whether they should be auto-filled.
     */
    private String additionalInfo;
}
