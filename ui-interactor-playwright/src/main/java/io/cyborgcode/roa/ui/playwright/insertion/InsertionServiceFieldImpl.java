package io.cyborgcode.roa.ui.playwright.insertion;

import io.cyborgcode.roa.ui.playwright.annotations.InsertionField;
import io.cyborgcode.roa.ui.playwright.annotations.PlaywrightLocator;
import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

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
public class InsertionServiceFieldImpl extends BaseInsertionService<InsertionField> {

   public InsertionServiceFieldImpl(final InsertionServiceRegistry serviceRegistry) {
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
   protected String buildSelector(final InsertionField annotation) {
      return resolveSelector(annotation.locator());
   }

   @Override
   protected ComponentType getType(final InsertionField annotation) {
      final String componentTypeEnumName = annotation.componentType();
      final Class<? extends ComponentType> typeClass = annotation.type();
      return ReflectionUtil.findEnumImplementationsOfInterface(
            typeClass, componentTypeEnumName, getPlaywrightConfig().projectPackages()
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
   private String resolveSelector(final PlaywrightLocator locator) {
      if (!locator.selector().isEmpty()) {
         return locator.selector();
      }
      if (!locator.css().isEmpty()) {
         return locator.css();
      }
      if (!locator.xpath().isEmpty()) {
         return "xpath=" + locator.xpath();
      }
      if (!locator.testId().isEmpty()) {
         return "[data-testid='" + locator.testId() + "']";
      }
      if (!locator.text().isEmpty()) {
         return "text=" + locator.text();
      }
      return "";
   }

}
