package io.cyborgcode.roa.validator.core;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a validation rule used in test assertions.
 *
 * <p>This class defines the structure of an assertion, specifying what is being validated,
 * the type of validation, the expected value, and whether it should be treated as a soft assertion.
 * Assertions can be applied in various testing contexts, including API responses, UI elements,
 * and database records.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Getter
@SuppressWarnings("java:S3740")
public final class Assertion {

   /**
    * The subject of the assertion, specifying what is being validated.
    */
   private final AssertionTarget target;

   /**
    * Provides a supplementary identifier for targeted validation.
    */
   @Setter
   private String key;

   /**
    * Indicates the logical operation for this validation.
    */
   private final AssertionType<?> type;

   /**
    * The reference value expected by this assertion.
    */
   private final Object expected;

   /**
    * Determines if the assertion is a soft assertion.
    */
   private final boolean soft;

   @Builder
   private Assertion(AssertionTarget target,
                     String key,
                     AssertionType<?> type,
                     Object expected,
                     boolean soft) {

      StringBuilder missing = new StringBuilder();
      if (target == null) {
         missing.append("target, ");
      }
      if (type == null) {
         missing.append("type, ");
      }
      if (expected == null) {
         missing.append("expected, ");
      }

      if (!missing.isEmpty()) {
         missing.setLength(missing.length() - 2);
         throw new IllegalArgumentException("Missing required fields: [" + missing + "]");
      }

      this.target = target;
      this.key = key;
      this.type = type;
      this.expected = expected;
      this.soft = soft;
   }


}