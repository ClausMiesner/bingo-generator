package de.miesner.claus.bingo.table;

import de.miesner.claus.bingo.random.ITermRandomizer;
import de.miesner.claus.bingo.util.latex.LatexTextAlignment;

import java.util.ArrayList;
import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexTextAlignment.CENTER_ALIGN;

class TableBuilder {

  private ITermRandomizer termRandomizer;
  private int numberOfRows;
  private LatexTextAlignment textAlignment = CENTER_ALIGN;
  private boolean hasColumnSeparator = false;
  private List<String> possibleBingoTerms;

  public TableBuilder withTermRandomizer(ITermRandomizer termRandomizer) {
    this.termRandomizer = termRandomizer;
    return this;
  }

  public TableBuilder withPossibleBingoTerms(List<String> possibleBingoTerms) {
    this.possibleBingoTerms = possibleBingoTerms;
    return this;
  }

  public TableBuilder withRowsPerTable(int numberOfRows) {
    this.numberOfRows = numberOfRows;
    return this;
  }

  public TableBuilder withTextAlignment(LatexTextAlignment alignmentOption) {
    this.textAlignment = alignmentOption;
    return this;
  }

  public TableBuilder withColumnSeparator(boolean hasColumnSeparator) {
    this.hasColumnSeparator = hasColumnSeparator;
    return this;
  }

  public Table build() {
    checkFieldRequirements();
    int fieldsPerRow = numberOfRows;

    List<String> termsForTable = new ArrayList<>(numberOfRows * fieldsPerRow);
    for (int row = 0; row < numberOfRows; row++) {
      for (int field = 0; field < fieldsPerRow; field++) {
        termsForTable.add(termRandomizer.getNextTerm());
      }
    }
    return new Table(numberOfRows, termsForTable, textAlignment, hasColumnSeparator);
  }

  private void checkFieldRequirements() {
    if (requiredValueMissing()) {
      throw new IllegalArgumentException(
              "You need to specify reasonable value for rows per table (was: '" + numberOfRows + "') and " +
                      "supply a matching list of possible terms. " +
                      "Also make sure to provide a term randomizer.");
    }
    if (rowRequirementTermsMismatch()) {
      throw new IllegalArgumentException("Number of rows mismatches provided number of terms.");
    }
  }

  private boolean rowRequirementTermsMismatch() {
    return numberOfRows > Math.sqrt(possibleBingoTerms.size());
  }

  private boolean requiredValueMissing() {
    return possibleBingoTerms == null || numberOfRows <= 0 || termRandomizer == null;
  }
}
