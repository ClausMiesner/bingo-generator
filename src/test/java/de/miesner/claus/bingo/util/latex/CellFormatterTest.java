package de.miesner.claus.bingo.util.latex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static de.miesner.claus.bingo.util.latex.LatexExpression.MAX_CHARS_PER_ROW;
import static de.miesner.claus.bingo.util.latex.LatexExpression.MAX_ROWS_PER_CELL;
import static org.assertj.core.api.Assertions.assertThat;

class CellFormatterTest {

  CellFormatter cellFormatter;

  @BeforeEach
  void setUp() {
    this.cellFormatter = new CellFormatter(MAX_CHARS_PER_ROW, MAX_ROWS_PER_CELL);
  }

  @Test
  @SuppressWarnings("UnnecessaryLocalVariable")
  void testFormatToFitCell() {
    String testString = "word";
    String expected = testString;

    assertThat(cellFormatter.formatToFitCell(testString)).isEqualTo(expected);
  }

  @Test
  void testFormatWithEmptyAndBlankString() {
    String blank = "   ";
    String empty = "";

    assertThat(cellFormatter.formatToFitCell(blank)).isEmpty();
    assertThat(cellFormatter.formatToFitCell(empty)).isEmpty();
  }

  @Test
  void testFormatWordHasMaxLength() {
    String word = "abbreviation";
    String expected = "abbreviation";
    assertThat(word.length()).as("The test is not valid.").isEqualTo(MAX_CHARS_PER_ROW);

    assertThat(cellFormatter.formatToFitCell(word)).isEqualTo(expected);
  }

  @Test
  void testSplitWordTooLong() {
    String word = "jackhammering";
    String expected = "jackhammeri- ng";
    assertThat(word.length()).as("The test is not valid.").isGreaterThan(MAX_CHARS_PER_ROW);

    assertThat(cellFormatter.formatToFitCell(word)).isEqualTo(expected);
  }

  @Test
  void testOneWordInExpressionTooLong() {
    String expression = "This is hammer";
    int maxCharsPerRow = 4;
    cellFormatter = new CellFormatter(maxCharsPerRow, MAX_ROWS_PER_CELL);
    String expected = "This is ham- mer";

    assertThat(cellFormatter.formatToFitCell(expression)).isEqualTo(expected);
  }

  @Test
  void wordTooLongForCellTruncate() {
    String veryLongWord = "supercalifragilisticexpialidocious";
    int maxCharsPerRow = 8;
    int maxRowsPerCell = 1;
    cellFormatter = new CellFormatter(maxCharsPerRow, maxRowsPerCell);
    String expected = "super...";

    assertThat(cellFormatter.formatToFitCell(veryLongWord)).isEqualTo(expected);
  }
}
