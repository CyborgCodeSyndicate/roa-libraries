package ${package}.api.authentication;

import io.cyborgcode.roa.api.authentication.BaseAuthenticationClient;
import io.cyborgcode.roa.api.service.RestService;
import io.restassured.http.Header;

// TODO implement authorization logic and return the required Authorization header
public class ExampleAuthenticationClient extends BaseAuthenticationClient {

    @Override
    protected Header authenticateImpl(RestService restService, String username, String password) {
        // Example:
        // String token = restService
        //    .request(POST_LOGIN_USER, new LoginDto(username, password))
        //    .getBody()
        //    .jsonPath()
        //    .getString(TOKEN.getJsonPath());
        // return new Header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_HEADER_VALUE + token);
        return null;
    }
}
