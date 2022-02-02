package de.miesner.claus.bingo.table;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexExpression.LINE_BREAK;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_END;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_ROW_BREAK;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_ROW_SEPARATOR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TableTest {
  Table table;
  List<String> sampleInputs = List.of(
          "one", "two", "three",
          "four", "five", "six",
          "seven", "eight", "nine"
  );

  @BeforeEach
  void setup() {
    this.table = new Table((int) Math.sqrt(sampleInputs.size()), sampleInputs);
  }

  @Test
  void testZeroInputsCreateRow() {
    this.table = new Table(0, List.of());
    assertThat(table.createRows()).isEqualTo(new Row[0]);
  }

  @Test
  void testCorrectInputs() {
    Row[] expected = {
            new Row(List.of("one", "two", "three")),
            new Row(List.of("four", "five", "six")),
            new Row(List.of("seven", "eight", "nine"))
    };

    assertThat(table.createRows()).isEqualTo(expected);
  }

  @Test
  void testTooManyInputs() {
    assertThrows(IllegalArgumentException.class, () -> new Table(1, List.of("one", "two")));
  }

  @Test
  void testTooManyRowsWanted() {
    assertThrows(IllegalArgumentException.class, () -> new Table(1, List.of()));
  }

  @Test
  void testToStringWithoutAnyRows() {
    this.table = new Table(0, List.of());

    String expected = "";
    assertThat(table.toString()).isEqualTo(expected);
  }

  @Test
  void testToStringWithOneEntry() {
    this.table = new Table(1, List.of("one"));
    String expected = "\\begin{table}" + LINE_BREAK +
            "\\begin{tabular}{|c|}" + LINE_BREAK +
            TABLE_ROW_SEPARATOR + LINE_BREAK +
            "one" + LINE_BREAK +
            TABLE_ROW_BREAK + LINE_BREAK +
            TABLE_ROW_SEPARATOR + LINE_BREAK+
            TABLE_END;
    assertThat(table.toString()).isEqualTo(expected);
  }

  @Test
  void testToStringWithMultipleEntries() {

    String expected = "\\begin{table}" + LINE_BREAK +
            "\\begin{tabular}{|c|c|c|}" + LINE_BREAK +
            TABLE_ROW_SEPARATOR + LINE_BREAK +
            "one & two & three" + LINE_BREAK +
            TABLE_ROW_BREAK + LINE_BREAK +
            TABLE_ROW_SEPARATOR + LINE_BREAK +
            "four & five & six" + LINE_BREAK +
            TABLE_ROW_BREAK + LINE_BREAK +
            TABLE_ROW_SEPARATOR + LINE_BREAK +
            "seven & eight & nine" + LINE_BREAK +
            TABLE_ROW_BREAK + LINE_BREAK +
            TABLE_ROW_SEPARATOR + LINE_BREAK +
            TABLE_END;
    assertThat(table.toString()).isEqualTo(expected);
  }
}
