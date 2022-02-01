package de.miesner.claus.bingo.table;

import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_ROW_ENTRY_CONNECTOR;

public class Row {

  private final List<String> inputs;

  public Row(List<String> inputs) {
    this.inputs = inputs;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (String input : inputs) {
      stringBuilder.append(input)
              .append(TABLE_ROW_ENTRY_CONNECTOR);
    }
    int lastIndex = stringBuilder.lastIndexOf(TABLE_ROW_ENTRY_CONNECTOR);
    if (lastIndex > -1) {
      stringBuilder.delete(lastIndex, lastIndex + TABLE_ROW_ENTRY_CONNECTOR.length());
    }
    return stringBuilder.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || o.getClass() != getClass()) {
      return false;
    }
    Row that = (Row) o;

    return this.inputs.equals(that.inputs);
  }
}
