package de.miesner.claus.bingo.util.latex;

public class CellFormatter {

  private final int charsPerRow;

  private final int rowsPerCell;


  public CellFormatter(int charsPerRow, int rowsPerCell) {
    this.charsPerRow = charsPerRow;
    this.rowsPerCell = rowsPerCell;
  }

  public String formatToFitCell(String cellValue) {
    return "word";
  }
}
