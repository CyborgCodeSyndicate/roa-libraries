package io.cyborgcode.roa.ui.components.checkbox;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Abstract base implementation for checkbox service operations.
 *
 * @param <E> The element/container type.
 * @param <C> The checkbox component interface type.
 * @param <D> The driver or page type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class CheckboxServiceImplCore<E extends BaseUiElement, C extends CheckboxCore<E, L>, D, L>
      extends AbstractComponentServiceCore<CheckboxComponentType, C, D>
      implements CheckboxServiceCore<E, L> {

   protected CheckboxServiceImplCore(final D driver) {
      super(driver);
   }

   protected C checkboxComponent(final CheckboxComponentType componentType) {
      return getOrCreateComponent(componentType);
   }

   public void select(final CheckboxComponentType componentType, final E container, final String... checkBoxText) {
      LogUi.step("Selecting checkbox");
      checkboxComponent(componentType).select(container, checkBoxText);
   }

   public void select(final CheckboxComponentType componentType, final E container, final Strategy strategy) {
      LogUi.step("Selecting checkbox");
      checkboxComponent(componentType).select(container, strategy);
   }

   public void select(final CheckboxComponentType componentType, final String... checkBoxText) {
      LogUi.step("Selecting checkbox");
      checkboxComponent(componentType).select(checkBoxText);
   }

   public void deSelect(final CheckboxComponentType componentType, final E container, final String... checkBoxText) {
      LogUi.step("Deselecting checkbox");
      checkboxComponent(componentType).deSelect(container, checkBoxText);
   }

   public void deSelect(final CheckboxComponentType componentType, final E container, final Strategy strategy) {
      LogUi.step("Deselecting checkbox");
      checkboxComponent(componentType).deSelect(container, strategy);
   }

   public void deSelect(final CheckboxComponentType componentType, final String... checkBoxText) {
      LogUi.step("Deselecting checkbox");
      checkboxComponent(componentType).deSelect(checkBoxText);
   }

   public boolean areSelected(final CheckboxComponentType componentType, final E container, final String... checkBoxText) {
      return checkboxComponent(componentType).areSelected(container, checkBoxText);
   }

   public boolean areSelected(final CheckboxComponentType componentType, final String... checkBoxText) {
      return checkboxComponent(componentType).areSelected(checkBoxText);
   }

   public boolean areEnabled(final CheckboxComponentType componentType, final E container, final String... checkBoxText) {
      return checkboxComponent(componentType).areEnabled(container, checkBoxText);
   }

   public boolean areEnabled(final CheckboxComponentType componentType, final String... checkBoxText) {
      return checkboxComponent(componentType).areEnabled(checkBoxText);
   }

   public List<String> getSelected(final CheckboxComponentType componentType, final E container) {
      return checkboxComponent(componentType).getSelected(container);
   }

   public List<String> getAll(final CheckboxComponentType componentType, final E container) {
      return checkboxComponent(componentType).getAll(container);
   }

   public void select(final CheckboxComponentType componentType, final L... checkBoxLocator) {
      LogUi.step("Selecting checkbox by locator");
      checkboxComponent(componentType).select(checkBoxLocator);
   }

   public void deSelect(final CheckboxComponentType componentType, final L... checkBoxLocator) {
      LogUi.step("Deselecting checkbox by locator");
      checkboxComponent(componentType).deSelect(checkBoxLocator);
   }

   public boolean areSelected(final CheckboxComponentType componentType, final L... checkBoxLocator) {
      return checkboxComponent(componentType).areSelected(checkBoxLocator);
   }

   public boolean areEnabled(final CheckboxComponentType componentType, final L... checkBoxLocator) {
      return checkboxComponent(componentType).areEnabled(checkBoxLocator);
   }

   public List<String> getSelected(final CheckboxComponentType componentType, final L containerLocator) {
      return checkboxComponent(componentType).getSelected(containerLocator);
   }

   public List<String> getAll(final CheckboxComponentType componentType, final L containerLocator) {
      return checkboxComponent(componentType).getAll(containerLocator);
   }

   @Override
   public void insertion(final ComponentType componentType, final L locator, final Object... values) {
      if (!(componentType instanceof CheckboxComponentType checkboxType)) {
         throw new IllegalArgumentException("Component type needs to be from: CheckboxComponentType.");
      }
      select(checkboxType, (String) values[0]);
   }

}
