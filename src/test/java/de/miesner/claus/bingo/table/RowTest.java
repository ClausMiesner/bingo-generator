package de.miesner.claus.bingo.table;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexExpression.MAX_CHARS_PER_ROW;
import static org.assertj.core.api.Assertions.assertThat;

class RowTest {

  Row row = new Row(List.of());

  @Test
  void testToStringWithoutWords() {
    String expected = "";
    assertThat(row.toString()).isEqualTo(expected);
  }

  @Test
  void testToStringOneEntry() {
    this.row = new Row(List.of("one"));
    String expected = "one";
    assertThat(row.toString()).isEqualTo(expected);
  }

  @Test
  void testToStringTwoEntries() {
    this.row = new Row(List.of("one", "two"));
    String expected = "one & two";
    assertThat(row.toString()).isEqualTo(expected);
  }

  @Test
  void testToStringMultipleEntries() {
    this.row = new Row(List.of("one", "two", "three", "four"));
    String expected = "one & two & three & four";
    assertThat(row.toString()).isEqualTo(expected);
  }

  @Test
  void testNullEqual() {
    assertThat(row.equals(null)).isFalse();
  }

  @Test
  void testNoEntriesEqual() {
    this.row = new Row(List.of());
    var equalRow = new Row(List.of());
    assertThat(row.equals(equalRow)).isTrue();
  }

  @Test
  void testOneHasEntryOtherNotEqual() {
    this.row = new Row(List.of("one"));
    var differentRow = new Row(List.of());
    assertThat(row.equals(differentRow)).isFalse();
  }

  @Test
  void testBothSameEntriesEqual() {
    this.row = new Row(List.of("one"));
    var equalRow = new Row(List.of("one"));
    assertThat(row.equals(equalRow)).isTrue();
  }

  @Test
  void testSplitNoSplitNeeded() {
    String word = "one";
    String expected = "one";
    assertThat(word.length()).as("The test is valid.").isLessThanOrEqualTo(MAX_CHARS_PER_ROW);

    assertThat(row.split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  @Test
  void testSplitWordHasMaxLength() {
    String word = "abbreviation";
    String expected = "abbreviation";
    assertThat(word.length()).as("The test is valid.").isEqualTo(MAX_CHARS_PER_ROW);

    assertThat(row.split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  @Test
  void testSplitWordTooLong() {
    String word = "jackhammering";
    String expected = "jackhammer- ing";
    assertThat(12).as("The test is valid.").isEqualTo(MAX_CHARS_PER_ROW);

    assertThat(row.split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  @Test
  void testSplitTwoWordsFit() {
    String word = "jacks hammer";
    String expected = "jacks hammer";
    assertThat(word.length()).as("The test is valid.").isLessThanOrEqualTo(MAX_CHARS_PER_ROW);

    assertThat(row.split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  @Test
  void testSplitTwoWordsDontFitButSpaceIsAlreadyThere() {
    String word = "jack's hammering";
    String expected = "jack's hammering";
    assertThat(word.length()).as("The test is valid.").isGreaterThan(MAX_CHARS_PER_ROW);
    assertThat(word.indexOf(" ")).as("The test is valid.").isLessThanOrEqualTo(MAX_CHARS_PER_ROW - 1);

    assertThat(row.split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  @Test
  void testSplitMultipleWordsSpacesPresent() {
    String word = "jacks hammer hammers";
    String expected = "jacks hammer hammers";
    assertThat(word.length()).as("The test is valid.").isGreaterThan(MAX_CHARS_PER_ROW);
    int indexFirstSpace = word.indexOf(" ");
    int indexSecondSpace = word.indexOf(" ", indexFirstSpace + 1);
    assertThat(indexFirstSpace).as("The test is valid.").isLessThanOrEqualTo(MAX_CHARS_PER_ROW);
    assertThat(indexSecondSpace - indexFirstSpace).as("The test is valid.").isLessThanOrEqualTo(MAX_CHARS_PER_ROW);

    assertThat(row.split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  @Test
  void testSplitMultipleWordsNeedToBeSplit() {
    String word = "jack's jackhammering massively";
    String expected = "jacks's ja- ckhammering massively";
    assertThat(12).as("The test is valid.").isEqualTo(MAX_CHARS_PER_ROW);

    assertThat(row.split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }
}
