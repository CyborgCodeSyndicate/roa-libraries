package io.cyborgcode.roa.ui.components.radio;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.radio.RadioComponentType;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Abstract base implementation for radio button service operations.
 *
 * @param <E> The element/container type.
 * @param <C> The radio component interface type.
 * @param <D> The driver or page type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class RadioServiceImplCore<E, C extends RadioCore<E>, D, L>
      extends AbstractComponentServiceCore<RadioComponentType, C, D>
      implements RadioServiceCore<E, L> {

   protected RadioServiceImplCore(final D driver) {
      super(driver);
   }

   protected C radioComponent(final RadioComponentType componentType) {
      return getOrCreateComponent(componentType);
   }

   public void select(final RadioComponentType componentType, final E container, final String radioButtonText) {
      LogUi.step("Selecting radio: '{}'", radioButtonText);
      radioComponent(componentType).select(container, radioButtonText);
   }

   public void select(final RadioComponentType componentType, final E container, final Strategy strategy) {
      radioComponent(componentType).select(container, strategy);
   }

   public void select(final RadioComponentType componentType, final String radioButtonText) {
      LogUi.step("Selecting radio: '{}'", radioButtonText);
      radioComponent(componentType).select(radioButtonText);
   }

   public boolean isEnabled(final RadioComponentType componentType, final E container, final String radioButtonText) {
      return radioComponent(componentType).isEnabled(container, radioButtonText);
   }

   public boolean isEnabled(final RadioComponentType componentType, final String radioButtonText) {
      return radioComponent(componentType).isEnabled(radioButtonText);
   }

   public boolean isSelected(final RadioComponentType componentType, final E container, final String radioButtonText) {
      return radioComponent(componentType).isSelected(container, radioButtonText);
   }

   public boolean isSelected(final RadioComponentType componentType, final String radioButtonText) {
      return radioComponent(componentType).isSelected(radioButtonText);
   }

   public boolean isVisible(final RadioComponentType componentType, final E container, final String radioButtonText) {
      return radioComponent(componentType).isVisible(container, radioButtonText);
   }

   public boolean isVisible(final RadioComponentType componentType, final String radioButtonText) {
      return radioComponent(componentType).isVisible(radioButtonText);
   }

   public String getSelected(final RadioComponentType componentType, final E container) {
      return radioComponent(componentType).getSelected(container);
   }

   public List<String> getAll(final RadioComponentType componentType, final E container) {
      return radioComponent(componentType).getAll(container);
   }
}
