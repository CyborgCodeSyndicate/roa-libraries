package ${package}.common.data.creator;

import io.cyborgcode.roa.framework.parameters.DataForge;
import io.cyborgcode.roa.framework.parameters.Late;
import ${package}.api.dto.request.ExampleRequestDto;
import ${package}.ui.model.ExampleTableModel;

/**
 * Registry of reusable test data factories.
 * <p>
 * This enum implements {@link DataForge}, allowing you to define "Late" objects -
 * data that is instantiated just before the test runs. Use the {@code @Craft} annotation
 * to inject these into your tests.
 * </p>
 */
public enum DataCreator implements DataForge<DataCreator> {

    /**
     * Example model creator.
     * <p>
     * TODO: Replace with a real data factory. This example creates an API DTO.
     * </p>
     */
    EXAMPLE_MODEL(() -> ExampleRequestDto.builder()
            .name("John Doe")
            .job("Engineer")
            .build()),

    /**
     * Example table model creator.
     * <p>
     * TODO: This creates a UI model. Useful for auto-filling forms.
     * </p>
     */
    EXAMPLE_TABLE_MODEL(() -> ExampleTableModel.builder()
            .exampleText("Sample Text")
            .exampleSelection("Option 1")
            .additionalInfo("Extra Details")
            .build());

    /**
     * Constants for use in {@code @Craft} annotations.
     * <p>
     * TODO: Add matching constants for your new enum values here.
     * </p>
     */
    public static final class Data {

        public static final String EXAMPLE_MODEL = "EXAMPLE_MODEL";
        public static final String EXAMPLE_TABLE_MODEL = "EXAMPLE_TABLE_MODEL";

        private Data() {
        }
    }

    private final Late<Object> createDataFunction;

    DataCreator(final Late<Object> createDataFunction) {
        this.createDataFunction = createDataFunction;
    }

    @Override
    public Late<Object> dataCreator() {
        return createDataFunction;
    }

    @Override
    public DataCreator enumImpl() {
        return this;
    }
}
