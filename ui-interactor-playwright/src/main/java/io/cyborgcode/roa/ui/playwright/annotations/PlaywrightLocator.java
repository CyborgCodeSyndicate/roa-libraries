package io.cyborgcode.roa.ui.playwright.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies a CSS selector for locating a Playwright element.
 *
 * <p>This annotation is the Playwright equivalent of Selenium's {@code @FindBy}.
 * It provides a CSS selector string used to locate elements on the page.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface PlaywrightLocator {

   /**
    * The CSS selector used to locate the element.
    *
    * @return The CSS selector string.
    */
   String css() default "";

   /**
    * The XPath selector used to locate the element.
    *
    * @return The XPath selector string.
    */
   String xpath() default "";

   /**
    * The text content used to locate the element via {@code text=} selector.
    *
    * @return The text selector string.
    */
   String text() default "";

   /**
    * The test ID used to locate the element via {@code data-testid} attribute.
    *
    * @return The test ID string.
    */
   String testId() default "";

   /**
    * A raw Playwright selector string. Takes precedence over other attributes when set.
    *
    * @return The raw selector string.
    */
   String selector() default "";

}
