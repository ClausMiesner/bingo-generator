package de.miesner.claus.bingo.table;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexExpression.CENTER_ALIGN;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_COLUMN_SPECIFICATION_OFFSET;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_END;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_ROW_BREAK;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_START;

public class Table {

  private final int numberOfRows;
  private final List<String> inputs;
  private final Row[] rows;
  private char textAlignment = CENTER_ALIGN;
  private boolean hasLineSeparators = true;

  public Table(int numberOfRows, List<String> inputs) {
    this.numberOfRows = numberOfRows;
    this.inputs = inputs;
    if (!numberOfRowsMatchInputs()) {
      throw new IllegalArgumentException("Number of inputs does not match number of rows." +
              " The number of inputs must be the square of the number of rows.");
    } else {
      this.rows = createRows();
    }
  }

  private boolean numberOfRowsMatchInputs() {
    return this.numberOfRows == Math.sqrt(this.inputs.size());
  }

  @VisibleForTesting
  Row[] createRows() {
    Row[] result = new Row[numberOfRows];
    for (int i = 0; i < numberOfRows; i++) {
      List<String> partialInputs = new ArrayList<>(numberOfRows);
      for (int j = numberOfRows * i; j < (i + 1) * numberOfRows; j++) {
        partialInputs.add(this.inputs.get(j));
      }
      result[i] = new Row(partialInputs);
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append(TABLE_START)
            .insert(TABLE_COLUMN_SPECIFICATION_OFFSET, createColumnLayout());
    for (int i = 0; i < numberOfRows; i++) {
      stringBuilder.append(rows[i].toString())
              .append(TABLE_ROW_BREAK);
    }
    int indexLastRowBreak = stringBuilder.lastIndexOf(TABLE_ROW_BREAK);
    stringBuilder.delete(indexLastRowBreak, indexLastRowBreak + 1);
    stringBuilder.append(TABLE_END);

    return stringBuilder.toString();
  }

  private String createColumnLayout() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(TABLE_ROW_BREAK);
    for (int i = 0; i < numberOfRows; i++) {
      stringBuilder.append(textAlignment);
      if (hasLineSeparators) {
        stringBuilder.append(TABLE_ROW_BREAK);
      }
    }
    if (!hasLineSeparators) {
      stringBuilder.append(TABLE_ROW_BREAK);
    }
    return stringBuilder.toString();
  }

  public void setHasLineSeparators(boolean hasLineSeparators) {
    this.hasLineSeparators = hasLineSeparators;
  }

  public void setTextAlignment(char textAlignment) {
    this.textAlignment = textAlignment;
  }
}
