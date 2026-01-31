package io.cyborgcode.roa.ui.components.table.base;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import java.lang.reflect.InvocationTargetException;
import java.util.function.BiConsumer;

/**
 * Represents a field in a table row that can be dynamically accessed and modified.
 * This functional interface allows for invoking a setter method on a given instance
 * to set a value for a specific table column.
 *
 * <p>Primarily used for reading, writing, and updating table data within
 * the {@code Table} and {@code TableService} implementations.
 *
 * <p>The row model type is represented by {@code T}, which defines the structure of a table row.
 * The type {@code P} represents the field type being set within the table row model.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@FunctionalInterface
@Pandora(
      description = "UI table field descriptor and invoker used to set/read column values on row models.",
      tags = {"ui", "table", "table-field"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "table-field")
      }
)
public interface TableField<T> {

   /**
    * Creates a {@code TableField} from a given {@link BiConsumer}, which represents
    * a setter method for a specific field.
    *
    * @param consumer The setter method reference.
    * @param <T>      The type of the row model.
    * @param <P>      The type of the field being set.
    * @return A {@code TableField} instance.
    */
   @Pandora(
         description = "Create a TableField from a setter method reference.",
         tags = {"ui", "table", "table-field"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   static <T, P> TableField<T> of(
         @Pandora(
               description = "Setter method reference for the field."
         ) BiConsumer<T, P> consumer) {
      return (t, obj) -> consumer.accept(t, (P) obj);
   }

   /**
    * Invokes the setter method for the corresponding field in the provided instance.
    *
    * @param instance The object instance on which to set the field value.
    * @param o        The value to be set.
    * @throws IllegalAccessException    If the method is not accessible.
    * @throws InvocationTargetException If the method invocation fails.
    */
   void invoke(T instance, Object o) throws IllegalAccessException, InvocationTargetException;

}
