package io.cyborgcode.roa.ui.components.alert;

import io.cyborgcode.roa.ui.components.alert.AlertComponentType;
import io.cyborgcode.roa.ui.components.alert.AlertCore;
import io.cyborgcode.roa.ui.components.alert.AlertServiceCore;
import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;

/**
 * Abstract base implementation for alert service operations.
 *
 * @param <E> The element/container type.
 * @param <C> The alert component interface type.
 * @param <D> The driver or page type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class AlertServiceImplCore<E extends BaseUiElement, C extends AlertCore<E, L>, D, L>
      extends AbstractComponentServiceCore<AlertComponentType, C, D>
      implements AlertServiceCore<E, L> {

   protected AlertServiceImplCore(final D driver) {
      super(driver);
   }

   protected C alertComponent(final AlertComponentType componentType) {
      return getOrCreateComponent(componentType);
   }

   public String getValue(final AlertComponentType componentType, final E container) {
      return alertComponent(componentType).getValue(container);
   }

   public boolean isVisible(final AlertComponentType componentType, final E container) {
      return alertComponent(componentType).isVisible(container);
   }

   public String getValue(final AlertComponentType componentType, final L containerLocator) {
      return alertComponent(componentType).getValue(containerLocator);
   }

   public boolean isVisible(final AlertComponentType componentType, final L containerLocator) {
      return alertComponent(componentType).isVisible(containerLocator);
   }

}
