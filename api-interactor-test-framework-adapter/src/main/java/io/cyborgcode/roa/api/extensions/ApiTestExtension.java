package io.cyborgcode.roa.api.extensions;

import io.cyborgcode.roa.api.annotations.AuthenticateViaApiAs;
import io.cyborgcode.roa.api.authentication.BaseAuthenticationClient;
import io.cyborgcode.roa.api.authentication.Credentials;
import io.cyborgcode.roa.api.log.LogApi;
import io.cyborgcode.roa.api.service.fluent.RestServiceFluent;
import io.cyborgcode.roa.api.service.fluent.SuperRestServiceFluent;
import io.cyborgcode.roa.framework.allure.CustomAllureListener;
import io.cyborgcode.roa.framework.decorators.DecoratorsFactory;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import io.cyborgcode.roa.framework.storage.StoreKeys;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.cyborgcode.roa.api.storage.StorageKeysApi.API;
import static io.cyborgcode.roa.api.storage.StorageKeysApi.PASSWORD;
import static io.cyborgcode.roa.api.storage.StorageKeysApi.USERNAME;
import static io.cyborgcode.roa.framework.allure.StepType.PERFORMING_PRE_QUEST_AUTHENTICATION;

/**
 * Handles API authentication for test execution.
 *
 * <p>This JUnit 5 extension processes the {@link AuthenticateViaApiAs} annotation
 * to authenticate users before API tests.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ApiTestExtension implements BeforeTestExecutionCallback {

   /**
    * Executes authentication before test execution.
    *
    * @param context The test execution context.
    * @throws Exception If authentication fails.
    */
   @Override
   public void beforeTestExecution(final ExtensionContext context) throws Exception {
      context.getTestMethod()
            .map(method -> method.getAnnotation(AuthenticateViaApiAs.class))
            .ifPresent(annotation -> handleAuthentication(context, annotation));
   }

   private void handleAuthentication(final ExtensionContext context, final AuthenticateViaApiAs annotation) {
      try {
         Credentials credentials = annotation.credentials().getDeclaredConstructor().newInstance();
         ApplicationContext appCtx = SpringExtension.getApplicationContext(context);
         DecoratorsFactory decoratorsFactory = appCtx.getBean(DecoratorsFactory.class);

         Consumer<SuperQuest> questConsumer = createQuestConsumer(decoratorsFactory,
               credentials.username(),
               credentials.password(),
               annotation.type(),
               annotation.cacheCredentials()
         );

         addConsumerToStore(context, questConsumer);

      } catch (InstantiationException | IllegalAccessException | InvocationTargetException
               | NoSuchMethodException e) {
         throw new IllegalStateException("Failed to instantiate credentials.", e);
      }
   }

   private Consumer<SuperQuest> createQuestConsumer(DecoratorsFactory decoratorsFactory, final String username,
                                                    final String password,
                                                    final Class<? extends BaseAuthenticationClient> clientType,
                                                    final boolean cacheCredentials) {
      if (!CustomAllureListener.isStepActive(PERFORMING_PRE_QUEST_AUTHENTICATION.getDisplayName())) {
         CustomAllureListener.startStep(PERFORMING_PRE_QUEST_AUTHENTICATION);
      }
      return (SuperQuest quest) -> {
         quest.getStorage().sub(API).put(USERNAME, username);
         quest.getStorage().sub(API).put(PASSWORD, password);

         LogApi.extended("Updated quest storage with credentials for username: {}", username);
         RestServiceFluent restServiceFluent = quest.enters(RestServiceFluent.class);
         SuperRestServiceFluent superRestServiceFluent =
               decoratorsFactory.decorate(restServiceFluent, SuperRestServiceFluent.class);
         superRestServiceFluent.getRestService().setCacheAuthentication(cacheCredentials);
         restServiceFluent.authenticate(username, password, clientType);
         if (CustomAllureListener.isStepActive(PERFORMING_PRE_QUEST_AUTHENTICATION.getDisplayName())) {
            CustomAllureListener.stopStep();
         }
      };
   }

   private void addConsumerToStore(final ExtensionContext context, final Consumer<SuperQuest> questConsumer) {
      @SuppressWarnings("unchecked")
      List<Consumer<SuperQuest>> consumers =
            (List<Consumer<SuperQuest>>) context.getStore(ExtensionContext.Namespace.GLOBAL)
                  .computeIfAbsent(StoreKeys.QUEST_CONSUMERS, key -> new ArrayList<Consumer<SuperQuest>>());

      consumers.add(questConsumer);
   }
}
