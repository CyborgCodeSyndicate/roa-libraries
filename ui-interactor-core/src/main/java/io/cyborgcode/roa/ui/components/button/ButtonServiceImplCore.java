package io.cyborgcode.roa.ui.components.button;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonCore;
import io.cyborgcode.roa.ui.log.LogUi;

/**
 * Abstract base implementation for button service operations.
 *
 * @param <E> The element/container type.
 * @param <C> The button component interface type.
 * @param <D> The driver or page type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class ButtonServiceImplCore<E extends BaseUiElement, C extends ButtonCore<E, L>, D, L>
      extends AbstractComponentServiceCore<ButtonComponentType, C, D>
      implements ButtonServiceCore<E, L> {

   protected ButtonServiceImplCore(final D driver) {
      super(driver);
   }

   protected C buttonComponent(final ButtonComponentType componentType) {
      return getOrCreateComponent(componentType);
   }

   public void click(final ButtonComponentType componentType, final E container, final String buttonText) {
      LogUi.step("Clicking button: '{}' in container", buttonText);
      buttonComponent(componentType).click(container, buttonText);
   }

   public void click(final ButtonComponentType componentType, final E container) {
      LogUi.step("Clicking button in container");
      buttonComponent(componentType).click(container);
   }

   public void click(final ButtonComponentType componentType, final L buttonSelector) {
      LogUi.step("Clicking button by selector");
      buttonComponent(componentType).click(buttonSelector);
   }

   public void click(final ButtonComponentType componentType, final String buttonText) {
      LogUi.step("Clicking button: '{}'", buttonText);
      buttonComponent(componentType).click(buttonText);
   }

   public boolean isEnabled(final ButtonComponentType componentType, final E container, final String buttonText) {
      LogUi.step("Checking if button is enabled: '{}'", buttonText);
      return buttonComponent(componentType).isEnabled(container, buttonText);
   }

   public boolean isEnabled(final ButtonComponentType componentType, final E container) {
      LogUi.step("Checking if button is enabled in container");
      return buttonComponent(componentType).isEnabled(container);
   }

   public boolean isEnabled(final ButtonComponentType componentType, final L buttonSelector) {
      LogUi.step("Checking if button is enabled by selector");
      return buttonComponent(componentType).isEnabled(buttonSelector);
   }

   public boolean isEnabled(final ButtonComponentType componentType, final String buttonText) {
      LogUi.step("Checking if button is enabled: '{}'", buttonText);
      return buttonComponent(componentType).isEnabled(buttonText);
   }


   public boolean isVisible(final ButtonComponentType componentType, final E container, final String buttonText) {
      LogUi.step("Checking if button is visible: '{}'", buttonText);
      return buttonComponent(componentType).isVisible(container, buttonText);
   }

   public boolean isVisible(final ButtonComponentType componentType, final E container) {
      LogUi.step("Checking if button is visible in container");
      return buttonComponent(componentType).isVisible(container);
   }

   public boolean isVisible(final ButtonComponentType componentType, final L buttonSelector) {
      LogUi.step("Checking if button is visible in container");
      return buttonComponent(componentType).isVisible(buttonSelector);
   }

   public boolean isVisible(final ButtonComponentType componentType, final String buttonText) {
      LogUi.step("Checking if button is visible: '{}'", buttonText);
      return buttonComponent(componentType).isVisible(buttonText);
   }

   @Override
   public void tableInsertion(E cellElement, ComponentType componentType, String... values) {
      if (!(componentType instanceof ButtonComponentType buttonType)) {
         throw new IllegalArgumentException("Component type needs to be from: ButtonComponentType.");
      }
      LogUi.step("Performing table insertion in cell element");
      buttonComponent(buttonType).clickElementInCell(cellElement);
   }

}
