package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxServiceCore;
import io.cyborgcode.roa.ui.elements.CheckboxUiElementCore;
import io.qameta.allure.Allure;
import java.util.List;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with checkbox components.
 *
 * @param <T> Represents the fluent UI service that extends {@link UiServiceFluentCore}.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
public class CheckboxServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private final CheckboxServiceCore<E, L> checkboxService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;

   public CheckboxServiceFluentCore(T uiServiceFluent, Storage storage, CheckboxServiceCore<E, L> checkboxService,
                                    D webDriver) {
      this.checkboxService = checkboxService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   public T select(final CheckboxUiElementCore<L, D> element) {
      Allure.step("[UI - Checkbox] Select checkbox with element: " + element);
      checkboxService.select((CheckboxComponentType) element.componentType(), element.locator());
      return uiServiceFluent;
   }

   public T deSelect(final CheckboxUiElementCore<L, D> element) {
      Allure.step("[UI - Checkbox] Deselect checkbox with element: " + element);
      checkboxService.deSelect((CheckboxComponentType) element.componentType(), element.locator());
      return uiServiceFluent;
   }

   public T isSelected(final CheckboxUiElementCore<L, D> element) {
      Allure.step("[UI - Checkbox] Check if checkbox with element " + element + " is selected");
      boolean selected =
            checkboxService.areSelected((CheckboxComponentType) element.componentType(), element.locator());
      storage.sub(UI).put(element.enumImpl(), selected);
      return uiServiceFluent;
   }

   public T validateIsSelected(final CheckboxUiElementCore<L, D> element) {
      return validateIsSelected(element, false);
   }

   public T validateIsSelected(final CheckboxUiElementCore<L, D> element, boolean soft) {
      Allure.step("[UI - Checkbox] Validate if checkbox with element " + element + " is selected");
      element.before().accept(driver);
      boolean selected =
            checkboxService.areSelected((CheckboxComponentType) element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selected);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(selected)
                     .as("Validating checkbox Selected").isTrue()
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(selected)
                     .as("Validating checkbox Selected").isTrue()
         );
      }
   }

   public T areSelected(final CheckboxUiElementCore<L, D> element) {
      Allure.step("[UI - Checkbox] Check if multiple checkboxes with element " + element + " are selected");
      boolean selected =
            checkboxService.areSelected((CheckboxComponentType) element.componentType(), element.locator());
      storage.sub(UI).put(element.enumImpl(), selected);
      return uiServiceFluent;
   }

   public T isEnabled(final CheckboxUiElementCore<L, D> element) {
      Allure.step("[UI - Checkbox] Check if checkbox with element " + element + " is enabled");
      boolean enabled = checkboxService.areEnabled((CheckboxComponentType) element.componentType(), element.locator());
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   public T validateIsEnabled(final CheckboxUiElementCore<L, D> element) {
      return validateIsEnabled(element, false);
   }

   public T validateIsEnabled(final CheckboxUiElementCore<L, D> element, boolean soft) {
      Allure.step("[UI - Checkbox] Validate if checkbox with element " + element + " is enabled");
      element.before().accept(driver);
      boolean enabled = checkboxService.areEnabled((CheckboxComponentType) element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(enabled)
                     .as("Validating checkbox Enabled").isTrue()
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(enabled)
                     .as("Validating checkbox Enabled").isTrue()
         );
      }
   }

   public T areEnabled(final CheckboxUiElementCore<L, D> element) {
      Allure.step("[UI - Checkbox] Check if multiple checkboxes with element " + element + " are enabled");
      boolean enabled = checkboxService.areEnabled((CheckboxComponentType) element.componentType(), element.locator());
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   public T getSelected(final CheckboxUiElementCore<L, D> element) {
      Allure.step("[UI - Checkbox] Retrieve the selected values from checkbox " + element);
      List<String> selectedValues = checkboxService.getSelected(element.componentType(), element.locator());
      storage.sub(UI).put(element.enumImpl(), selectedValues);
      return uiServiceFluent;
   }

   public T getAll(final CheckboxUiElementCore<L, D> element) {
      Allure.step("[UI - Checkbox] Retrieve all checkbox values from " + element);
      element.before().accept(driver);
      List<String> allItems = checkboxService.getAll(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), allItems);
      return uiServiceFluent;
   }
}
