package de.miesner.claus.bingo.table;


import java.util.ArrayList;
import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexExpression.CENTER_ALIGN;

public class TableCreator {

  private final int requiredTables;
  private final TermRandomizer termRandomizer;
  private final int rowsPerTable;
  private char textAlignment = CENTER_ALIGN;
  private boolean hasColumnSeparator = false;

  public TableCreator(List<String> possibleBingoTerms, int rowsPerTable, int requiredTables) {
    this.requiredTables = requiredTables;
    this.termRandomizer = new TermRandomizer(possibleBingoTerms);
    this.rowsPerTable = rowsPerTable;
  }

  public TableCreator withTextAlignment(char alignmentOption) {
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
      // We need to use a constructor with more parameters to pass all values over.
      // We can also make table package private. It should only be instantiated via this
      tables.add(new Table(rowsPerTable, termsForTable));
      termRandomizer.reset();
    }
    return tables;
  }
}
