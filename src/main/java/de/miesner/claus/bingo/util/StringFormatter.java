package de.miesner.claus.bingo.util;

public class StringFormatter {

  public static final String DEFAULT_ELLIPSIS = "...";

  public String truncate(String s, int maxChars) {
    return truncate(s, maxChars, DEFAULT_ELLIPSIS);
  }

  public String truncate(String s, int maxChars, String ending) {
    if (maxChars < 0 || s.length() <= maxChars) {
      return s;
    }
    if (maxChars <= ending.length()) {
      return s.substring(0, maxChars);
    }
    return s.substring(0, maxChars) + DEFAULT_ELLIPSIS;
  }
}
