package io.cyborgcode.roa.ui.pandora;

import io.cyborgcode.pandora.options.EnumsInPackageRule;
import io.cyborgcode.pandora.options.RuleContext;
import io.cyborgcode.roa.ui.components.accordion.AccordionComponentType;
import io.cyborgcode.roa.ui.components.alert.AlertComponentType;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.components.link.LinkComponentType;
import io.cyborgcode.roa.ui.components.list.ItemListComponentType;
import io.cyborgcode.roa.ui.components.loader.LoaderComponentType;
import io.cyborgcode.roa.ui.components.modal.ModalComponentType;
import io.cyborgcode.roa.ui.components.radio.RadioComponentType;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.components.tab.TabComponentType;
import io.cyborgcode.roa.ui.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.components.table.filters.FilterStrategy;
import io.cyborgcode.roa.ui.components.table.sort.SortingStrategy;
import io.cyborgcode.roa.ui.components.toggle.ToggleComponentType;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.Arrays;
import java.util.List;

/**
 * Provides Pandora option rules used to resolve available UI component types.
 *
 * <p>These rules integrate with the Pandora configuration engine to expose the
 * set of enum-based component types that can be selected when configuring UI
 * interactions for various widgets (accordion, alert, button, etc.).
 *
 * <p>Each nested rule class extends {@link EnumsInPackageRule} and declares the
 * component-type interface whose enums will be discovered and offered as
 * available options.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public final class AvailableOptionsRules {

   private AvailableOptionsRules() {
   }

   /**
    * Exposes enums implementing {@link AccordionComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableAccordionComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return AccordionComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes enums implementing {@link AlertComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableAlertComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return AlertComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes enums implementing {@link ButtonComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableButtonComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return ButtonComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes enums implementing {@link CheckboxComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableCheckboxComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return CheckboxComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes enums implementing {@link InputComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableInputComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return InputComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes enums implementing {@link LinkComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableLinkComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return LinkComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes enums implementing {@link ItemListComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableListComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return ItemListComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes enums implementing {@link LoaderComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableLoaderComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return LoaderComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes enums implementing {@link ModalComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableModalComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return ModalComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes enums implementing {@link RadioComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableRadioComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return RadioComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes enums implementing {@link SelectComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableSelectComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return SelectComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes enums implementing {@link TabComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableTabComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return TabComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes enums implementing {@link TableComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableTableComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return TableComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes enums implementing {@link ToggleComponentType} as available options.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableToggleComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return ToggleComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes all enums implementing {@link ComponentType} as available options for Pandora.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableComponentTypes extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ctx) {
         return ComponentType.class;
      }

      @Override
      protected boolean printEnumsAsStrings() {
         return true;
      }
   }

   /**
    * Exposes all enums inside {@link Strategy} as available options for Pandora.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableStrategyOptions extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return null;
      }

      @Override
      public List<String> findAvailableOptions(RuleContext ruleContext) {
         return Arrays.stream(Strategy.values())
               .map(Enum::name)
               .toList();
      }
   }

   /**
    * Exposes all enums inside {@link FilterStrategy} as available options for Pandora.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableFilterStrategyOptions extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return null;
      }

      @Override
      public List<String> findAvailableOptions(RuleContext ruleContext) {
         return Arrays.stream(FilterStrategy.values())
               .map(Enum::name)
               .toList();
      }
   }

   /**
    * Exposes all enums inside {@link SortingStrategy} as available options for Pandora.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class AvailableSortingStrategyOptions extends EnumsInPackageRule {

      @Override
      public Class<?> getInterface(RuleContext ruleContext) {
         return null;
      }

      @Override
      public List<String> findAvailableOptions(RuleContext ruleContext) {
         return Arrays.stream(SortingStrategy.values())
               .map(Enum::name)
               .toList();
      }
   }
}
