package de.miesner.claus.bingo.util.latex;

public class CellFormatter {


  public static String split(String expression, int maxChars) {
    if (expression.length() <= maxChars) {
      return expression;
    }
    
    StringBuilder result = new StringBuilder();
    String[] words = expression.split(" ");
    for (String word : words) {
      if (word.isBlank()) {
        continue;
      }
      if (wordIsTooLong(word, maxChars)) {
        result.append(splitWord(word, maxChars));
      } else {
        result.append(word);
      }
      result.append(" ");
    }
    return result.toString().strip();
  }

  private static boolean wordIsTooLong(String word, int maxChars) {
    return word.strip().length() > maxChars;
  }

  private static String splitWord(String word, int maxChars) {
    return word.substring(0, maxChars - 1) + "- " + word.substring(maxChars - 1);
  }
}
