package io.cyborgcode.roa.ui.components.table.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TableCellLocatorInfo<L> {

   private L cellSelector;

   private String tableSection;

   private L cellTextSelector;

   private L headerCellSelector;


}
