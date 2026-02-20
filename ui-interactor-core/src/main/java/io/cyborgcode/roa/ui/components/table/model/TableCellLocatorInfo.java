package io.cyborgcode.roa.ui.components.table.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TableCellLocatorInfo<V> {

   private V cellSelector;

   private String tableSection;

   private V cellTextSelector;

   private V headerCellSelector;


}
