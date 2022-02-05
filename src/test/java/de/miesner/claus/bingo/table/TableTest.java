package de.miesner.claus.bingo.table;

import de.miesner.claus.bingo.random.TermRandomizer;
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
    this.table = new TableBuilder()
            .withPossibleBingoTerms(sampleInputs)
            .withRowsPerTable((int) Math.sqrt(sampleInputs.size()))
            .withTermRandomizer(new PassThroughTermRandomizer(sampleInputs))
            .build();
  }

  @Test
  void testTooFewInformationRows() {
    assertThrows(IllegalArgumentException.class, () -> new TableBuilder()
            .withRowsPerTable(0)
            .withPossibleBingoTerms(List.of("one"))
            .withTermRandomizer(new TermRandomizer())
            .build(), "No rows requested.");
    assertThrows(IllegalArgumentException.class, () -> new TableBuilder()
            .withRowsPerTable(-1)
            .withPossibleBingoTerms(List.of("one"))
            .withTermRandomizer(new TermRandomizer())
            .build(), "Negative number of rows requested.");
  }

  @Test
  void testTooFewInformationTerms() {
    assertThrows(IllegalArgumentException.class, () -> new TableBuilder()
            .withRowsPerTable(1)
            .withPossibleBingoTerms(null)
            .withTermRandomizer(new TermRandomizer())
            .build(), "Terms are null.");
    assertThrows(IllegalArgumentException.class, () -> new TableBuilder()
            .withRowsPerTable(1)
            .withPossibleBingoTerms(List.of())
            .withTermRandomizer(new PassThroughTermRandomizer(List.of()))
            .build(), "Too few terms.");
  }

  @Test
  void testTooFewInformationTermRandomizer() {
    assertThrows(IllegalArgumentException.class, () -> new TableBuilder()
            .withRowsPerTable(1)
            .withPossibleBingoTerms(List.of("one"))
            .withTermRandomizer(null)
            .build(), "Term randomizer is null.");
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
  void testMoreInputsThanRequired() {
    List<String> terms = List.of("one", "two");
    this.table = new TableBuilder()
            .withPossibleBingoTerms(terms)
            .withRowsPerTable(1)
            .withTermRandomizer(new PassThroughTermRandomizer(terms))
            .build();
    String expected = "\\begin{table}" + LINE_BREAK +
            "\\begin{tabular}{ c }" + LINE_BREAK +
            TABLE_ROW_SEPARATOR + LINE_BREAK +
            "one" + LINE_BREAK +
            TABLE_ROW_BREAK + LINE_BREAK +
            TABLE_ROW_SEPARATOR + LINE_BREAK +
            TABLE_END;

    assertThat(table.toString()).isEqualTo(expected);
  }

  @Test
  void testTooManyRowsWanted() {
    assertThrows(IllegalArgumentException.class, () -> new TableBuilder()
            .withRowsPerTable(1)
            .withPossibleBingoTerms(List.of())
            .withTermRandomizer(new PassThroughTermRandomizer(List.of()))
            .build());
  }

  @Test
  void testToStringWithOneEntry() {
    this.table = new TableBuilder()
            .withPossibleBingoTerms(List.of("one"))
            .withRowsPerTable(1)
            .withTermRandomizer(new PassThroughTermRandomizer(List.of("one")))
            .build();

    String expected = "\\begin{table}" + LINE_BREAK +
            "\\begin{tabular}{ c }" + LINE_BREAK +
            TABLE_ROW_SEPARATOR + LINE_BREAK +
            "one" + LINE_BREAK +
            TABLE_ROW_BREAK + LINE_BREAK +
            TABLE_ROW_SEPARATOR + LINE_BREAK +
            TABLE_END;
    assertThat(table.toString()).isEqualTo(expected);
  }

  @Test
  void testToStringWithMultipleEntries() {

    String expected = "\\begin{table}" + LINE_BREAK +
            "\\begin{tabular}{ c c c }" + LINE_BREAK +
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
