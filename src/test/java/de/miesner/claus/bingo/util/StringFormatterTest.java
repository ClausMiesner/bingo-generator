package de.miesner.claus.bingo.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static de.miesner.claus.bingo.util.StringFormatter.DEFAULT_ELLIPSIS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

class StringFormatterTest {

  StringFormatter stringFormatter = new StringFormatter();
  String example = "example";
  int sameLength = example.length();

  @Test
  void testTruncateNoTruncationMoreCharsAllowed() {
    assertThat(stringFormatter.truncate(example, sameLength)).as("String shouldn't be truncated but was.")
            .isEqualTo(example);
  }

  @Test
  void testTruncateNegativeIsUnlimited() {
    assertThat(stringFormatter.truncate(example, -1))
            .as("Negative number as max chars provided, but did not default to unlimited.")
            .isEqualTo(example);
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2, 3})
  void testTruncateMaxCharsDoesNotFitEllipsis(int maxChars) {
    assumeThat(maxChars)
            .as("Allowed number of chars was not greater than length of default ellipsis.")
            .isLessThanOrEqualTo(DEFAULT_ELLIPSIS.length());

    assertThat(stringFormatter.truncate(example, maxChars))
            .as("String was not truncated to allowed max size of '" + maxChars + "'.")
            .hasSize(maxChars)
            .as("Even though allowed chars ('" + maxChars + "') is less than or equal to default ellipsis length ('" + DEFAULT_ELLIPSIS.length() +
                    "'), the string ends on it.")
            .doesNotEndWith(DEFAULT_ELLIPSIS)
            .startsWith(example.substring(0, maxChars));
  }

  @Test
  void testTruncateIsNeeded() {
    int maxChars = example.length() - DEFAULT_ELLIPSIS.length();
    assertThat(stringFormatter.truncate(example, maxChars))
            .as("Size after truncate is not correct.")
            .hasSameSizeAs("exam")
            .as("Does not have same beginning as original string.")
            .startsWith(example.substring(0, maxChars - DEFAULT_ELLIPSIS.length()))
            .as("Does not end with default ellipsis.")
            .endsWith(DEFAULT_ELLIPSIS);
  }

  @Test
  void testCustomEllipsis() {
    String customEllipsis = "__";
    String exampleWord = "exampleWord";
    int maxLength = exampleWord.length() - 4;
    assertThat(stringFormatter.truncate(exampleWord, maxLength, customEllipsis))
            .as("Result does not have the right length.")
            .hasSize(maxLength)
            .as("Does not have same beginning as original string.")
            .startsWith(exampleWord.substring(0, maxLength - customEllipsis.length()))
            .as("Does not end with custom ellipsis.")
            .endsWith(customEllipsis);
  }

  @Test
  void testHyphenateEmptyString() {
    String empty = "";
    assertThat(stringFormatter.hyphenate(empty, 1))
            .as("An empty string doesn't need to be hyphenated.")
            .isEmpty();
  }

  @Test
  void testHyphenateBlankString() {
    String blank = "  ";
    assertThat(stringFormatter.hyphenate(blank, 0)).as("Blank string is returned empty")
            .isEmpty();
  }

  @Test
  void testHyphenateNotNeeded() {
    assertThat(stringFormatter.hyphenate(example, sameLength))
            .as("String has max length and doesn't need to be hyphenated.")
            .isEqualTo(example);
  }

  @Test
  void testHyphenateStringShorter() {
    assertThat(stringFormatter.hyphenate(example, ++sameLength))
            .as("String is shorter than allowed and doesn't need to be modified")
            .isEqualTo(example);
  }

  @Test
  void testHyphenateOnlyWhiteSpacesMakeStringTooLong() {
    String modifiedExample = "   " + example + "   ";
    assertThat(stringFormatter.hyphenate(modifiedExample, sameLength))
            .as("String shouldn't be modified.")
            .isEqualTo(example);
  }

  @Test
  void testHyphenateExpressionIsTooLong() {
    String tooLongString = "jackhammer";
    int maxLength = 5;
    String expected = "jack- hammer";

    assertThat(stringFormatter.hyphenate(tooLongString, maxLength))
            .as("The string should be truncated.")
            .isEqualTo(expected);
  }

  @Test
  void testHyphenateExpressionTooLongButMultipleWords() {
    String s = "jack hammer";
    int maxLength = "hammer".length();

    assertThat(stringFormatter.hyphenate(s, maxLength))
            .as("String should not be modified.")
            .isEqualTo(s);
  }

  @Test
  void testHyphenateMultipleWordsOneTooLong() {
    String s = "jack's jackhammering";
    int maxLength = "jack's".length();
    String expected = "jack's jackh- ammering";

    assertThat(stringFormatter.hyphenate(s, maxLength)).isEqualTo(expected);
  }
}
