package ${package}.ui.interceptor;

import io.cyborgcode.roa.ui.parameters.DataIntercept;

// TODO: Replace EXAMPLE_INTERCEPT with your real request interception patterns and add more entries as needed.
public enum RequestsInterceptor implements DataIntercept<RequestsInterceptor> {

    EXAMPLE_INTERCEPT("/example-endpoint");

    public static final class Data {

        public static final String EXAMPLE_INTERCEPT = "EXAMPLE_INTERCEPT";

        private Data() {
        }
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
