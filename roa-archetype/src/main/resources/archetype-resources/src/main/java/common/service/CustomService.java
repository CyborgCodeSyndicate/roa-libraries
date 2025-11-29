package $

{package}.common.service;

import io.cyborgcode.roa.framework.annotation.Ring;
import io.cyborgcode.roa.framework.chain.FluentService;

/**
 * Custom high-level service for application-specific actions.
 *
 * <p>This service is intended to encapsulate reusable business flows
 * that orchestrate multiple rings (UI, API, DB) in a readable way.
 *
 * <p>Replace the example methods with real flows needed for your application.
 * Tests can access this service with:
 *
 * <pre>{@code
 * quest.use(RING_OF_CUSTOM).exampleFlow();
 * }</pre>
 */
@Ring("Custom")
public class CustomService extends FluentService {

    /**
     * Example method demonstrating how to implement a custom flow.
     *
     * <p>Replace this with your actual business operations (e.g., login,
     * create user, create order, etc.).
     */
    public CustomService exampleFlow() {
        // Example of orchestration:
        // quest.use(RING_OF_UI).browser().navigate("https://example.com");
        // quest.use(RING_OF_API).request(...);
        // quest.use(RING_OF_DB).query(...);

        System.out.println("Executing example custom flow...");
        return this;
    }
}
