package ${package}.common.data.creator;
#set($mods = $modules.toUpperCase())
import io.cyborgcode.roa.framework.parameters.DataForge;
import io.cyborgcode.roa.framework.parameters.Late;
#if($mods.contains("API"))
import ${package}.api.dto.request.ExampleRequestDto;
#end

// TODO: Replace EXAMPLE_MODEL with your real data creation functions (or delete this enum if not needed).
public enum DataCreator implements DataForge<DataCreator> {

    EXAMPLE_MODEL(() -> {
#if($mods.contains("API"))
        // Example:
        // return ExampleRequestDto.builder().name("John Doe").job("Engineer").build();
#end
        return null;
    });

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
