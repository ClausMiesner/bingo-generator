package de.miesner.claus.bingo.table;

import com.google.common.annotations.VisibleForTesting;
import de.miesner.claus.bingo.util.latex.LatexTextAlignment;

import java.util.ArrayList;
import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexExpression.LINE_BREAK;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_COLUMN_SEPARATOR;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_COLUMN_SPECIFICATION_OFFSET;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_END;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_ROW_BREAK;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_ROW_SEPARATOR;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_START;
import static de.miesner.claus.bingo.util.latex.LatexTextAlignment.CENTER_ALIGN;

public class Table {

  private final int numberOfRows;
  private final List<String> words;
  private final Row[] rows;
  private LatexTextAlignment textAlignment = CENTER_ALIGN;
  private boolean hasColumnSeparators = true;

  Table(int numberOfRows, List<String> words, LatexTextAlignment textAlignment, boolean hasColumnSeparators) {
    this(numberOfRows, words);
    this.textAlignment = textAlignment;
    this.hasColumnSeparators = hasColumnSeparators;
  }

  private Table(int numberOfRows, List<String> words) {
    this.numberOfRows = numberOfRows;
    this.words = words;
    if (!numberOfRowsMatchWords()) {
      throw new IllegalArgumentException("Number of words does not match number of rows." +
              " The number of words must be the square of the number of rows.");
    } else {
      this.rows = createRows();
    }
  }

  public static TableBuilder builder() {
    return new TableBuilder();
  }

  private boolean numberOfRowsMatchWords() {
    return this.numberOfRows == Math.sqrt(this.words.size());
  }

  @VisibleForTesting
  Row[] createRows() {
    Row[] result = new Row[numberOfRows];
    for (int i = 0; i < numberOfRows; i++) {
      List<String> wordsForRow = new ArrayList<>(numberOfRows);
      for (int j = numberOfRows * i; j < (i + 1) * numberOfRows; j++) {
        wordsForRow.add(this.words.get(j));
      }
      result[i] = new Row(wordsForRow);
    }
    return result;
  }

  @Override
  public String toString() {
    if (numberOfRows == 0) {
      return "";
    }
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder
            .append(TABLE_START)
            .insert(TABLE_COLUMN_SPECIFICATION_OFFSET, createColumnLayout());
    stringBuilder
            .append(LINE_BREAK)
            .append(TABLE_ROW_SEPARATOR)
            .append(LINE_BREAK);
    for (int i = 0; i < numberOfRows; i++) {
      stringBuilder
              .append(rows[i].toString())
              .append(LINE_BREAK)
              .append(TABLE_ROW_BREAK)
              .append(LINE_BREAK)
              .append(TABLE_ROW_SEPARATOR)
              .append(LINE_BREAK);
    }
    stringBuilder.append(TABLE_END);

    return stringBuilder.toString();
  }

  private String createColumnLayout() {
    StringBuilder stringBuilder = new StringBuilder();
    appendColumnSeparatorOrSpace(stringBuilder);
    for (int i = 0; i < numberOfRows; i++) {
      stringBuilder.append(textAlignment.getAlignment());
      appendColumnSeparatorOrSpace(stringBuilder);
    }
    return stringBuilder.toString();
  }

  private void appendColumnSeparatorOrSpace(StringBuilder stringBuilder) {
    if (hasColumnSeparators) {
      stringBuilder.append(TABLE_COLUMN_SEPARATOR);
    } else {
      stringBuilder.append(" ");
    }
  }

}
