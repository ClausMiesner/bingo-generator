package de.miesner.claus.bingo.util.latex;

import org.junit.jupiter.api.BeforeEach;

class CellFormatterTest {

  CellFormatter cellFormatter;

  @BeforeEach
  void setUp() {
    this.cellFormatter = new CellFormatter(0, 0);
  }
}
