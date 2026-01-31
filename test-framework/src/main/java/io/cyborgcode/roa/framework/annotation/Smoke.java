package io.cyborgcode.roa.framework.annotation;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Tag;

/**
 * Marks a test or test class as part of the Smoke Test Suite.
 *
 * <p>This annotation is used to categorize test methods or entire test classes
 * as "Smoke" tests. Smoke tests are typically a subset of test cases
 * that verify the most critical functionalities of an application.
 *
 * <p>The annotation is retained at runtime and applies to both class and method levels.
 * It also integrates with JUnit 5 by applying the {@code @Tag("Smoke")} annotation,
 * allowing test filtering via tags.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Tag("Smoke")
@Pandora(
      description = "JUnit tag annotation for smoke suite. "
            + "Apply on test classes or test methods to mark them as Smoke.",
      tags = {"framework", "annotation"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/general-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "junit-tag-annotation"),
         @PandoraOptions.Meta(key = "tag", value = "Smoke"),
         @PandoraOptions.Meta(key = "scope", value = "class-or-method")
      }
)
public @interface Smoke {

}
