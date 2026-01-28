package io.cyborgcode.roa.framework.parameters;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;

/**
 * Represents a deferred or lazy evaluation of an object.
 *
 * <p>Implementations of this interface provide a mechanism for delaying
 * the creation of an object until it is explicitly needed.
 * This is particularly useful for scenarios where test data or
 * dependencies should not be instantiated at the beginning of a test
 * but rather at a specific point during execution.
 *
 * <p>The generic type {@code T} represents the type of object that will be
 * lazily instantiated.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@FunctionalInterface
@Pandora(
      description = "Lazy/deferred value wrapper. The contained object is only materialized when create() is called "
            + "(commonly used for on-demand @Craft test data).",
      tags = {"framework", "test-data"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "late")
      }
)
public interface Late<T> {

   /**
    * Retrieves or initializes the object when required.
    *
    * <p>The actual instantiation logic is deferred until this method is invoked.
    * The returned object should be fully constructed and ready for use.
    *
    * @return The instantiated object of type {@code T}.
    */
   @Pandora(
         description = "Materialize and return the underlying value on demand. "
               + "May create a new instance or return a cached one, depending on implementation."
   )
   T create();

}
