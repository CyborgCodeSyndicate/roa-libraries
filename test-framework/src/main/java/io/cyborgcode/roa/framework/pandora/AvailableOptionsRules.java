package io.cyborgcode.roa.framework.pandora;

import io.cyborgcode.pandora.options.EnumsInPackageRule;
import io.cyborgcode.pandora.options.RuleContext;
import io.cyborgcode.roa.framework.parameters.DataForge;
import io.cyborgcode.roa.framework.parameters.DataRipper;
import io.cyborgcode.roa.framework.parameters.PreQuestJourney;

/**
 * Pandora option rules for framework-level registries (data forge, cleanup, and preconditions).
 *
 * <p>These rules expose enum implementations of core framework contracts so Pandora can offer
 * valid selectable options in annotations and configuration.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public final class AvailableOptionsRules {

   private AvailableOptionsRules() {
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
}
