package ${package}.ui.interceptor;

import io.cyborgcode.roa.ui.parameters.DataIntercept;

public enum RequestsInterceptorAI implements DataIntercept<RequestsInterceptorAI> {

    // TODO: Add requests to intercept
    EXAMPLE_INTERCEPT("/example");

    private final String endpointSubString;

    RequestsInterceptorAI(final String endpointSubString) {
        this.endpointSubString = endpointSubString;
    }

    @Override
    public String getEndpointSubString() {
        return endpointSubString;
    }

    @Override
    public RequestsInterceptorAI enumImpl() {
        return this;
    }
}
