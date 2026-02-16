package io.cyborgcode.roa.ui.playwright.components.base;

/**
 * Represents a UI component type.
 *
 * <p>This interface is implemented by enums that define specific component types
 * (e.g., input types, button types). Each enum constant identifies a distinct
 * UI element variation that the framework can interact with.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ComponentType {

   /**
    * Retrieves the enum constant representing this component type.
    *
    * @return The enum constant.
    */
   Enum<?> getType();
}
