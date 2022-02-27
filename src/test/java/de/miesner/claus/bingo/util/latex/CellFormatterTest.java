package de.miesner.claus.bingo.util.latex;

import org.junit.jupiter.api.Test;

import static de.miesner.claus.bingo.util.latex.CellFormatter.split;
import static de.miesner.claus.bingo.util.latex.Expression.MAX_CHARS_PER_ROW;
import static org.assertj.core.api.Assertions.assertThat;

class CellFormatterTest {

  @Test
  void testSplitNoSplitNeeded() {
    String word = "one";
    String expected = "one";
    assertThat(word.length()).as("The test is valid.").isLessThanOrEqualTo(MAX_CHARS_PER_ROW);

    assertThat(split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  @Test
  void testSplitWordHasMaxLength() {
    String word = "abbreviation";
    String expected = "abbreviation";
    assertThatMaxCharsIs(word.length());

    assertThat(split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  @Test
  void testSplitWordTooLong() {
    String word = "jackhammering";
    String expected = "jackhammeri- ng";
    assertThatMaxCharsIs(12);

    assertThat(split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  @Test
  void testSplitTwoWordsFit() {
    String word = "jacks hammer";
    String expected = "jacks hammer";
    assertThat(word.length()).as("The test is valid.").isLessThanOrEqualTo(MAX_CHARS_PER_ROW);

    assertThat(split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  @Test
  void testSplitTwoWordsDontFitButSpaceIsAlreadyThere() {
    String word = "jack's hammering";
    String expected = "jack's hammering";
    assertThat(word.length()).as("The test is valid.").isGreaterThan(MAX_CHARS_PER_ROW);
    assertThat(word.indexOf(" ")).as("The test is valid.").isLessThanOrEqualTo(MAX_CHARS_PER_ROW - 1);

    assertThat(split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
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

    assertThat(split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  @Test
  void testSplitMultipleWordsNeedToBeSplit() {
    String word = "jack's jackhammering massively";
    String expected = "jack's jackhammeri- ng massively";
    assertThatMaxCharsIs(12);

    assertThat(split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  @Test
  void testSplitSpacesMakeWordTooLong() {
    String word = "jacks    hammer";
    String expected = "jacks hammer";
    assertThatMaxCharsIs(12);

    assertThat(split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  @Test
  void testSplitGetsRidOfSpaces() {
    String word = "   jackhammer   ";
    String expected = "jackhammer";
    assertThatMaxCharsIs(12);

    assertThat(split(word, MAX_CHARS_PER_ROW)).isEqualTo(expected);
  }

  private void assertThatMaxCharsIs(int actual) {
    assertThat(actual).as("The test is valid.").isEqualTo(MAX_CHARS_PER_ROW);
  }
}