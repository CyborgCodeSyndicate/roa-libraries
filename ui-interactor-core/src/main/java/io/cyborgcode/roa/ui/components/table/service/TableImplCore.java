package io.cyborgcode.roa.ui.components.table.service;

import io.cyborgcode.roa.ui.components.base.BaseComponentCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.table.annotations.CellFilter;
import io.cyborgcode.roa.ui.components.table.annotations.CellInsertion;
import io.cyborgcode.roa.ui.components.table.annotations.CustomCellFilter;
import io.cyborgcode.roa.ui.components.table.annotations.CustomCellInsertion;
import io.cyborgcode.roa.ui.components.table.base.TableField;
import io.cyborgcode.roa.ui.components.table.exceptions.TableException;
import io.cyborgcode.roa.ui.components.table.filters.CellFilterComponent;
import io.cyborgcode.roa.ui.components.table.filters.CellFilterFunction;
import io.cyborgcode.roa.ui.components.table.filters.FilterStrategy;
import io.cyborgcode.roa.ui.components.table.filters.TableFilter;
import io.cyborgcode.roa.ui.components.table.insertion.CellInsertionComponent;
import io.cyborgcode.roa.ui.components.table.insertion.CellInsertionFunction;
import io.cyborgcode.roa.ui.components.table.insertion.TableInsertion;
import io.cyborgcode.roa.ui.components.table.model.CellLocator;
import io.cyborgcode.roa.ui.components.table.model.TableCell;
import io.cyborgcode.roa.ui.components.table.model.TableCellLocatorInfo;
import io.cyborgcode.roa.ui.components.table.model.TableLocators;
import io.cyborgcode.roa.ui.components.table.registry.TableServiceRegistry;
import io.cyborgcode.roa.ui.components.table.sort.SortingStrategy;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Setter;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Base implementation of the {@link Table} interface for Playwright, providing core functionalities
 * for reading, filtering, sorting, and inserting values into table elements in the UI.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings("java:S3011")
public abstract class TableImplCore<E extends BaseUiElement, L, D> extends BaseComponentCore<D> implements Table {

   public static final String NO_LOCATOR_FOR_FIELD_EXCEPTION = "No locator found for the provided field.";
   private static final String READING_CLASS = "Reading entire table as class '%s'";
   private static final String READING_CLASS_WITH_FIELDS = "Reading table as class '%s' with fields %s";
   private static final String READING_RANGE = "Reading table rows from %d to %d as class '%s'";
   private static final String READING_RANGE_WITH_FIELDS =
         "Reading table rows from %d to %d as class '%s' with fields %s";
   private static final String READING_ROW = "Reading row number %d as class '%s'";
   private static final String READING_ROW_WITH_FIELDS = "Reading row number %d as class '%s' with fields %s";
   private static final String READING_ROW_CRITERIA = "Reading row matching criteria %s as class '%s'";
   private static final String READING_ROW_CRITERIA_WITH_FIELDS =
         "Reading row matching criteria %s as class '%s' with fields %s";
   private static final String INSERT_CELL_ROW_CRITERIA =
         "Inserting cell value in row matching criteria %s for class '%s', field '%s', cell index %d";
   private static final String INSERT_CELL_ROW =
         "Inserting cell value in row %d for class '%s', field '%s', cell index %d";
   private static final String INSERT_CELL_FIELD_CRITERIA =
         "Inserting cell value(s) into field '%s' for row matching criteria %s for class '%s'";
   private static final String INSERT_CELL_FIELD_ROW =
         "Inserting cell value(s) into field '%s' for row %d for class '%s'";
   private static final String INSERT_CELL_DATA_CRITERIA =
         "Inserting cell value(s) using data for row matching criteria %s for class '%s'";
   private static final String INSERT_CELL_DATA_ROW = "Inserting cell value(s) using data for row %d for class '%s'";
   private static final String FILTERING =
         "Filtering table for class '%s' on column '%s' using strategy '%s' with values %s";
   private static final String SORTING = "Sorting table for class '%s' on column '%s' using sorting strategy '%s'";
   private static final String INVALID_FIELD_TYPE = "Some fields are not TableCell or List<TableCell>.";
   private final List<Object> acceptedValues;
   @Setter
   protected TableServiceRegistry<E> serviceRegistry;

   protected TableImplCore(final D driver) {
      super(driver);
      this.acceptedValues = List.of(
            new TableCell<E>(""),
            List.of()
      );
   }

   protected TableImplCore(final D driver, final TableServiceRegistry<E> serviceRegistry) {
      super(driver);
      this.serviceRegistry = serviceRegistry;
      this.acceptedValues = List.of(
            new TableCell<E>(""),
            List.of()
      );
   }

   @Override
   public final <T> List<T> readTable(final Class<T> clazz) {
      LogUi.step(String.format(READING_CLASS, clazz.getSimpleName()));
      return readTableInternal(clazz, null, null, null);
   }

   @Override
   @SafeVarargs
   public final <T> List<T> readTable(final Class<T> clazz, final TableField<T>... fields) {
      LogUi.step(String.format(READING_CLASS_WITH_FIELDS, clazz.getSimpleName(), Arrays.toString(fields)));
      return readTableInternal(clazz, (fields == null) ? null : List.of(fields), null, null);
   }

   @Override
   public final <T> List<T> readTable(final int start, final int end, final Class<T> clazz) {
      LogUi.step(String.format(READING_RANGE, start, end, clazz.getSimpleName()));
      return readTableInternal(clazz, null, start, end);
   }

   @Override
   @SafeVarargs
   public final <T> List<T> readTable(final int start, final int end, final Class<T> clazz,
                                      final TableField<T>... fields) {
      LogUi.step(String.format(READING_RANGE_WITH_FIELDS, start, end, clazz.getSimpleName(), Arrays.toString(fields)));
      return readTableInternal(clazz, (fields == null) ? null : Arrays.asList(fields), start, end);
   }

   @Override
   public final <T> T readRow(final int row, final Class<T> clazz) {
      LogUi.step(String.format(READING_ROW, row, clazz.getSimpleName()));
      return readRowInternal(row - 1, clazz, null);
   }

   @Override
   public final <T> T readRow(final List<String> searchCriteria, final Class<T> clazz) {
      LogUi.step(String.format(READING_ROW_CRITERIA, searchCriteria, clazz.getSimpleName()));
      return readRowInternal(searchCriteria, clazz, null);
   }

   @Override
   @SafeVarargs
   public final <T> T readRow(final int row, final Class<T> clazz, final TableField<T>... fields) {
      LogUi.step(String.format(READING_ROW_WITH_FIELDS, row, clazz.getSimpleName(), Arrays.toString(fields)));
      return readRowInternal(row - 1, clazz, fields);
   }

   @Override
   @SafeVarargs
   public final <T> T readRow(final List<String> searchCriteria, final Class<T> clazz, final TableField<T>... fields) {
      LogUi.step(String.format(READING_ROW_CRITERIA_WITH_FIELDS, searchCriteria, clazz.getSimpleName(),
            Arrays.toString(fields)));
      return readRowInternal(searchCriteria, clazz, fields);
   }

   @Override
   public final <T> void insertCellValue(final List<String> searchCriteria, final Class<T> rowClass,
                                         final TableField<T> field, final int cellIndex, final String... values) {
      LogUi.step(String.format(INSERT_CELL_ROW_CRITERIA, searchCriteria, rowClass.getSimpleName(), field, cellIndex));
      insertCellValueInternal(searchCriteria, rowClass, field, cellIndex, values);
   }

   @Override
   public final <T> void insertCellValue(final int row, final Class<T> rowClass, final TableField<T> field,
                                         final int cellIndex, final String... values) {
      LogUi.step(String.format(INSERT_CELL_ROW, row, rowClass.getSimpleName(), field, cellIndex));
      insertCellValueInternal(row - 1, rowClass, field, cellIndex, values);
   }

   @Override
   public final <T> void insertCellValue(final List<String> searchCriteria, final Class<T> clazz, final T data) {
      LogUi.step(String.format(INSERT_CELL_DATA_CRITERIA, searchCriteria, clazz.getSimpleName()));
      processInsertCellValue((fieldInvoker, strings) -> {
         if (strings.length == 1) {
            insertCellValue(searchCriteria, clazz, fieldInvoker, 1, strings);
         } else {
            for (int i = 0; i < strings.length; i++) {
               insertCellValue(searchCriteria, clazz, fieldInvoker, i + 1, strings[i]);
            }
         }
      }, clazz, data);
   }

   @Override
   public final <T> void insertCellValue(final List<String> searchCriteria, final Class<T> clazz,
                                         final TableField<T> field, final String... values) {
      LogUi.step(String.format(INSERT_CELL_FIELD_CRITERIA, field, searchCriteria, clazz.getSimpleName()));
      Table.super.insertCellValue(searchCriteria, clazz, field, values);
   }

   @Override
   public final <T> void insertCellValue(final int row, final Class<T> clazz, final TableField<T> field,
                                         final String... values) {
      LogUi.step(String.format(INSERT_CELL_FIELD_ROW, field, row, clazz.getSimpleName()));
      Table.super.insertCellValue(row, clazz, field, values);
   }

   @Override
   public final <T> void insertCellValue(final int row, final Class<T> clazz, final T data) {
      LogUi.step(String.format(INSERT_CELL_DATA_ROW, row, clazz.getSimpleName()));
      processInsertCellValue((fieldInvoker, strings) -> {
         if (strings.length == 1) {
            insertCellValue(row, clazz, fieldInvoker, 1, strings);
         } else {
            for (int i = 0; i < strings.length; i++) {
               insertCellValue(row, clazz, fieldInvoker, i + 1, strings[i]);
            }
         }
      }, clazz, data);
   }

   @Override
   public final <T> void filterTable(final Class<T> tclass, final TableField<T> column,
                                     final FilterStrategy filterStrategy, final String... values) {
      LogUi.step(String.format(FILTERING, tclass.getSimpleName(), column, filterStrategy, Arrays.toString(values)));
      final Map<String, List<CellLocator<L, E>>> tableSectionLocatorsMap =
            getTableSectionLocatorsMap(tclass, List.of(column));

      final Map.Entry<String, List<CellLocator<L, E>>> firstEntry = tableSectionLocatorsMap.entrySet().stream()
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(NO_LOCATOR_FOR_FIELD_EXCEPTION));

      final String tableSection = firstEntry.getKey();
      final CellLocator<L, E> cellLocator = firstEntry.getValue().get(0);

      TableLocators<L> tableLocators = getTableLocators(tclass);
      E tableContainer = getTableContainer(tableLocators.getTableContainerLocator());
      E headerRow = getHeaderRow(tableContainer, tableLocators.getHeaderRowLocator(), tableSection);
      filterCells(cellLocator, headerRow, filterStrategy, values);
   }

   @Override
   public final <T> void sortTable(final Class<T> tclass, final TableField<T> column,
                                   final SortingStrategy sortingStrategy) {
      LogUi.step(String.format(SORTING, tclass.getSimpleName(), column, sortingStrategy));

      final Map<String, List<CellLocator<L, E>>> tableSectionLocatorsMap =
            getTableSectionLocatorsMap(tclass, List.of(column));

      final Map.Entry<String, List<CellLocator<L, E>>> firstEntry = tableSectionLocatorsMap.entrySet().stream()
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(NO_LOCATOR_FOR_FIELD_EXCEPTION));

      final CellLocator<L, E> cellLocator = firstEntry.getValue().get(0);

      TableLocators<L> tableLocators = getTableLocators(tclass);
      E tableContainer = getTableContainer(tableLocators.getTableContainerLocator());
      E headerRow = getHeaderRow(tableContainer, tableLocators.getHeaderRowLocator(),
            firstEntry.getKey());
      E cellFromHeaderRow = locateElements(headerRow, cellLocator.getHeaderCellLocator()).get(0);
      sortTable(cellFromHeaderRow, sortingStrategy);
   }

   /**
    * Extension point for subclasses to define sorting logic.
    */
   protected void sortTable(E headerCell, SortingStrategy sortingStrategy) {
   }

   /**
    * Retrieves the table container element using the specified selector.
    */
   protected abstract E getTableContainer(L tableContainerSelector);

   protected abstract String getTextFromElement(E element);

   protected abstract List<E> locateElements(E element, L locator);

   /**
    * Retrieves all rows from the specified table container.
    */
   protected abstract List<E> getRows(E tableContainer, L tableRowsSelector, String section);

   /**
    * Retrieves the header row of a table.
    */
   protected abstract E getHeaderRow(E tableContainer, L headerRowSelector, String tableSection);

   protected abstract TableCell<E> buildTableCell(final E container,
                                                  final L cellSelector,
                                                  final L textSelector);

   protected abstract TableLocators<L> getTableLocators(Class<?> clazz);

   protected abstract Class<? extends Annotation> tableCellLocatorAnnotation();

   protected abstract TableCellLocatorInfo<L> populateInfoFromAnnotation(Annotation annotation);


   private <T> List<T> readTableInternal(final Class<T> rowClass,
                                         final List<TableField<T>> fields,
                                         final Integer start,
                                         final Integer end) {
      TableLocators<L> tableLocators = getTableLocators(rowClass);
      E tableContainer = getTableContainer(tableLocators.getTableContainerLocator());

      final Map<String, List<CellLocator<L, E>>> tableSectionLocatorsMap =
            getTableSectionLocatorsMap(rowClass, fields);

      final Map<String, List<E>> rowsMap =
            tableSectionLocatorsMap.keySet().stream()
                  .collect(Collectors.toMap(
                        Function.identity(),
                        section -> readRowsInRange(tableContainer, tableLocators.getTableRowsLocator(), section,
                              start, end)
                  ));

      return mergeRowsAcrossSections(rowsMap, tableSectionLocatorsMap, rowClass);
   }


   private List<E> readRowsInRange(E tableContainer, L tableRowsSelector,
                                   final String tableSection,
                                   final Integer start,
                                   final Integer end) {
      final List<E> allRows = getRows(tableContainer, tableRowsSelector, tableSection);

      if (start != null && end != null) {
         final int fromIndex = Math.max(0, start - 1);
         final int toIndex = Math.min(allRows.size(), end);
         if (fromIndex >= toIndex) {
            LogUi.warn("Requested start/end range is invalid: {}-{}. Returning empty list.", start, end);
            return Collections.emptyList();
         }
         return allRows.subList(fromIndex, toIndex);
      }
      return allRows;
   }


   private <T> List<T> mergeRowsAcrossSections(final Map<String, List<E>> rowsPerSection,
                                               final Map<String, List<CellLocator<L, E>>> locatorsMap,
                                               final Class<T> rowClass) {
      final List<T> results = new ArrayList<>();

      rowsPerSection.values().stream().findFirst().ifPresent(rows -> {
         for (int i = 0; i < rows.size(); i++) {
            T mergedRow = null;
            for (Map.Entry<String, List<E>> entry : rowsPerSection.entrySet()) {
               final String section = entry.getKey();
               final E rowElement = entry.getValue().get(i);

               final T partialRow = readSingleRow(rowClass, locatorsMap.get(section), rowElement);
               mergedRow = mergeObjects(mergedRow, partialRow);
            }
            results.add(mergedRow);
         }
      });
      return results;
   }


   private <T> T readRowInternal(final Object rowIdentifier,
                                 final Class<T> rowClass,
                                 final TableField<T>[] fields) {
      TableLocators<L> tableLocators = getTableLocators(rowClass);
      E tableContainer = getTableContainer(tableLocators.getTableContainerLocator());
      final Map<String, List<CellLocator<L, E>>> locatorsMap =
            getTableSectionLocatorsMap(rowClass, (fields == null) ? null : Arrays.asList(fields));

      final Map<String, E> rowElementMap = locatorsMap.keySet()
            .stream()
            .collect(Collectors.toMap(
                  Function.identity(),
                  section -> findRowElement(tableContainer,
                        tableLocators.getTableRowsLocator(),
                        rowIdentifier, section)
            ));

      T mergedRow = null;
      for (Map.Entry<String, E> entry : rowElementMap.entrySet()) {
         final T partialRow = readSingleRow(rowClass, locatorsMap.get(entry.getKey()), entry.getValue());
         mergedRow = mergeObjects(mergedRow, partialRow);
      }

      return mergedRow;
   }


   private E findRowElement(E tableContainer, L tableRowsSelector,
                            final Object rowIdentifier,
                            final String section) {
      final List<E> rows = getRows(tableContainer, tableRowsSelector, section);

      if (rowIdentifier instanceof Integer rowIndex) {
         if (rowIndex < 0 || rowIndex >= rows.size()) {
            throw new IndexOutOfBoundsException(String.format(
                  "Requested row index %d is out of valid range [1..%d]", rowIndex + 1, rows.size()
            ));
         }
         return rows.get(rowIndex);
      } else if (rowIdentifier instanceof List<?> criteria) {
         return findRowByCriteria(criteria, rows);
      } else {
         throw new IllegalArgumentException("Unsupported row identifier type: " + rowIdentifier);
      }
   }


   private E findRowByCriteria(final List<?> searchCriteria, final List<E> rows) {
      return rows.stream()
            .filter(row -> searchCriteria.stream().allMatch(
                  criterion -> Optional.ofNullable(getTextFromElement(row))
                        .orElse("")
                        .contains(String.valueOf(criterion))
            ))
            .findFirst()
            .orElseThrow(() -> new RuntimeException(
                  "No row found containing all criteria: " + searchCriteria
            ));
   }


   private <T> T readSingleRow(final Class<T> rowClass,
                               final List<CellLocator<L, E>> cellLocators,
                               final E rowElement) {
      final T rowInstance = createInstance(rowClass);
      for (CellLocator<L, E> cellLocator : cellLocators) {
         populateFieldValue(rowInstance, rowElement, cellLocator);
      }
      return rowInstance;
   }


   private <T> void populateFieldValue(final T rowInstance,
                                       final E rowElement,
                                       final CellLocator<L, E> cellLocator) {
      final L locator = cellLocator.getLocator();
      final L textLocator = cellLocator.getCellTextLocator();
      final String fieldName = cellLocator.getFieldName();
      final boolean isCollection = cellLocator.isCollection();

      if (!isCollection) {
         final TableCell<E> singleCell = buildTableCell(rowElement, locator, textLocator);
         invokeSetter(rowInstance, fieldName, singleCell);
      } else {
         final List<E> cellElements = locateElements(rowElement, locator);
         final List<TableCell<E>> tableCells = cellElements.stream()
               .map(elem -> buildTableCell(elem, null, textLocator))
               .toList();
         invokeSetter(rowInstance, fieldName, tableCells);
      }
   }


   private <T> T createInstance(final Class<T> clazz) {
      try {
         return clazz.getDeclaredConstructor().newInstance();
      } catch (ReflectiveOperationException e) {
         final String message = "Could not create a new instance of class: " + clazz.getName();
         LogUi.error(message, e);
         throw new IllegalStateException(message, e);
      }
   }


   private <T> T mergeObjects(final T t1, final T t2) {
      if (t1 == null) {
         return t2;
      }
      if (t2 == null) {
         return t1;
      }

      final Field[] fields = t1.getClass().getDeclaredFields();
      for (Field field : fields) {
         field.setAccessible(true);
         try {
            final Object value1 = field.get(t1);
            if (value1 == null) {
               final Object value2 = field.get(t2);
               if (value2 != null) {
                  field.set(t1, value2);
               }
            }
         } catch (IllegalAccessException e) {
            final String message = "Cannot access field: " + field.getName();
            LogUi.error(message, e);
         }
      }
      return t1;
   }


   private void invokeSetter(final Object targetObject, final String fieldName, final Object value) {
      if (targetObject == null || fieldName == null || value == null) {
         return;
      }

      Class<?> paramType = value.getClass();
      if (List.class.isAssignableFrom(paramType)) {
         paramType = List.class;
      }

      final String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

      try {
         final Method setter = targetObject.getClass().getDeclaredMethod(setterName, paramType);
         setter.invoke(targetObject, value);
      } catch (NoSuchMethodException e) {
         final String message = String.format(
               "Setter not found: %s(%s) in class %s",
               setterName,
               paramType.getSimpleName(),
               targetObject.getClass().getName()
         );
         LogUi.error(message, e);
      } catch (IllegalAccessException | InvocationTargetException e) {
         final String message = String.format(
               "Failed to invoke setter: %s on %s",
               setterName,
               targetObject.getClass().getName()
         );
         LogUi.error(message, e);
      }
   }


   private <T> Map<String, List<CellLocator<L, E>>> getTableSectionLocatorsMap(final Class<T> rowClass,
                                                                               final List<TableField<T>> fields) {
      final List<CellLocator<L, E>> cellLocators;
      if (fields == null || fields.isEmpty()) {
         cellLocators = extractAnnotatedFields(rowClass, Collections.emptyList());
      } else {
         cellLocators = extractAnnotatedFields(rowClass, fields);
      }

      return cellLocators.stream()
            .collect(Collectors.groupingBy(CellLocator::getTableSection));
   }


   private <T> List<CellLocator<L, E>> extractAnnotatedFields(final Class<T> clazz,
                                                              final List<TableField<T>> fields) {
      final T rowInstance = createInstance(clazz);
      Class<? extends Annotation> annotationClass = tableCellLocatorAnnotation();

      if (!fields.isEmpty()) {
         validateFieldInvokers(rowInstance, fields);
      }

      final Field[] declaredFields = clazz.getDeclaredFields();
      final List<Field> validFields;
      if (fields.isEmpty()) {
         validFields = Arrays.stream(declaredFields)
               .filter(f -> f.isAnnotationPresent(annotationClass))
               .toList();
      } else {
         validFields = Arrays.stream(declaredFields)
               .filter(f -> {
                  boolean hasValue = false;
                  try {
                     f.setAccessible(true);
                     hasValue = (f.get(rowInstance) != null);
                     if (hasValue && !f.isAnnotationPresent(annotationClass)) {
                        throw new IllegalArgumentException(
                              "Field " + f.getName()
                                    + " is missing a @TableCellLocator annotation."
                        );
                     }
                  } catch (IllegalAccessException ex) {
                     LogUi.error("Cannot access field: {}", f.getName(), ex);
                  }
                  return hasValue;
               })
               .toList();
      }

      final boolean validSyntax = validFields.stream()
            .allMatch(
                  f -> isListOfTableCell(f) || TableCell.class.isAssignableFrom(f.getType()));
      if (!validSyntax) {
         LogUi.error(INVALID_FIELD_TYPE);
         throw new TableException("Invalid field type for table cell usage.");
      }

      return validFields.stream()
            .map(this::mapToCellLocator)
            .toList();
   }


   private boolean isListOfTableCell(final Field field) {
      if (!List.class.isAssignableFrom(field.getType())) {
         return false;
      }
      final Type genericType = field.getGenericType();
      if (genericType instanceof ParameterizedType parameterizedType) {
         final Type[] typeArguments = parameterizedType.getActualTypeArguments();
         return (typeArguments.length == 1) && (typeArguments[0] == TableCell.class);
      }
      return false;
   }


   private CellLocator<L, E> mapToCellLocator(final Field field) {
      final Annotation annotation = field.getAnnotation(tableCellLocatorAnnotation());

      TableCellLocatorInfo<L> tableCellLocatorInfo = populateInfoFromAnnotation(annotation);

      final CellInsertionComponent cellInsertionComponent = Optional.ofNullable(
                  field.getAnnotation(CellInsertion.class)
            ).map(ci -> new CellInsertionComponent(ci.type(), ci.componentType(), ci.order()))
            .orElse(null);

      final Class<? extends CellInsertionFunction> customCellInsertion = Optional.ofNullable(
            field.getAnnotation(CustomCellInsertion.class)
      ).map(CustomCellInsertion::insertionFunction).orElse(null);

      final CellFilterComponent cellFilterComponent = Optional.ofNullable(
                  field.getAnnotation(CellFilter.class)
            ).map(ci -> new CellFilterComponent(ci.type(), ci.componentType()))
            .orElse(null);

      final Class<? extends CellFilterFunction> customCellFilter = Optional.ofNullable(
            field.getAnnotation(CustomCellFilter.class)
      ).map(CustomCellFilter::cellFilterFunction).orElse(null);

      final boolean isCollection = Collection.class.isAssignableFrom(field.getType());

      return new CellLocator(
            field.getName(),
            tableCellLocatorInfo.getCellSelector(),
            tableCellLocatorInfo.getCellTextSelector(),
            tableCellLocatorInfo.getHeaderCellSelector(),
            isCollection,
            tableCellLocatorInfo.getTableSection(),
            cellInsertionComponent,
            customCellInsertion,
            cellFilterComponent,
            customCellFilter
      );
   }


   private <T> void validateFieldInvokers(final T instance, final List<TableField<T>> fields) {
      for (TableField<T> setter : fields) {
         boolean success = false;
         for (Object testValue : acceptedValues) {
            try {
               setter.invoke(instance, testValue);
               success = true;
               break;
            } catch (InvocationTargetException | IllegalAccessException ex) {
               // Try next accepted value
            }
         }
         if (!success) {
            final String msg = "No accepted value could be applied via FieldInvoker. "
                  + "Possible illegal field or setter in " + instance.getClass().getName();
            LogUi.error(msg);
            throw new IllegalArgumentException(msg);
         }
      }
   }


   private <T> void insertCellValueInternal(final Object rowIdentifier,
                                            final Class<T> rowClass,
                                            final TableField<T> field,
                                            final int cellIndex,
                                            final String... values) {
      TableLocators<L> tableLocators = getTableLocators(rowClass);
      E tableContainer = getTableContainer(tableLocators.getTableContainerLocator());
      final Map<String, List<CellLocator<L, E>>> tableSectionLocatorsMap =
            getTableSectionLocatorsMap(rowClass, List.of(field));
      final Map.Entry<String, List<CellLocator<L, E>>> firstEntry = tableSectionLocatorsMap.entrySet().stream()
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(NO_LOCATOR_FOR_FIELD_EXCEPTION));

      final String tableSection = firstEntry.getKey();
      final CellLocator<L, E> cellLocator = firstEntry.getValue().get(0);

      final E rowElement;
      if (rowIdentifier instanceof List<?> criteria) {
         rowElement = findRowByCriteria(criteria,
               getRows(tableContainer, tableLocators.getTableRowsLocator(), tableSection));
      } else if (rowIdentifier instanceof Integer rowIndex) {
         final List<E> rows = getRows(tableContainer, tableLocators.getTableRowsLocator(),
               tableSection);
         if (rowIndex < 0 || rowIndex >= rows.size()) {
            throw new IndexOutOfBoundsException(String.format(
                  "Requested row index %d is out of valid range [1..%d]", rowIndex + 1, rows.size()
            ));
         }
         rowElement = rows.get(rowIndex);
      } else {
         throw new IllegalArgumentException("Unsupported row identifier type: " + rowIdentifier);
      }

      insertInCell(cellLocator, rowElement, values, cellIndex);
   }


   private void insertInCell(final CellLocator<L, E> cellLocator,
                             final E rowElement,
                             final String[] values,
                             final int cellIndex) {
      final CellInsertionComponent component = cellLocator.getCellInsertionComponent();
      final Class<? extends CellInsertionFunction<E>> customFunction = cellLocator.getCustomCellInsertion();

      if (component == null && customFunction == null) {
         throw new TableException(
               "No table cell insertion method provided for field: " + cellLocator.getFieldName()
         );
      }

      final List<E> cells = locateElements(rowElement, cellLocator.getLocator());
      if (cells.isEmpty() || cellIndex <= 0 || cellIndex > cells.size()) {
         throw new TableException(String.format(
               "Invalid cell index: %d for locator: %s", cellIndex, cellLocator.getLocator()
         ));
      }

      final E targetCell = cells.get(cellIndex - 1);

      if (component != null) {
         insertUsingComponent(component, targetCell, values);
      } else {
         insertUsingCustomFunction(customFunction, targetCell, values);
      }
   }


   private void insertUsingComponent(final CellInsertionComponent component,
                                     final E targetCell,
                                     final String[] values) {
      if (serviceRegistry == null) {
         throw new IllegalStateException(
               "Your instance of table is not having registered services. You can't use CellInsertion annotation.");
      }
      try {
         final Class<? extends ComponentType> type = component.getType();

         Enum<?> componentInstance =
               (Enum<?>) ReflectionUtil.findEnumImplementationsOfInterface(type, component.getComponentType(),
                     getUiConfig().projectPackages());
         final TableInsertion<E> service = serviceRegistry.getTableService(type);
         service.tableInsertion(targetCell, (ComponentType) componentInstance, values);
      } catch (Exception e) {
         throw new TableException(
               "Failed to insert using component: " + component.getComponentType(), e
         );
      }
   }


   private void insertUsingCustomFunction(final Class<? extends CellInsertionFunction<E>> customFunction,
                                          final E targetCell,
                                          final String[] values) {
      try {
         Constructor<? extends CellInsertionFunction<E>> constructor = customFunction.getDeclaredConstructor();
         constructor.setAccessible(true);
         final CellInsertionFunction<E> functionInstance = constructor.newInstance();
         functionInstance.accept(targetCell, values);
      } catch (ReflectiveOperationException e) {
         throw new TableException(
               "Failed to instantiate custom cell insertion function: " + customFunction.getName(), e
         );
      }
   }


   private void filterCells(final CellLocator<L, E> cellLocator,
                            final E headerRowElement,
                            final FilterStrategy filterStrategy,
                            final String[] values) {
      final CellFilterComponent component = cellLocator.getCellFilterComponent();
      final Class<? extends CellFilterFunction<E>> customFunction = cellLocator.getCustomCellFilter();

      if (component == null && customFunction == null) {
         throw new TableException(
               "No table cell filter method provided for field: " + cellLocator.getFieldName()
         );
      }
      final E targetCell = locateElements(headerRowElement, cellLocator.getHeaderCellLocator()).get(0);

      if (component != null) {
         filterCellsUsingComponent(component, targetCell, filterStrategy, values);
      } else {
         filterCellsUsingCustomFunction(customFunction, targetCell, filterStrategy, values);
      }
   }

   private void filterCellsUsingComponent(final CellFilterComponent component,
                                          final E targetCell, FilterStrategy filterStrategy,
                                          final String[] values) {
      if (serviceRegistry == null) {
         throw new IllegalStateException(
               "Your instance of table is not having registered services. You can't use CellFilter annotation.");
      }
      try {
         final Class<? extends ComponentType> type = component.getType();
         final Enum<?> componentInstance =
               (Enum<?>) ReflectionUtil.findEnumImplementationsOfInterface(type, component.getComponentType(),
                     getUiConfig().projectPackages());
         final TableFilter<E> service = serviceRegistry.getFilterService(type);
         service.tableFilter(targetCell, (ComponentType) componentInstance, filterStrategy, values);
      } catch (Exception e) {
         throw new TableException(
               "Failed to filter using component: " + component.getComponentType(), e
         );
      }
   }


   private void filterCellsUsingCustomFunction(final Class<? extends CellFilterFunction<E>> customFunction,
                                               final E targetCell, FilterStrategy filterStrategy,
                                               final String[] values) {
      try {
         final CellFilterFunction<E> functionInstance = customFunction.getDeclaredConstructor().newInstance();
         functionInstance.accept(targetCell, filterStrategy, values);
      } catch (ReflectiveOperationException e) {
         throw new TableException(
               "Failed to instantiate custom cell filter function: " + customFunction.getName(), e
         );
      }
   }


   private <T> void processInsertCellValue(BiConsumer<TableField<T>, String[]> action, Class<T> classType, T data) {
      Map<TableField<T>, String[]> invokersMap = prepareFieldInvokersMap(classType, data);
      invokersMap.forEach(action);
   }


   private <T> Map<TableField<T>, String[]> prepareFieldInvokersMap(Class<T> classType, T data) {
      return Arrays.stream(classType.getDeclaredFields())
            .filter(field -> field.isAnnotationPresent(CellInsertion.class) || field.isAnnotationPresent(
                  CustomCellInsertion.class))
            .filter(field -> isListOfTableCell(field) || TableCell.class.isAssignableFrom(field.getType()))
            .map(field -> {
               int order = field.isAnnotationPresent(CellInsertion.class)
                     ? field.getAnnotation(CellInsertion.class).order()
                     : field.getAnnotation(CustomCellInsertion.class).order();

               TableField<T> fieldInvoker = (instance, value) -> {
                  field.setAccessible(true);
                  field.set(instance, value);
               };

               String[] stringValues = convertFieldValueToStrings(field, data);

               return new OrderedFieldInvokerAndValues<>(fieldInvoker, order, stringValues);
            })
            .sorted(Comparator.comparingInt(OrderedFieldInvokerAndValues::order))
            .collect(Collectors.toMap(
                  OrderedFieldInvokerAndValues::fieldInvoker,
                  OrderedFieldInvokerAndValues::stringValues,
                  (v1, v2) -> v1,
                  LinkedHashMap::new
            ));
   }


   private String[] convertFieldValueToStrings(Field field, Object data) {
      field.setAccessible(true);
      try {
         Object value = field.get(data);
         if (value instanceof TableCell cell) {
            return cell.getText() == null ? new String[0] : new String[] {cell.getText()};
         } else if (value instanceof List<?> list) {
            return list.stream()
                  .filter(TableCell.class::isInstance)
                  .map(TableCell.class::cast)
                  .map(TableCell::getText)
                  .filter(Objects::nonNull)
                  .toArray(String[]::new);
         }
      } catch (IllegalAccessException e) {
         throw new TableException("Unable to access field: " + field.getName(), e);
      }
      return new String[0];
   }


   private record OrderedFieldInvokerAndValues<T>(
         TableField<T> fieldInvoker,
         int order,
         String[] stringValues
   ) {
      @Override
      public boolean equals(Object o) {
         if (this == o) {
            return true;
         }
         if (!(o instanceof OrderedFieldInvokerAndValues<?> that)) {
            return false;
         }
         return order == that.order
               && Objects.equals(fieldInvoker, that.fieldInvoker)
               && Arrays.equals(stringValues, that.stringValues);
      }

      @Override
      public int hashCode() {
         int result = Objects.hash(fieldInvoker, order);
         result = 31 * result + Arrays.hashCode(stringValues);
         return result;
      }

      @Override
      public String toString() {
         return "OrderedFieldInvokerAndValues["
               + "fieldInvoker=" + fieldInvoker
               + ", order=" + order
               + ", stringValues=" + Arrays.toString(stringValues)
               + ']';
      }
   }

}
