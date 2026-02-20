package io.cyborgcode.roa.ui.components.checkbox;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxServiceCore;
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
public abstract class CheckboxServiceImplCore<E, C extends CheckboxCore<E>, D, L>
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

   @Override
   public void insertion(final ComponentType componentType, final L locator, final Object... values) {
      if (!(componentType instanceof CheckboxComponentType checkboxType)) {
         throw new IllegalArgumentException("Component type needs to be from: CheckboxComponentType.");
      }
      select(checkboxType, (String) values[0]);
   }

}
