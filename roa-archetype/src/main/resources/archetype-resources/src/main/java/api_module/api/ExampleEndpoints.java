package ${package}.api_module.api;

import io.cyborgcode.roa.api.core.Endpoint;
import io.restassured.http.Method;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

/**
 * Example endpoint definition.
 *
 * <p>This is a placeholder used to demonstrate how to define endpoints
 * using the ROA {@link Endpoint} interface. Replace these examples with
 * your application's real API endpoints.</p>
 */
public enum ExampleEndpoints implements Endpoint<ExampleEndpoints> {

   EXAMPLE_GET(Method.GET, "/example/get"),
   EXAMPLE_POST(Method.POST, "/example/create");

   private final Method method;
   private final String url;

   ExampleEndpoints(Method method, String url) {
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
   public ExampleEndpoints enumImpl() {
      return this;
   }

   @Override
   public RequestSpecification defaultConfiguration() {
      return Endpoint.super.defaultConfiguration()
            .contentType(ContentType.JSON);
   }

   @Override
   public Map<String, List<String>> headers() {
      return Map.of(
            "Accept", List.of("application/json")
      );
   }
}
