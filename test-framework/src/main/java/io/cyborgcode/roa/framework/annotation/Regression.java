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
 * The {@code Regression} annotation is used to mark test methods or test classes
 * as part of the regression test suite. This annotation is a custom extension of
 * JUnit's {@link Tag} annotation and automatically assigns the tag "Regression"
 * to any test it is applied to.
 *
 * <p>By using this annotation, test execution frameworks that support tagged execution,
 * such as JUnit and Maven Surefire Plugin, can selectively run regression tests.
 *
 * <h3>Integration:</h3>
 * This annotation is used in conjunction with test execution tools to filter
 * and execute regression-specific tests. It simplifies test categorization
 * and improves maintainability in large test suites.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Tag("Regression")
@Pandora(
      description = "JUnit tag annotation for regression suite. "
            + "Apply on test classes or test methods to mark them as Regression.",
      tags = {"framework", "annotation"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "junit-tag-annotation"),
         @PandoraOptions.Meta(key = "tag", value = "Regression"),
         @PandoraOptions.Meta(key = "scope", value = "class-or-method")
      }
)
public @interface Regression {
}
