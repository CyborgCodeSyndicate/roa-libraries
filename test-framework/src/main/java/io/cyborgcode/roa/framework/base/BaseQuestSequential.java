package io.cyborgcode.roa.framework.base;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Base test class for sequential test execution.
 *
 * <p>This class extends {@code BaseQuest} and ensures that tests run sequentially
 * within a single instance using the {@code PER_CLASS} lifecycle.
 * It also provides before-all and after-all lifecycle hooks for test setup and cleanup.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Component
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Pandora(
      description = "Sequential variant of BaseQuest that runs tests in a single instance (PER_CLASS lifecycle) "
            + "and exposes beforeAll/afterAll hooks for custom setup and cleanup.",
      tags = {"framework"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "base-quest"),
         @PandoraOptions.Meta(key = "lifecycle", value = "sequential-per-class")
      }
)
public class BaseQuestSequential extends BaseQuest {

   /**
    * The test service container for managing test-related dependencies.
    */
   private Services services;

   @Autowired
   public void setServices(Services services) {
      this.services = services;
   }

   /**
    * Executes the setup logic before all test methods in the class.
    */
   @BeforeAll
   protected final void beforeAll() {
      beforeAll(services);
   }

   /**
    * Hook method for pre-test setup logic.
    *
    * @param services The test service container.
    */
   @Pandora(
         description = "Override this to run setup once per test class when using BaseQuestSequential. "
               + "Called automatically by the framework before any @Test methods execute."
   )
   protected void beforeAll(Services services) {
      //empty method for beforeAll to override if needed
   }

   /**
    * Executes the cleanup logic after all test methods in the class.
    */
   @AfterAll
   protected final void afterAll() {
      afterAll(services);
   }

   /**
    * Hook method for post-test cleanup logic.
    *
    * @param services The test service container.
    */
   @Pandora(
         description = "Override this to run cleanup once per test class when using BaseQuestSequential. "
               + "Called automatically by the framework after all @Test methods have finished."
   )
   protected void afterAll(Services services) {
      //empty method for beforeAll to override if needed
   }

}
