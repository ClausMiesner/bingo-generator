package de.miesner.claus.bingo.table;

import java.util.List;

public class Row {

  List<String> inputs;

  public Row(List<String> inputs) {
    this.inputs = inputs;
  }

  public List<String> getInputs() {
    return inputs;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < inputs.size(); i++) {
      stringBuilder.append(inputs.get(i))
              .append(" & ");
    }
    return stringBuilder.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o.getClass() != getClass()) {
      return false;
    }
    Row that = (Row) o;

    return this.inputs.equals(that.inputs);
  }
}
