package de.miesner.claus.bingo.util.latex;

import de.miesner.claus.bingo.util.StringFormatter;

public class CellFormatter {

  private final int charsPerRow;

  private final int rowsPerCell;

  private final StringFormatter stringFormatter;


  public CellFormatter(int charsPerRow, int rowsPerCell) {
    this.charsPerRow = charsPerRow;
    this.rowsPerCell = rowsPerCell;
    this.stringFormatter = new StringFormatter();
  }

  public String formatToFitCell(String cellValue) {
    if (cellValue.isBlank()) {
      return "";
    }
    return stringFormatter.hyphenate(cellValue, charsPerRow);
  }
}
