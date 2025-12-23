package ${package}.ui.interceptor;

import io.cyborgcode.roa.ui.parameters.DataIntercept;

/**
 * Registry of network request interception patterns.
 * <p>
 * Use this to define network requests (e.g., specific API calls) that the UI test
 * should spy on or wait for.
 * </p>
 */
public enum RequestsInterceptor implements DataIntercept<RequestsInterceptor> {

    /**
     * Example interception pattern:
     */
    EXAMPLE_INTERCEPT("/example-endpoint");

    /**
     * Constants referencing the enum names for use in {@code @InterceptRequests} annotations.
     *
     * Example in tests:
     *
     * @InterceptRequests(requestUrlSubStrings = {RequestsInterceptor.Data.EXAMPLE_INTERCEPT})
     */
    public static final class Data {

        public static final String EXAMPLE_INTERCEPT = "EXAMPLE_INTERCEPT";
    }

    private final String endpointSubString;

    RequestsInterceptor(final String endpointSubString) {
        this.endpointSubString = endpointSubString;
    }

    @Override
    public String getEndpointSubString() {
        return endpointSubString;
    }

    @Override
    public RequestsInterceptor enumImpl() {
        return this;
    }
}
