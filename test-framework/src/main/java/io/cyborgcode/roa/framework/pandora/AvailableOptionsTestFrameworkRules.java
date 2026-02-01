package io.cyborgcode.roa.framework.pandora;

import io.cyborgcode.pandora.options.EnumsInPackageRule;
import io.cyborgcode.pandora.options.RuleContext;
import io.cyborgcode.roa.framework.hooks.HookExecution;
import io.cyborgcode.roa.framework.parameters.DataForge;
import io.cyborgcode.roa.framework.parameters.DataRipper;
import io.cyborgcode.roa.framework.parameters.PreQuestJourney;
import io.cyborgcode.roa.framework.storage.StorageKeysTest;
import io.cyborgcode.roa.framework.storage.StoreKeys;
import java.util.Arrays;
import java.util.List;

/**
 * Pandora option rules for framework-level registries (data forge, cleanup, and preconditions).
 *
 * <p>These rules expose enum implementations of core framework contracts so Pandora can offer
 * valid selectable options in annotations and configuration.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public final class AvailableOptionsTestFrameworkRules {

   private AvailableOptionsTestFrameworkRules() {
   }

   /**
    * Exposes all enums implementing {@link DataForge} as available options for Pandora.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableDataForgeOptions extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return DataForge.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes all enums implementing {@link DataRipper} as available options for Pandora.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableDataRipperOptions extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return DataRipper.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes all enums implementing {@link PreQuestJourney} as available options for Pandora.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailablePreQuestJourneyOptions extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return PreQuestJourney.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes all {@link HookExecution} constants as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableHookExecutionOptions extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return null;
      }

      @Override
      public List<String> findAvailableOptions(RuleContext ruleContext) {
         return Arrays.stream(HookExecution.values())
               .map(Enum::name)
               .toList();
      }
   }

   /**
    * Exposes all {@link StorageKeysTest} constants as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableStorageKeysTestOptions extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return null;
      }

      @Override
      public List<String> findAvailableOptions(RuleContext ruleContext) {
         return Arrays.stream(StorageKeysTest.values())
               .map(Enum::name)
               .toList();
      }
   }

   /**
    * Exposes all {@link StoreKeys} constants as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableStoreKeysTestOptions extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return null;
      }

      @Override
      public List<String> findAvailableOptions(RuleContext ruleContext) {
         return Arrays.stream(StoreKeys.values())
               .map(Enum::name)
               .toList();
      }
   }
}
