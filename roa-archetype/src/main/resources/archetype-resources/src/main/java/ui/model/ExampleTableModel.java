package ${package}.ui.model;

import io.cyborgcode.roa.ui.annotations.InsertionElement;
import ${package}.ui.elements.InputFields;
import ${package}.ui.elements.SelectFields;
import lombok.*;

/**
 * Example domain model demonstrating ROA automatic UI form insertion.
 *
 * <p>This class is part of the archetype template. Replace the fields and the
 * {@link InsertionElement} mappings with your real application's data model.</p>
 *
 * <p>When used with:</p>
 * <pre>{@code
 * quest.use(RING_OF_UI).insertion().insertData(order);
 * }</pre>
 *
 * ROA will automatically fill all annotated fields into the corresponding
 * UI elements in the specified order.
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
   @InsertionElement(
         locatorClass = InputFields.class,
         elementEnum = "EXAMPLE_INPUT",
         order = 1
   )
   private String exampleText;

   /**
    * Example dropdown/select field.
    * Replace with a real field and element mapping.
    */
   @InsertionElement(
         locatorClass = SelectFields.class,
         elementEnum = "EXAMPLE_DROPDOWN",
         order = 2
   )
   private String exampleSelection;

   /**
    * Add your own model fields below.
    * They can be plain fields or annotated with @InsertionElement
    * depending on whether they should be auto-filled.
    */
   private String additionalInfo;
}
