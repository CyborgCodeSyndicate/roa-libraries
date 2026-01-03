package ${package}.api;

import io.cyborgcode.roa.api.core.Endpoint;
import io.restassured.http.Method;

public enum EndpointsAI implements Endpoint<EndpointsAI> {

    ;

    private final Method method;
    private final String url;

    EndpointsAI(Method method, String url) {
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
    public EndpointsAI enumImpl() {
        return this;
    }
}
