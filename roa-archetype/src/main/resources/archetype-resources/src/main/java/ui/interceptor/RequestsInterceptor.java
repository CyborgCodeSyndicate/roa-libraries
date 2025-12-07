package ${package}.ui.interceptor;

import io.cyborgcode.roa.ui.parameters.DataIntercept;

/**
 * Example registry of network request interception patterns.
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
//        return endpointSubString;
        return "";
    }

    @Override
    public RequestsInterceptor enumImpl() {
//        return this;
        return null;
    }
}
