package io.cyborgcode.roa.db.allure;

import io.cyborgcode.roa.db.client.DbClient;
import io.cyborgcode.roa.db.client.DbClientManager;
import io.cyborgcode.roa.db.config.DatabaseConfiguration;
import io.cyborgcode.roa.db.connector.BaseDbConnectorService;
import org.springframework.stereotype.Component;

/**
 * Specialized {@code DbClientManager} implementation for Allure reporting.
 *
 * <p>This class extends {@link DbClientManager} and overrides the client initialization
 * to integrate with Allure, enabling enhanced logging and reporting for database interactions.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Component
public class AllureDbClientManager extends DbClientManager {

   /**
    * Constructs an {@code AllureDbClientManager} with the specified database connector.
    *
    * @param connector The {@link BaseDbConnectorService} responsible for establishing database connections.
    */
   public AllureDbClientManager(final BaseDbConnectorService connector) {
      super(connector);
   }

   /**
    * Initializes an {@code Allure}-enhanced database client.
    *
    * <p>This method overrides the default client initialization to provide
    * enhanced request and response logging using Allure reports.
    *
    * @param dbConfig The configuration used to initialize the client.
    * @return A {@link DbClient} instance enhanced with Allure logging.
    */
   @Override
   protected DbClient initializeDbClient(final DatabaseConfiguration dbConfig) {
      return new RelationalDbClientAllure(getConnector(), dbConfig);
   }
}
