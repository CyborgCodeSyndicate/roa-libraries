package ${package}.ui.interceptor;

import io.cyborgcode.roa.ui.parameters.DataIntercept;

/**
 * Example registry of network request interception patterns.
 *
 * <p>This template demonstrates how to register URL substring patterns that
 * can be intercepted during UI automation.</p>
 *
 * <p>Replace the example entries below with the actual endpoints or
 * request patterns used by your application.</p>
 *
 * <p>Usage example:</p>
 * <pre>{@code
 * @Intercept(request = RequestsInterceptor.EXAMPLE_INTERCEPT)
 * void test(Quest quest) {
 *     // ...
 * }
 * }</pre>
 */
public enum RequestsInterceptor implements DataIntercept<RequestsInterceptor> {

    /**
     * Example interception pattern.
     * Replace this with a real pattern such as "/api/orders" or "?ajax=true".
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
