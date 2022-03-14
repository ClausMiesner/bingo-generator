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
}
