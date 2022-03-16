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
    assertThat(stringFormatter.truncate(example, example.length() - DEFAULT_ELLIPSIS.length()))
            .as("Although only the last chars of the string should have been substituted, the size of the result string is different.")
            .hasSameSizeAs(example)
            .as("Does not have same beginning as original string.")
            .startsWith(example.substring(0, example.length() - DEFAULT_ELLIPSIS.length()))
            .as("Does not end with default ellipsis.")
            .endsWith(DEFAULT_ELLIPSIS);
  }

  @Test
  void testCustomEllipsis() {
    String customEllipsis = "__";
    assertThat(stringFormatter.truncate(example, example.length() - customEllipsis.length(), customEllipsis))
            .as("Result does does not have the right length.")
            .hasSameSizeAs(example)
            .as("Does not have same beginning as original string.")
            .startsWith(example.substring(0, example.length() - customEllipsis.length()))
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
}
