package io.cyborgcode.roa.framework.base;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.annotation.Odyssey;
import io.cyborgcode.roa.framework.config.TestConfig;
import io.cyborgcode.roa.framework.log.LogQuest;
import io.cyborgcode.roa.framework.quest.QuestHolder;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import io.cyborgcode.roa.framework.storage.DataExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;

import static io.cyborgcode.roa.framework.util.PropertiesUtil.addSystemProperties;

/**
 * Base test class providing foundational test setup and utilities.
 *
 * <p>This class serves as the base for all test classes, handling global test configurations,
 * logging setup, and utility methods for retrieving stored test data.
 * It ensures consistent test initialization and provides convenience methods
 * for accessing stored test artifacts.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Odyssey
@SpringBootTest(
      classes = {TestConfig.class},
      webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@Tag("exclude-from-verify")
@Pandora(
      description = "Base test class for RoA-style quests. "
            + "Provides Spring Boot test wiring, logging setup "
            + "and convenience methods for retrieving data from quest storage.",
      tags = {"framework", "test-data"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/general-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "base-quest"),
         @PandoraOptions.Meta(key = "level", value = "framework")
      }
)
public class BaseQuest {

   private static final String RETRIEVAL_LOG_TEMPLATE = "Fetching data from storage by key: '{}' and type: '{}'";

   static {
      synchronized (BaseQuest.class) {
         addSystemProperties();
         LoggerContext context = (LoggerContext) LogManager.getContext(false);
         context.reconfigure();
      }
   }

   /**
    * Retrieves stored test data by key.
    *
    * @param key   The key identifying the stored data.
    * @param clazz The expected type of the retrieved object.
    * @param <T>   The type parameter corresponding to the retrieved object.
    * @return The stored test data of the specified type.
    */
   @Pandora(
         description = "Retrieve an object from quest storage by a single enum key in the default storage namespace."
   )
   protected <T> T retrieve(
         @Pandora(
               description = "Enum key under which the test data was stored in the default storage."
         ) Enum<?> key,
         Class<T> clazz) {
      SuperQuest quest = QuestHolder.get();
      LogQuest.extended(RETRIEVAL_LOG_TEMPLATE, key.name(), clazz.getName());
      return quest.getStorage().get(key, clazz);
   }

   /**
    * Retrieves stored test data from a sub-key.
    *
    * @param subKey The sub-key identifying the data subset.
    * @param key    The key identifying the stored data.
    * @param clazz  The expected type of the retrieved object.
    * @param <T>    The type parameter corresponding to the retrieved object.
    * @return The stored test data from the specified sub-key.
    */
   @Pandora(
         description = "Retrieve an object from a sub-storage "
               + "(e.g. API, DB) using a subKey (namespace) and entry enum key."
   )
   protected <T> T retrieve(
         @Pandora(
               description = "Top-level sub-storage key (e.g. API, DB) selecting a logical storage namespace."
         ) Enum<?> subKey,
         @Pandora(
               description = "Entry key inside the chosen sub-storage under which the object was stored."
         ) Enum<?> key,
         Class<T> clazz) {
      SuperQuest quest = QuestHolder.get();
      LogQuest.extended(RETRIEVAL_LOG_TEMPLATE, key.name(), clazz.getName());
      return quest.getStorage().sub(subKey).get(key, clazz);
   }

   /**
    * Retrieves test data using a {@code DataExtractor}.
    *
    * @param extractor The data extractor to retrieve test data.
    * @param clazz     The expected type of the retrieved object.
    * @param <T>       The type parameter corresponding to the retrieved object.
    * @return The extracted test data of the specified type.
    */
   @Pandora(
         description = "Retrieve an object using a DataExtractor abstraction (e.g. JSON path, DB selector)."
   )
   protected <T> T retrieve(
         @Pandora(
               description = "Extractor describing how to locate the data (JSON path, DB row selector, etc.)."
         ) DataExtractor<T> extractor,
         Class<T> clazz) {
      SuperQuest quest = QuestHolder.get();
      LogQuest.extended(RETRIEVAL_LOG_TEMPLATE, extractor.getKey().name(), clazz.getName());
      return quest.getStorage().get(extractor, clazz);
   }

   /**
    * Retrieves test data using a {@code DataExtractor} at a specified index.
    *
    * @param extractor The data extractor to retrieve test data.
    * @param index     The index of the extracted data.
    * @param clazz     The expected type of the retrieved object.
    * @param <T>       The type parameter corresponding to the retrieved object.
    * @return The extracted test data of the specified type at the given index.
    */
   @Pandora(
         description = "Retrieve an indexed element produced by a DataExtractor (e.g. element N in a list)."
   )
   protected <T> T retrieve(
         @Pandora(
               description = "Extractor describing how to locate the collection from which an element will be taken."
         ) DataExtractor<T> extractor,
         int index,
         Class<T> clazz) {
      SuperQuest quest = QuestHolder.get();
      LogQuest.extended(RETRIEVAL_LOG_TEMPLATE, extractor.getKey().name(), clazz.getName());
      return quest.getStorage().get(extractor, clazz, index);
   }

   /**
    * Retrieves data that was stored by a test hook (e.g., DbHook or ApiHook).
    *
    * <p>This method looks up hook-scoped data using the provided key object and
    * returns it as an instance of the specified class.</p>
    *
    * @param value the key object under which hook data was stored
    * @param clazz the expected type of the retrieved data
    * @param <T>   the type parameter of the returned data
    * @return the hook-stored data, cast to {@code T}
    */
   @Pandora(
         description = "Retrieve data written by framework hooks (e.g. ApiHook or DbHook) by their key object."
   )
   protected <T> T hookData(
         @Pandora(
               description = "Key object used by hooks when storing data (often an enum or dedicated key type)."
         ) Object value,
         Class<T> clazz) {
      SuperQuest quest = QuestHolder.get();
      LogQuest.extended(RETRIEVAL_LOG_TEMPLATE, value, clazz.getName());
      return quest.getStorage().getHookData(value, clazz);
   }

   /**
    * Provides static utility methods for retrieving stored test data.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static final class DefaultStorage {

      private DefaultStorage() {
      }

      /**
       * Retrieves stored test data by key within a sub-storage context.
       *
       * @param key   The key identifying the stored data.
       * @param clazz The expected type of the retrieved object.
       * @param <T>   The type parameter corresponding to the retrieved object.
       * @return The stored test data of the specified type.
       */
      public static <T> T retrieve(Enum<?> key, Class<T> clazz) {
         SuperQuest quest = QuestHolder.get();
         return quest.getStorage().sub().get(key, clazz);
      }

      /**
       * Retrieves test data using a {@code DataExtractor} within a sub-storage context.
       *
       * @param extractor The data extractor to retrieve test data.
       * @param clazz     The expected type of the retrieved object.
       * @param <T>       The type parameter corresponding to the retrieved object.
       * @return The extracted test data of the specified type.
       */
      public static <T> T retrieve(DataExtractor<T> extractor, Class<T> clazz) {
         SuperQuest quest = QuestHolder.get();
         return quest.getStorage().sub().get(extractor, clazz);
      }

   }

}
