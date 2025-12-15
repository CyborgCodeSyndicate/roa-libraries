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
     * Example interception pattern.
     * <p>
     * TODO: Replace this with a real pattern such as "/api/orders" or "?ajax=true".
     * </p>
     */
    EXAMPLE_INTERCEPT("/example-endpoint");

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
