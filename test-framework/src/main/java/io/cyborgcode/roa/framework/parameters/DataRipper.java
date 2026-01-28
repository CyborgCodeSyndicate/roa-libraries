package io.cyborgcode.roa.framework.parameters;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import java.util.function.Consumer;

/**
 * Defines a contract for cleaning up test data after execution.
 *
 * <p>Implementations of this interface specify cleanup logic for removing
 * test-generated data, ensuring that test environments remain in a consistent state.
 *
 * <p>The cleanup operation is executed via a {@link Consumer} that accepts a {@link SuperQuest} instance.
 * Each implementation also provides an enumeration reference for structured identification.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Contract for post-test cleanup actions. Implementations provide a cleanup Consumer "
            + "that runs with the current SuperQuest and expose an enum constant identifying the cleanup target.",
      tags = {"framework", "cleanup"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "data-ripper")
      }
)
public interface DataRipper<T extends Enum<T>> {

   /**
    * Provides the cleanup operation for test-generated data.
    *
    * <p>Implementations return a {@link Consumer} that defines the cleanup logic,
    * allowing for structured and reusable test data removal.
    *
    * @return A {@link Consumer} responsible for executing the cleanup process.
    */
   @Pandora(
         description = "Returns the cleanup operation to execute after the test (runs with the current SuperQuest)."
   )
   Consumer<SuperQuest> eliminate();

   /**
    * Returns the enumeration instance associated with the cleanup operation.
    *
    * <p>This ensures that cleanup actions can be identified and referenced
    * in a structured manner within the framework.
    *
    * @return An {@link Enum} instance representing the cleanup operation.
    */
   @Pandora(
         description = "Returns the enum constant that identifies this cleanup target (used by @Ripper selection)."
   )
   T enumImpl();

}
