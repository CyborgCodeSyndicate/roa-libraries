package ${package}.common.data.creator;

import io.cyborgcode.roa.framework.parameters.DataForge;
import io.cyborgcode.roa.framework.parameters.Late;
import ${package}.api.dto.request.ExampleRequestDto;

/**
 * Registry of reusable test data factories.
 * <p>
 * This enum implements {@link DataForge}, allowing you to define "Late" objects -
 * data that is instantiated just before the test runs. Use the {@code @Craft} or {@JourneyData} annotation
 * to inject these into your tests.
 * </p>
 */
public enum DataCreator implements DataForge<DataCreator> {

    /**
     * Factory for creating an {@link ExampleRequestDto} with standard test values.
     */
    EXAMPLE_MODEL(() -> ExampleRequestDto.builder()
            .name("John Doe")
            .job("Engineer")
            .build());

    /**
     * Constants referencing the enum names for use in {@code @Craft} annotations.
     *
     * Example in tests:
     *
     * void test(model = @Craft(DataCreator.Data.EXAMPLE_MODEL) ExampleDto dto)
     */
    public static final class Data {

        public static final String EXAMPLE_MODEL = "EXAMPLE_MODEL";

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
