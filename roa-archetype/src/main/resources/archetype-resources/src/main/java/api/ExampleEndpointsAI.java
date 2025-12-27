package ${package}.api;

import io.cyborgcode.roa.api.core.Endpoint;
import io.restassured.http.Method;

public enum ExampleEndpointsAI implements Endpoint<ExampleEndpointsAI> {

    ;

    private final Method method;
    private final String url;

    ExampleEndpointsAI(Method method, String url) {
        this.method = method;
        this.url = url;
    }

    @Override
    public Method method() {
        return method;
    }

    @Override
    public String url() {
        return url;
    }

    @Override
    public ExampleEndpointsAI enumImpl() {
        return this;
    }
}
