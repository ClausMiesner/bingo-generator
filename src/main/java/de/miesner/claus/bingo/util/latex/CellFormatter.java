package de.miesner.claus.bingo.util.latex;

import static de.miesner.claus.bingo.util.latex.Expression.MAX_CHARS_PER_ROW;

public class CellFormatter {


  public static String split(String word, int maxChars) {
    if (word.length() <= maxChars) {
      return word;
    }
    return word.substring(0, MAX_CHARS_PER_ROW - 3) + "- " + word.substring(MAX_CHARS_PER_ROW - 3);
  }
}
