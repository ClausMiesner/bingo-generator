package de.miesner.claus.bingo.util.latex;

public class CellFormatter {


  public static String formatToFitCell(String expression, int maxRowsPerCell, int maxCharsPerRow) {
    if (expression.length() <= maxCharsPerRow) {
      return expression;
    }

    StringBuilder result = new StringBuilder();
    String[] words = expression.split(" ");
    for (String word : words) {
      if (word.isBlank()) {
        continue;
      }
      if (wordIsTooLongForRowOrCell(word, maxCharsPerRow, maxRowsPerCell, result)) {
        result.append(splitAndTruncate(word, maxCharsPerRow, maxRowsPerCell));
      } else {
        result.append(word);
      }
      result.append(" ");
    }
    return result.toString().strip();
  }

  private static boolean wordIsTooLongForRowOrCell(String word, int maxCharsPerRow, int maxRowsPerCell, StringBuilder sb) {
    int maxCharsPerCell = calculateMaxCharsPerCell(maxCharsPerRow, maxRowsPerCell);
    return wordIsTooLongForRow(word, maxCharsPerRow) || wordIsTooLongForCell(word, sb, maxCharsPerCell);
  }

  private static boolean wordIsTooLongForRow(String word, int maxCharsPerRow) {
    return word.strip().length() > maxCharsPerRow;
  }

  private static boolean wordIsTooLongForCell(String word, StringBuilder current, int maxCharsPerCell) {
    return word.strip().length() + current.length() > maxCharsPerCell;
  }

  private static String splitAndTruncate(String word, int maxCharsPerRow, int maxRowsPerCell) {
    if (hasToBeSplitMultipleTimes(word, maxCharsPerRow)) {
      int times = calculateSplitCount(word, maxCharsPerRow);
      StringBuilder result = new StringBuilder();
      splitMultipleTimes(word, result, maxCharsPerRow, times);

      int maxCharsPerCell = calculateMaxCharsPerCell(maxCharsPerRow, maxRowsPerCell);
      if (hasToBeTruncated(result, maxCharsPerCell)) {
        truncate(result, maxCharsPerCell);
      }
      return result.toString();
    }
    return splitOnce(word, maxCharsPerRow);
  }

  private static boolean hasToBeSplitMultipleTimes(String word, int maxChars) {
    return word.length() >= 2 * maxChars;
  }

  private static int calculateSplitCount(String word, int maxChars) {
    return word.length() / (maxChars - 1);
  }

  private static void splitMultipleTimes(String word, StringBuilder sb, int maxChars, int times) {
    for (int i = 0; i < times; i++) {
      int offset = i * (maxChars - 1);
      sb.append(word, offset, maxChars - 1 + offset);
      sb.append("- ");
    }
    sb.append(word, times * (maxChars - 1), word.length());
  }

  private static int calculateMaxCharsPerCell(int maxCharsPerRow, int maxRowsPerCell) {
    // There can be maxRowsPerCell more characters in the result since spaces are not count in latex.
    // they mark the new line.
    return maxCharsPerRow * maxRowsPerCell + (maxRowsPerCell - 1);
  }

  private static boolean hasToBeTruncated(StringBuilder sb, int maxCharsPerCell) {
    return sb.length() > maxCharsPerCell;
  }

  private static void truncate(StringBuilder sb, int maxCharsPerCell) {
    sb.replace(maxCharsPerCell - 3, sb.length(), "...");
  }

  private static String splitOnce(String word, int maxChars) {
    return word.substring(0, maxChars - 1) + "- " + word.substring(maxChars - 1);
  }
}
