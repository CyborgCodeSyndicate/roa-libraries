package io.cyborgcode.roa.ui.validator;

import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.util.table.TableReflectionUtil;
import io.cyborgcode.roa.validator.core.Assertion;
import io.cyborgcode.roa.validator.core.AssertionResult;
import io.cyborgcode.roa.validator.util.AssertionUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;

/**
 * Implementation of {@link UiTableValidator} responsible for validating table data and elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@NoArgsConstructor
public class UiTableValidatorImplCore<V> implements UiTableValidator {

   protected void printAssertionTarget(Map<String, Object> data) {
      LogUi.extended("Validation target: [{}]", data.toString());
   }

   @Override
   @SuppressWarnings("unchecked")
   public <T> List<AssertionResult<T>> validateTable(final Object object, final Assertion... assertions) {
      startValidation(assertions.length);

      Map<String, T> data = new HashMap<>();

      for (Assertion assertion : assertions) {
         processAssertion((UiTablesAssertionTarget) assertion.getTarget());
         switch ((UiTablesAssertionTarget) assertion.getTarget()) {
            case ROW_VALUES -> {
               data.put("rowValues", (T) TableReflectionUtil.extractTextsFromRow(object));
               assertion.setKey("rowValues");
            }
            case ROW_ELEMENTS -> {
               data.put("rowElements", (T) TableReflectionUtil.extractElementsFromRow(object));
               assertion.setKey("rowElements");
            }
            case TABLE_VALUES -> {
               List<?> rows = (List<?>) object;
               List<List<String>> rowList = rows.stream().map(TableReflectionUtil::extractTextsFromRow).toList();
               data.put("tableValues", (T) rowList);
               assertion.setKey("tableValues");
            }
            case TABLE_ELEMENTS -> {
               List<?> rows = (List<?>) object;
               List<List<V>> rowList = new ArrayList<>();
               for (Object row : rows) {
                  List<V> elements = TableReflectionUtil.extractElementsFromRow(row);
                  rowList.add(elements);
               }
               data.put("tableElements", (T) rowList);
               assertion.setKey("tableElements");
            }
            default -> throw new IllegalArgumentException("Unsupported assertion target");
         }
      }

      printAssertionTarget((Map<String, Object>) data);
      return AssertionUtil.validate(data, List.of(assertions));
   }

   public void startValidation(int assertionCount) {
      LogUi.info("Starting response validation with {} assertion(s).", assertionCount);
   }

   public void processAssertion(UiTablesAssertionTarget target) {
      LogUi.info("Processing assertion for target: {}", target);
   }
}
