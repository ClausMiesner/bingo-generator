package de.miesner.claus.bingo.util.latex;

public enum LatexTextAlignment {

  LEFT_ALIGN('l'),
  CENTER_ALIGN('c'),
  RIGHT_ALIGN('r');

  private final char alignment;

  LatexTextAlignment(char alignmentOption) {
    this.alignment = alignmentOption;
  }

  public char getAlignment() {
    return alignment;
  }
}
