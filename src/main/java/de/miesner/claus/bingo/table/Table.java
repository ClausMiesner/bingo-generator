package de.miesner.claus.bingo.table;

import java.util.ArrayList;
import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexExpression.CENTER_ALIGN;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_ROW_BREAK;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_COLUMN_SPECIFICATION_OFFSET;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_END;
import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_START;

public class Table {

  private int numberOfRows;
  private List<String> inputs;
  private Row[] rows;
  private char textAlignment = CENTER_ALIGN;
  private boolean hasLineSeparators = true;

  public Table(int numberOfRows, List<String> inputs) {
    this.numberOfRows = numberOfRows;
    this.inputs = inputs;
    if (numberOfRowsMatchInputs()) {
      this.rows = createRows();
    }
  }

  private Row[] createRows() {
    Row[] result = new Row[numberOfRows];
    for (int i = 0; i < numberOfRows; i++) {
      List<String> partialInputs = new ArrayList<>(numberOfRows);
      for (int j = 0; j < numberOfRows; j++) {
        partialInputs.add(this.inputs.get(j + i));
      }
      result[i] = new Row(partialInputs);
    }
    return result;
  }

  private boolean numberOfRowsMatchInputs() {
    return this.numberOfRows == this.inputs.size();
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
    stringBuilder.delete(indexLastRowBreak, indexLastRowBreak+1);
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
