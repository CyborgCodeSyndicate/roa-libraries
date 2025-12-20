package io.cyborgcode.roa.validator.pandora;

import io.cyborgcode.pandora.options.EnumsInPackageRule;
import io.cyborgcode.pandora.options.RuleContext;
import io.cyborgcode.roa.validator.core.AssertionTarget;
import io.cyborgcode.roa.validator.core.AssertionType;

/**
 * Provides Pandora option rules for assertion-related enums.
 *
 * <p>These rules are used by Pandora to expose all available assertion targets
 * and assertion types as selectable options when building validation steps
 * (e.g. {@code STATUS}, {@code BODY}, {@code IS}, {@code CONTAINS}, etc.).</p>
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public final class AvailableOptionsRules {

   private AvailableOptionsRules() {
   }

   /**
    * Pandora rule that exposes all enums implementing {@link AssertionTarget}
    * as available assertion targets.
    *
    * <p>Typical implementations include API-specific targets such as
    * {@code STATUS}, {@code BODY}, and {@code HEADER}, but the rule also
    * supports additional domains (DB, UI) as long as they implement
    * {@link AssertionTarget}.</p>
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static final class AvailableAssertionTargets extends EnumsInPackageRule {

      /**
       * Returns the marker interface used to discover assertion target enums.
       *
       * @param ruleContext the Pandora rule context (unused here but part of the contract)
       * @return {@link AssertionTarget} as the interface to scan for enums
       */
      @Override
      public Class<?> getInterface(final RuleContext ruleContext) {
         return AssertionTarget.class;
      }
   }

   /**
    * Pandora rule that exposes all enums implementing {@link AssertionType}
    * as available assertion types.
    *
    * <p>This includes operations such as {@code IS}, {@code CONTAINS},
    * {@code BETWEEN}, {@code MATCHES_REGEX}, and any custom assertion type
    * enums used across ROA.</p>
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static final class AvailableAssertionTypes extends EnumsInPackageRule {

      /**
       * Returns the marker interface used to discover assertion type enums.
       *
       * @param ruleContext the Pandora rule context (unused here but part of the contract)
       * @return {@link AssertionType} as the interface to scan for enums
       */
      @Override
      public Class<?> getInterface(final RuleContext ruleContext) {
         return AssertionType.class;
      }
   }

}
