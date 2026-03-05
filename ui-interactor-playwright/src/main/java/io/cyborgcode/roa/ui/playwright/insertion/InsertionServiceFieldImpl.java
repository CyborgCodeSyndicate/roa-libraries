package io.cyborgcode.roa.ui.playwright.insertion;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.insertion.BaseInsertionService;
import io.cyborgcode.roa.ui.insertion.InsertionServiceRegistry;
import io.cyborgcode.roa.ui.playwright.annotations.InsertionField;
import io.cyborgcode.roa.ui.playwright.annotations.PlaywrightLocator;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Service implementation for processing fields annotated with {@link InsertionField}.
 *
 * <p>This class inspects the {@code InsertionField} annotation on object fields,
 * builds the appropriate Playwright selector, determines the target
 * {@link ComponentType} enum, and delegates to the registered insertion service
 * to insert values into the UI component.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class InsertionServiceFieldImpl extends BaseInsertionService<InsertionField, PwBy> {

   public InsertionServiceFieldImpl(final InsertionServiceRegistry<PwBy> serviceRegistry) {
      super(serviceRegistry);
   }

   @Override
   protected Class<InsertionField> getAnnotationClass() {
      return InsertionField.class;
   }

   @Override
   protected int getOrder(final InsertionField annotation) {
      return annotation.order();
   }

   @Override
   protected Class<? extends ComponentType> getComponentTypeEnumClass(final InsertionField annotation) {
      return annotation.type();
   }

   @Override
   protected PwBy buildLocator(final InsertionField annotation) {
      return resolveSelector(annotation.locator());
   }

   @Override
   protected ComponentType getType(final InsertionField annotation) {
      final String componentTypeEnumName = annotation.componentType();
      final Class<? extends ComponentType> typeClass = annotation.type();
      return ReflectionUtil.findEnumImplementationsOfInterface(
            typeClass, componentTypeEnumName, getUiConfig().projectPackages()
      );
   }

   /**
    * Resolves a Playwright selector from the {@link PlaywrightLocator} annotation.
    *
    * <p>Priority order: selector > css > xpath > testId > text
    *
    * @param locator The PlaywrightLocator annotation.
    * @return The resolved selector string.
    */
   private PwBy resolveSelector(final PlaywrightLocator locator) {
      if (!locator.selector().isEmpty()) {
         return PwBy.playwright(locator.selector());
      }
      if (!locator.css().isEmpty()) {
         return PwBy.playwright(locator.css());
      }
      if (!locator.xpath().isEmpty()) {
         return PwBy.playwright("xpath=" + locator.xpath());
      }
      if (!locator.testId().isEmpty()) {
         return PwBy.playwright("[data-testid='" + locator.testId() + "']");
      }
      if (!locator.text().isEmpty()) {
         return PwBy.playwright("text=" + locator.text());
      }
      return null;
   }

}
