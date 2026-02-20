package io.cyborgcode.roa.ui.components.toggle;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.toggle.ToggleCore;
import io.cyborgcode.roa.ui.components.toggle.ToggleServiceCore;
import io.cyborgcode.roa.ui.log.LogUi;

/**
 * Abstract base implementation for toggle service operations.
 *
 * @param <E> The element/container type.
 * @param <C> The toggle component interface type.
 * @param <D> The driver or page type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class ToggleServiceImplCore<E, C extends ToggleCore<E>, D>
      extends AbstractComponentServiceCore<ToggleComponentType, C, D>
      implements ToggleServiceCore<E> {

   protected ToggleServiceImplCore(final D driver) {
      super(driver);
   }

   protected C toggleComponent(final ToggleComponentType componentType) {
      return getOrCreateComponent(componentType);
   }

   public void activate(final ToggleComponentType componentType, final E container, final String toggleText) {
      LogUi.step("Activating toggle: '{}'", toggleText);
      toggleComponent(componentType).activate(container, toggleText);
   }

   public void activate(final ToggleComponentType componentType, final String toggleText) {
      LogUi.step("Activating toggle: '{}'", toggleText);
      toggleComponent(componentType).activate(toggleText);
   }

   public void deactivate(final ToggleComponentType componentType, final E container, final String toggleText) {
      LogUi.step("Deactivating toggle: '{}'", toggleText);
      toggleComponent(componentType).deactivate(container, toggleText);
   }

   public void deactivate(final ToggleComponentType componentType, final String toggleText) {
      LogUi.step("Deactivating toggle: '{}'", toggleText);
      toggleComponent(componentType).deactivate(toggleText);
   }

   public boolean isEnabled(final ToggleComponentType componentType, final E container, final String toggleText) {
      return toggleComponent(componentType).isEnabled(container, toggleText);
   }

   public boolean isEnabled(final ToggleComponentType componentType, final String toggleText) {
      return toggleComponent(componentType).isEnabled(toggleText);
   }

   public boolean isActivated(final ToggleComponentType componentType, final E container, final String toggleText) {
      return toggleComponent(componentType).isActivated(container, toggleText);
   }

   public boolean isActivated(final ToggleComponentType componentType, final String toggleText) {
      return toggleComponent(componentType).isActivated(toggleText);
   }
}
