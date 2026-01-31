package io.cyborgcode.roa.api.pandora;

import io.cyborgcode.pandora.options.EnumsInPackageRule;
import io.cyborgcode.pandora.options.RuleContext;
import io.cyborgcode.roa.api.hooks.ApiHookFlow;

public class AvailableOptionsRules {

   private AvailableOptionsRules() {
   }

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

}
