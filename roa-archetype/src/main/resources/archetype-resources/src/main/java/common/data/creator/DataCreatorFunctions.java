package ${package}.common.data.creator;

import ${package}.api_module.api.dto.request.ExampleRequestDto;
import ${package}.ui_module.ui.model.ExampleTableModel;

/**
 * Factory methods used by {@link DataCreator}.
 *
 * <p>This class provides a single placeholder model to keep the archetype simple.
 * Replace the implementation with your actual domain models.</p>
 */
public final class DataCreatorFunctions {

   private DataCreatorFunctions() {}

   /**
    * Example models used across API/DB/UI tests.
    */
   public static ExampleRequestDto createExampleModel() {
      return ExampleRequestDto.builder()
            .name("Example Name")
            .job("Example Role")
            .build();
   }

   public static ExampleTableModel createExampleTableModel() {
      return ExampleTableModel.builder()
            .exampleText("Example Text")
            .exampleSelection("Example Selection")
            .build();
   }
}
