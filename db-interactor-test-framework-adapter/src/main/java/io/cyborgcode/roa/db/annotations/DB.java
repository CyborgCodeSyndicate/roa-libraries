package io.cyborgcode.roa.db.annotations;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.db.extensions.DbHookExtension;
import io.cyborgcode.roa.db.extensions.DbTestExtension;
import io.cyborgcode.roa.framework.annotation.FrameworkAdapter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Marks a test class as a database-related test.
 *
 * <p>This annotation integrates database testing functionality into test classes.
 * It ensures database connections are properly managed by applying the
 * {@link DbTestExtension}, which automatically closes connections after test execution.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Annotation added on a test class to enable database testing support, "
            + "including database connection management, hooks, and lifecycle handling.",
      tags = {"db", "test", "annotation"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "db-annotation"),
         @PandoraOptions.Meta(key = "scope", value = "class")
      }
)
@ExtendWith({DbTestExtension.class, DbHookExtension.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@FrameworkAdapter(basePackages = {"io.cyborgcode.roa.db"})
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public @interface DB {
}
