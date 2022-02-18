package de.miesner.claus.bingo.table;

import de.miesner.claus.bingo.MisconfigurationException;
import de.miesner.claus.bingo.random.BasicTermRandomizer;
import de.miesner.claus.bingo.random.TermRandomizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexExpression.LINE_BREAK;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_END;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_ROW_BREAK;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_ROW_SEPARATOR;
import static de.miesner.claus.bingo.util.latex.LatexTextAlignment.LEFT_ALIGN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TableTest {
  Table table;
  List<String> sampleWords = List.of(
          "one", "two", "three",
          "four", "five", "six",
          "seven", "eight", "nine"
  );

  TermRandomizer mockTermRandomizer = mock(TermRandomizer.class);

  @BeforeEach
  void setup() {
    when(mockTermRandomizer.getNextTerm()).thenReturn("one", "two", "three",
            "four", "five", "six",
            "seven", "eight", "nine");
    this.table = Table.builder()
            .withPossibleBingoTerms(sampleWords)
            .withNumberOfRows((int) Math.sqrt(sampleWords.size()))
            .withTermRandomizer(mockTermRandomizer)
            .build();
  }

  @Test
  void testTooFewInformationRows() {
    assertThrows(MisconfigurationException.class, () -> Table.builder()
            .withNumberOfRows(0)
            .withPossibleBingoTerms(List.of("one"))
            .withTermRandomizer(new BasicTermRandomizer())
            .build(), "No rows requested.");
    assertThrows(MisconfigurationException.class, () -> Table.builder()
            .withNumberOfRows(-1)
            .withPossibleBingoTerms(List.of("one"))
            .withTermRandomizer(new BasicTermRandomizer())
            .build(), "Negative number of rows requested.");
  }

  @Test
  void testTooFewInformationTerms() {
    assertThrows(MisconfigurationException.class, () -> Table.builder()
            .withNumberOfRows(1)
            .withPossibleBingoTerms(null)
            .withTermRandomizer(new BasicTermRandomizer())
            .build(), "Terms are null.");
    assertThrows(MisconfigurationException.class, () -> Table.builder()
            .withNumberOfRows(1)
            .withPossibleBingoTerms(List.of())
            .withTermRandomizer(mockTermRandomizer)
            .build(), "Too few terms.");
  }

  @Test
  void testTooFewInformationTermRandomizer() {
    assertThrows(MisconfigurationException.class, () -> Table.builder()
            .withNumberOfRows(1)
            .withPossibleBingoTerms(List.of("one"))
            .withTermRandomizer(null)
            .build(), "Term randomizer is null.");
  }

  @Test
  void testCorrectWords() {
    Row[] expected = {
            new Row(List.of("one", "two", "three")),
            new Row(List.of("four", "five", "six")),
            new Row(List.of("seven", "eight", "nine"))
    };

    assertThat(table.createRows()).isEqualTo(expected);
  }

  @Test
  void testMoreWordsThanRequired() {
    List<String> terms = List.of("one", "two");
    when(mockTermRandomizer.getNextTerm()).thenReturn("one", "two");
    this.table = Table.builder()
            .withPossibleBingoTerms(terms)
            .withNumberOfRows(1)
            .withTermRandomizer(mockTermRandomizer)
            .build();
    String expected = LINE_BREAK + "\\begin{table}" + LINE_BREAK +
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
    assertThrows(MisconfigurationException.class, () -> Table.builder()
            .withNumberOfRows(1)
            .withPossibleBingoTerms(List.of())
            .withTermRandomizer(mockTermRandomizer)
            .build());
  }

  @Test
  void testToStringWithOneEntry() {
    when(mockTermRandomizer.getNextTerm()).thenReturn("one");
    this.table = Table.builder()
            .withPossibleBingoTerms(List.of("one"))
            .withNumberOfRows(1)
            .withTermRandomizer(mockTermRandomizer)
            .build();

    String expected = LINE_BREAK + "\\begin{table}" + LINE_BREAK +
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

    String expected = LINE_BREAK + "\\begin{table}" + LINE_BREAK +
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

  @Test
  void testToStringWithLeftAlignAndSeparator() {
    when(mockTermRandomizer.getNextTerm()).thenReturn("one", "two", "three",
            "four", "five", "six",
            "seven", "eight", "nine");
    this.table = Table.builder()
            .withPossibleBingoTerms(sampleWords)
            .withNumberOfRows(3)
            .withTermRandomizer(mockTermRandomizer)
            .withTextAlignment(LEFT_ALIGN)
            .withColumnSeparator()
            .build();
    String expected = LINE_BREAK + "\\begin{table}" + LINE_BREAK +
            "\\begin{tabular}{|l|l|l|}" + LINE_BREAK +
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

  @Test
  void testRowRequirementsSquareNumbers() {
    TableBuilder tableBuilder = Table.builder()
            .withNumberOfRows(3) // 9 Terms required
            .withPossibleBingoTerms(List.of("one", "two", "three"))
            .withMaxOccurrencesForTerm(3); // 3 * 3 = 9 == 9

    assertThat(tableBuilder.rowRequirementTermsMismatch()).isFalse();
  }

  @Test
  void testRowRequirementsNonSquareNumbers() {
    TableBuilder tableBuilder = Table.builder()
            .withNumberOfRows(3) // 9 Terms required
            .withPossibleBingoTerms(List.of("one", "two", "three", "four", "five"))
            .withMaxOccurrencesForTerm(2); // 2 * 5 = 10 > 9

    assertThat(tableBuilder.rowRequirementTermsMismatch()).isFalse();
  }

  @Test
  void testRowRequirementsMismatch() {
    TableBuilder tableBuilder = Table.builder()
            .withNumberOfRows(3) // 9 Terms required
            .withPossibleBingoTerms(List.of("one", "two", "three", "four"))
            .withMaxOccurrencesForTerm(2); // 2 * 4 = 8 < 9

    assertThat(tableBuilder.rowRequirementTermsMismatch()).isTrue();
  }

  @Test
  void testRowRequirementsMismatchNoDoubledTerms() {
    TableBuilder tableBuilder = Table.builder()
            .withNumberOfRows(3) // 9 Terms required
            .withPossibleBingoTerms(List.of("one", "two", "three", "four"));

    assertThat(tableBuilder.rowRequirementTermsMismatch()).isTrue();
  }

  @Test
  void testRowRequirementsNoDoubledTerms() {
    TableBuilder tableBuilder = Table.builder()
            .withNumberOfRows(2) // 4 Terms required
            .withPossibleBingoTerms(List.of("one", "two", "three", "four"));

    assertThat(tableBuilder.rowRequirementTermsMismatch()).isFalse();
  }
}
