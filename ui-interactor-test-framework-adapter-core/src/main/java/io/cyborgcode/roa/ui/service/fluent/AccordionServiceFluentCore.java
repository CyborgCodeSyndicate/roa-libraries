package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.accordion.AccordionComponentType;
import io.cyborgcode.roa.ui.components.accordion.AccordionServiceCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.elements.AccordionUiElementCore;
import io.qameta.allure.Allure;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with accordion components.
 *
 * @param <T> Represents the fluent UI service that extends {@link UiServiceFluentCore}.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
public class AccordionServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private final AccordionServiceCore<E, L> accordionService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;

   public AccordionServiceFluentCore(T uiServiceFluent, Storage storage,
                                     AccordionServiceCore<E, L> accordionService, D webDriver) {
      this.accordionService = accordionService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   public T expand(final AccordionUiElementCore<L, D> element) {
      Allure.step("[UI - Accordion] Expanding accordion: " + element);
      element.before().accept(driver);
      accordionService.expand((AccordionComponentType) element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T collapse(final AccordionUiElementCore<L, D> element) {
      Allure.step("[UI - Accordion] Collapsing accordion: " + element);
      element.before().accept(driver);
      accordionService.collapse((AccordionComponentType) element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T areEnabled(final AccordionUiElementCore<L, D> element) {
      Allure.step("[UI - Accordion] Checking if accordion items are enabled: " + element);
      element.before().accept(driver);
      boolean enabled = accordionService.areEnabled((AccordionComponentType)element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   public T validateAreEnabled(final AccordionUiElementCore<L, D> element) {
      return validateAreEnabled(element, true, false);
   }

   public T validateAreEnabled(final AccordionUiElementCore<L, D> element, boolean soft) {
      return validateAreEnabled(element, true, soft);
   }

   private T validateAreEnabled(final AccordionUiElementCore<L, D> element, boolean shouldBeEnabled, boolean soft) {
      Allure.step("[UI - Accordion] Validating accordion items are "
            + (shouldBeEnabled ? "enabled" : "disabled") + ": " + element);
      element.before().accept(driver);
      boolean enabled = accordionService.areEnabled((AccordionComponentType) element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);

      String assertionMessage = shouldBeEnabled
            ? "Validating accordion items are enabled"
            : "Validating accordion items are disabled";

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> {
                  if (shouldBeEnabled) {
                     softAssertions.assertThat(enabled).as(assertionMessage).isTrue();
                  } else {
                     softAssertions.assertThat(enabled).as(assertionMessage).isFalse();
                  }
               }
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> {
                  if (shouldBeEnabled) {
                     Assertions.assertThat(enabled).as(assertionMessage).isTrue();
                  } else {
                     Assertions.assertThat(enabled).as(assertionMessage).isFalse();
                  }
               }
         );
      }
   }

   public T validateAreDisabled(final AccordionUiElementCore<L, D> element) {
      return validateAreEnabled(element, false, false);
   }

   public T validateAreDisabled(final AccordionUiElementCore<L, D> element, boolean soft) {
      return validateAreEnabled(element, false, soft);
   }

   public T getExpanded(final AccordionUiElementCore<L, D> element) {
      Allure.step("[UI - Accordion] Retrieving expanded items: " + element);
      element.before().accept(driver);
      List<String> expandedItems = accordionService.getExpanded(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), expandedItems);
      return uiServiceFluent;
   }

   public T validateExpandedItems(final AccordionUiElementCore<L, D> element, final String... expectedValues) {
      return validateExpandedItems(element, false, expectedValues);
   }

   public T validateExpandedItems(final AccordionUiElementCore<L, D> element, boolean soft,
                                  final String... expectedValues) {
      Allure.step("[UI - Accordion] Validating expanded items: " + element);
      element.before().accept(driver);
      List<String> expandedItems = accordionService.getExpanded(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), expandedItems);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(expandedItems)
                     .as("Validating expanded accordion items")
                     .containsAll(Arrays.asList(expectedValues))
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(expandedItems)
                     .as("Validating expanded accordion items")
                     .containsAll(Arrays.asList(expectedValues))
         );
      }
   }

   public T getCollapsed(final AccordionUiElementCore<L, D> element) {
      Allure.step("[UI - Accordion] Retrieving collapsed items: " + element);
      element.before().accept(driver);
      List<String> collapsedItems = accordionService.getCollapsed(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), collapsedItems);
      return uiServiceFluent;
   }

   public T validateCollapsedItems(final AccordionUiElementCore<L, D> element, final String... expectedValues) {
      return validateCollapsedItems(element, false, expectedValues);
   }

   public T validateCollapsedItems(final AccordionUiElementCore<L, D> element, boolean soft,
                                   final String... expectedValues) {
      Allure.step("[UI - Accordion] Validating collapsed items: " + element);
      element.before().accept(driver);
      List<String> collapsedItems = accordionService.getCollapsed(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), collapsedItems);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(collapsedItems)
                     .as("Validating collapsed accordion items")
                     .containsAll(Arrays.asList(expectedValues))
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(collapsedItems)
                     .as("Validating collapsed accordion items")
                     .containsAll(Arrays.asList(expectedValues))
         );
      }
   }

   public T getAll(final AccordionUiElementCore<L, D> element) {
      Allure.step("[UI - Accordion] Retrieving all accordion items: " + element);
      element.before().accept(driver);
      List<String> allItems = accordionService.getAll(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), allItems);
      return uiServiceFluent;
   }

   public T validateAllItems(final AccordionUiElementCore<L, D> element, final String... expectedValues) {
      return validateAllItems(element, false, expectedValues);
   }

   public T validateAllItems(final AccordionUiElementCore<L, D> element, boolean soft,
                             final String... expectedValues) {
      Allure.step("[UI - Accordion] Validating all accordion items: " + element);
      element.before().accept(driver);
      List<String> allItems = accordionService.getAll(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), allItems);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(allItems)
                     .as("Validating all accordion items")
                     .containsAll(Arrays.asList(expectedValues))
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(allItems)
                     .as("Validating all accordion items")
                     .containsAll(Arrays.asList(expectedValues))
         );
      }
   }
}
