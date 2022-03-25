package de.miesner.claus.bingo.util;

public class StringFormatter {

  public static final String DEFAULT_ELLIPSIS = "...";

  public String truncate(String s, int maxChars) {
    return truncate(s, maxChars, DEFAULT_ELLIPSIS);
  }

  public String truncate(String s, int maxChars, String ellipsis) {
    if (maxChars < 0 || s.length() <= maxChars) {
      return s;
    }
    if (maxChars <= ellipsis.length()) {
      return s.substring(0, maxChars);
    }
    return s.substring(0, maxChars) + ellipsis;
  }

  public String hyphenate(String expression, int maxLengthWord) {
    if (expression.isEmpty()) {
      return "";
    }
    expression = expression.strip();
    if (expression.length() <= maxLengthWord) {
      return expression;
    }
    return hyphenateSpaceAware(expression, maxLengthWord);
  }

  private String hyphenateSpaceAware(String expression, int maxLengthWord) {
    String[] words = expression.split(" ");
    StringBuilder result = new StringBuilder();
    for (String word : words) {
      if (word.length() > maxLengthWord) {
        result.append(addHyphen(word, maxLengthWord));
      } else {
        result.append(word);
      }
      result.append(" ");
    }
    return result.toString().strip();
  }

  private String addHyphen(String expression, int nThChar) {
    return expression.substring(0, nThChar - 1) + "- " + expression.substring(nThChar - 1);
  }
}
