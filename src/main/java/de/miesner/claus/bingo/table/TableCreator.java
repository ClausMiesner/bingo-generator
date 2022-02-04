package de.miesner.claus.bingo.table;


import de.miesner.claus.bingo.util.latex.LatexTextAlignment;

import java.util.ArrayList;
import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexTextAlignment.CENTER_ALIGN;


public class TableCreator {

  private final int requiredTables;
  private final TermRandomizer termRandomizer;
  private final int rowsPerTable;
  private LatexTextAlignment textAlignment = CENTER_ALIGN;
  private boolean hasColumnSeparator = false;

  public TableCreator(List<String> possibleBingoTerms, int rowsPerTable, int requiredTables) {
    this.requiredTables = requiredTables;
    this.termRandomizer = new TermRandomizer(possibleBingoTerms);
    this.rowsPerTable = rowsPerTable;
  }

  public TableCreator withTextAlignment(LatexTextAlignment alignmentOption) {
    this.textAlignment = alignmentOption;
    return this;
  }

  public TableCreator withColumnSeparator() {
    this.hasColumnSeparator = true;
    return this;
  }

  public List<Table> create() {
    int fieldsPerRow = rowsPerTable;
    List<Table> tables = new ArrayList<>(requiredTables);

    // This is a very large for loop construct. Split it up!
    for (int tableNumber = 0; tableNumber < requiredTables; tableNumber++) {
      List<String> termsForTable = new ArrayList<>(rowsPerTable * fieldsPerRow);
      for (int row = 0; row < rowsPerTable; row++) {
        for (int field = 0; field < fieldsPerRow; field++) {
          termsForTable.add(termRandomizer.getNextTerm());
        }
      }
      tables.add(new Table(rowsPerTable, termsForTable, textAlignment, hasColumnSeparator));
      termRandomizer.reset();
    }
    return tables;
  }
}
