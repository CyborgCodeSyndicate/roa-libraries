package io.cyborgcode.roa.db.pandora;

import io.cyborgcode.pandora.options.EnumsInPackageRule;
import io.cyborgcode.pandora.options.RuleContext;
import io.cyborgcode.roa.db.query.DbQuery;

/**
 * Provides Pandora option rules used to resolve available endpoint definitions.
 *
 * <p>These rules integrate with the Pandora configuration engine to expose the
 * set of enum-based endpoints that can be selected when configuring API
 * interactions.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public final class AvailableOptionsRules {

   private AvailableOptionsRules() {
   }

   /**
    * Rule implementation that exposes enums implementing the {@link DbQuery}
    * interface as available options within Pandora.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableDbQueries extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return DbQuery.class;
      }
   }

}
