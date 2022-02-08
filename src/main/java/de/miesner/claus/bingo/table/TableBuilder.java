package de.miesner.claus.bingo.table;

import com.google.common.annotations.VisibleForTesting;
import de.miesner.claus.bingo.random.TermRandomizer;
import de.miesner.claus.bingo.util.latex.LatexTextAlignment;

import java.util.ArrayList;
import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexTextAlignment.CENTER_ALIGN;

class TableBuilder {

  private TermRandomizer termRandomizer;
  private int numberOfRows;
  private LatexTextAlignment textAlignment = CENTER_ALIGN;
  private boolean hasColumnSeparator = false;
  private List<String> possibleBingoTerms;
  private int maxOccurrencesForTerm = 1;

  /**
   * <p>
   * Required.
   * Adds a {@link TermRandomizer} for choosing random words.
   * </p>
   *
   * @param termRandomizer to choose random terms for a table
   * @return the table builder object
   */
  public TableBuilder withTermRandomizer(TermRandomizer termRandomizer) {
    this.termRandomizer = termRandomizer;
    return this;
  }

  /**
   * <p>
   * Required.
   * Adds a list holding all possible bingo terms.
   * Actual terms of the table will be chosen by a {@link TermRandomizer} (see {@link #withTermRandomizer(TermRandomizer)}).
   * </p>
   *
   * @param possibleBingoTerms list holding all possible terms
   * @return the table builder object
   */
  public TableBuilder withPossibleBingoTerms(List<String> possibleBingoTerms) {
    this.possibleBingoTerms = possibleBingoTerms;
    return this;
  }

  /**
   * <p>
   * Required.
   * Specifies the tables number of rows.
   * Implicitly also specifies the number of columns
   * and therefore the number of fields in the square table.
   * </p>
   *
   * @param numberOfRows the number of rows in the table
   * @return the table builder object
   */
  public TableBuilder withNumberOfRows(int numberOfRows) {
    this.numberOfRows = numberOfRows;
    return this;
  }

  /**
   * <p>
   * Optional.
   * Specifies the align style of text in all fields of the table.
   * The default is center alignment. (see {@link LatexTextAlignment#CENTER_ALIGN})
   * </p>
   *
   * @param alignmentOption the {@link LatexTextAlignment} option to use
   * @return the table builder object
   */
  public TableBuilder withTextAlignment(LatexTextAlignment alignmentOption) {
    this.textAlignment = alignmentOption;
    return this;
  }

  /**
   * <p>
   * Optional.
   * Specifies whether the table should separate columns with a straight line.
   * Default is <code>false</code>. Set to <code>true</code> for a line.
   * </p>
   *
   * @param hasColumnSeparator whether to separate columns with a printed line
   * @return the table builder object
   */
  public TableBuilder withColumnSeparator(boolean hasColumnSeparator) {
    this.hasColumnSeparator = hasColumnSeparator;
    return this;
  }

  public TableBuilder withDoubledTerms(int maxOccurrencesForTerm) {
    this.maxOccurrencesForTerm = maxOccurrencesForTerm;
    return this;
  }

  /**
   * <p>
   * Creates the {@link Table} object with all set or default specifications.
   * </p>
   *
   * @return the table according to all set options
   */
  public Table build() {
    checkFieldRequirements();
    int fieldsPerRow = numberOfRows;

    List<String> termsForTable = new ArrayList<>(numberOfRows * fieldsPerRow);
    addTermsRandomly(fieldsPerRow, termsForTable);
    return new Table(numberOfRows, termsForTable, textAlignment, hasColumnSeparator);
  }

  private void addTermsRandomly(int fieldsPerRow, List<String> termsForTable) {
    for (int row = 0; row < numberOfRows; row++) {
      for (int field = 0; field < fieldsPerRow; field++) {
        termsForTable.add(termRandomizer.getNextTerm());
      }
    }
  }

  private void checkFieldRequirements() {
    if (requiredValueMissing()) {
      throw new IllegalArgumentException(
              "You need to specify reasonable value for rows per table (was: '" + numberOfRows + "') and " +
                      "supply a matching list of possible terms. " +
                      "Also make sure to provide a term randomizer.");
    }
    if (maxOccurrencesForTerm < 1) {
      this.maxOccurrencesForTerm = 1;
    }
    if (rowRequirementTermsMismatch()) {
      throw new IllegalArgumentException("Number of rows mismatches provided number of terms.");
    }
  }

  @VisibleForTesting
  boolean rowRequirementTermsMismatch() {
    if (maxOccurrencesForTerm == 1) {
      return square(numberOfRows) > possibleBingoTerms.size();
    }
    return square(numberOfRows) > possibleBingoTerms.size() * maxOccurrencesForTerm;
  }

  private double square(int number) {
    return Math.pow(number, 2);
  }

  private boolean requiredValueMissing() {
    return possibleBingoTerms == null || numberOfRows <= 0 || termRandomizer == null;
  }
}
