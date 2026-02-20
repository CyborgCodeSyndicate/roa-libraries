package io.cyborgcode.roa.ui.insertion;

import io.cyborgcode.roa.ui.insertion.Insertion;

/**
 * Defines the contract for inserting data into UI components.
 *
 * <p>This interface is responsible for processing objects and mapping their
 * values to UI components. It provides a way to automate the filling of
 * form-like structures in a UI by reading annotated fields and delegating
 * to the appropriate {@link Insertion} implementations.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface InsertionService {

   /**
    * Processes the provided data object, extracts annotated fields, and inserts values
    * into corresponding UI components.
    *
    * @param data The object containing values to be inserted into UI components.
    */
   void insertData(Object data);
}
