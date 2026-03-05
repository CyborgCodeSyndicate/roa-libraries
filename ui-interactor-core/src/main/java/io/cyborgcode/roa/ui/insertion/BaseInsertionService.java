package io.cyborgcode.roa.ui.insertion;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.utilities.reflections.exceptions.ReflectionException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Abstract base class for insertion services that handle inserting data into UI components.
 *
 * <p>This class defines a generic mechanism to process annotated fields in an object
 * and insert corresponding values into the UI using registered insertion services.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class BaseInsertionService<A extends Annotation, L> implements InsertionService {

   protected final InsertionServiceRegistry<L> serviceRegistry;

   protected BaseInsertionService(final InsertionServiceRegistry<L> serviceRegistry) {
      this.serviceRegistry = serviceRegistry;
   }

   @SuppressWarnings("java:S3011")
   @Override
   public void insertData(final Object data) {
      final Field[] fields = data.getClass().getDeclaredFields();
      final List<Field> targetedFields = filterAndSortFields(fields);

      for (Field field : targetedFields) {
         final A annotation = field.getAnnotation(getAnnotationClass());

         field.setAccessible(true);
         try {
            final Class<? extends ComponentType> enumClass = getComponentTypeEnumClass(annotation);
            final Class<? extends ComponentType> componentTypeClass = extractComponentTypeClass(enumClass);
            final Insertion<L> service = serviceRegistry.getService(componentTypeClass);
            if (service == null) {
               throw new IllegalStateException(
                     "No InsertionService registered for: " + componentTypeClass.getSimpleName()
               );
            }

            final L selector = buildLocator(annotation);
            final Object valueForField = field.get(data);

            if (valueForField != null) {
               beforeInsertion(annotation);
               service.insertion(getType(annotation), selector, valueForField);
               afterInsertion(annotation);
            }
         } catch (IllegalAccessException e) {
            throw new ReflectionException("Failed to access field: " + field.getName(), e);
         }
      }
      LogUi.info("Finished data insertion for [{}].", data.getClass().getSimpleName());
   }

   protected abstract Class<A> getAnnotationClass();

   protected abstract int getOrder(A annotation);

   protected abstract Class<? extends ComponentType> getComponentTypeEnumClass(A annotation);

   protected abstract L buildLocator(A annotation);

   protected abstract ComponentType getType(A annotation);

   protected void beforeInsertion(A annotation) {
   }

   protected void afterInsertion(A annotation) {
   }

   protected final List<Field> filterAndSortFields(final Field[] fields) {
      return Arrays.stream(fields)
            .filter(field -> field.isAnnotationPresent(getAnnotationClass()))
            .sorted(Comparator.comparingInt(field ->
                  getOrder(field.getAnnotation(getAnnotationClass()))))
            .toList();
   }

   protected static Class<? extends ComponentType> extractComponentTypeClass(
         final Class<? extends ComponentType> componentTypeClass) {
      if (Arrays.asList(componentTypeClass.getInterfaces()).contains(ComponentType.class)) {
         return componentTypeClass;
      }

      @SuppressWarnings("unchecked") final Class<? extends ComponentType> resolved =
            (Class<? extends ComponentType>) Arrays.stream(componentTypeClass.getInterfaces())
                  .filter(inter -> Arrays.asList(inter.getInterfaces())
                        .contains(ComponentType.class))
                  .findFirst()
                  .orElseThrow(() -> new IllegalStateException(
                        "No interface extending ComponentType found in " + componentTypeClass
                  ));
      return resolved;
   }

}
