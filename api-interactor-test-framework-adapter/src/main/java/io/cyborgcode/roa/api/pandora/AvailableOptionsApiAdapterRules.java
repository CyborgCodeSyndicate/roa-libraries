package io.cyborgcode.roa.api.pandora;

import io.cyborgcode.pandora.options.EnumsInPackageRule;
import io.cyborgcode.pandora.options.RuleContext;
import io.cyborgcode.roa.api.hooks.ApiHookFlow;
import io.cyborgcode.roa.api.storage.StorageKeysApi;
import java.util.Arrays;
import java.util.List;

/**
 * Provides Pandora option rules for exposing selectable values to AI tooling.
 *
 * <p>These rules integrate with the Pandora configuration engine to discover and publish
 * "available options" for annotated elements (for example, enums implementing specific
 * framework contracts). Each nested rule class targets a single contract and defines how
 * implementations are discovered and rendered in the generated metadata.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class AvailableOptionsApiAdapterRules {

   private AvailableOptionsApiAdapterRules() {
   }

   /**
    * Rule implementation that exposes enums implementing {@link ApiHookFlow}
    * as available options within Pandora.
    *
    * <p>This enables {@code @ApiHook(type = "...")} (and related metadata)
    * to present a curated list of hook flows that can be selected by name.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableApiHookFlows extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return ApiHookFlow.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }

   }

   /**
    * Rule implementation that exposes {@link StorageKeysApi} constants as available options.
    *
    * <p>This rule returns a curated list of storage key names used by the API adapter. It is
    * implemented as an explicit value list (rather than interface scanning) so consumers can
    * reliably populate dropdowns or validators with the exact supported key set.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableStorageKeysApiOptions extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return null;
      }

      @Override
      public List<String> findAvailableOptions(RuleContext ruleContext) {
         return Arrays.stream(StorageKeysApi.values())
               .map(Enum::name)
               .toList();
      }
   }

}
