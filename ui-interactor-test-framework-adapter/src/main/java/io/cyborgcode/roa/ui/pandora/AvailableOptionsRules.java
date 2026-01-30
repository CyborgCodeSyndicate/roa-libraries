package io.cyborgcode.roa.ui.pandora;

import io.cyborgcode.pandora.options.EnumsInPackageRule;
import io.cyborgcode.pandora.options.RuleContext;
import io.cyborgcode.roa.ui.parameters.DataIntercept;
import io.cyborgcode.roa.ui.selenium.AccordionUiElement;
import io.cyborgcode.roa.ui.selenium.AlertUiElement;
import io.cyborgcode.roa.ui.selenium.ButtonUiElement;
import io.cyborgcode.roa.ui.selenium.CheckboxUiElement;
import io.cyborgcode.roa.ui.selenium.InputUiElement;
import io.cyborgcode.roa.ui.selenium.LinkUiElement;
import io.cyborgcode.roa.ui.selenium.ListUiElement;
import io.cyborgcode.roa.ui.selenium.LoaderUiElement;
import io.cyborgcode.roa.ui.selenium.ModalUiElement;
import io.cyborgcode.roa.ui.selenium.RadioUiElement;
import io.cyborgcode.roa.ui.selenium.SelectUiElement;
import io.cyborgcode.roa.ui.selenium.TabUiElement;
import io.cyborgcode.roa.ui.selenium.ToggleUiElement;
import io.cyborgcode.roa.ui.service.tables.TableElement;

/**
 * Provides Pandora option rules used to resolve available UI element descriptors.
 *
 * <p>These rules integrate with the Pandora configuration engine to expose the set
 * of enum-based UI element descriptors that can be selected when configuring UI interactions
 * in the test framework adapter.
 *
 * <p>Each nested rule class extends {@link EnumsInPackageRule} and declares the interface whose
 * enums will be discovered and offered as available options.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public final class AvailableOptionsRules {

   private AvailableOptionsRules() {
   }

   /**
    * Exposes enums implementing {@link AccordionUiElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableAccordionUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return AccordionUiElement.class;
      }
   }

   /**
    * Exposes enums implementing {@link AlertUiElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableAlertUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return AlertUiElement.class;
      }
   }

   /**
    * Exposes enums implementing {@link ButtonUiElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableButtonUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return ButtonUiElement.class;
      }
   }

   /**
    * Exposes enums implementing {@link CheckboxUiElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableCheckboxUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return CheckboxUiElement.class;
      }
   }

   /**
    * Exposes enums implementing {@link InputUiElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableInputUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return InputUiElement.class;
      }
   }

   /**
    * Exposes enums implementing {@link LinkUiElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableLinkUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return LinkUiElement.class;
      }
   }

   /**
    * Exposes enums implementing {@link ListUiElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableListUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return ListUiElement.class;
      }
   }

   /**
    * Exposes enums implementing {@link LoaderUiElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableLoaderUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return LoaderUiElement.class;
      }
   }

   /**
    * Exposes enums implementing {@link ModalUiElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableModalUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return ModalUiElement.class;
      }
   }

   /**
    * Exposes enums implementing {@link RadioUiElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableRadioUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return RadioUiElement.class;
      }
   }

   /**
    * Exposes enums implementing {@link SelectUiElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableSelectUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return SelectUiElement.class;
      }
   }

   /**
    * Exposes enums implementing {@link TabUiElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableTabUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return TabUiElement.class;
      }
   }

   /**
    * Exposes enums implementing {@link TableElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableTableUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return TableElement.class;
      }
   }

   /**
    * Exposes enums implementing {@link ToggleUiElement} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableToggleUiElements extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return ToggleUiElement.class;
      }
   }

   /**
    * Exposes all enums implementing {@link DataIntercept} as available options for Pandora.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableDataInterceptOptions extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return DataIntercept.class;
      }
   }

}
