package de.miesner.claus.bingo.table;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TableTest {
  Table table;
  List<String> sampleInputs = List.of(
          "one", "two", "three",
          "four", "five", "six",
          "seven", "eight", "nine"
  );

  @BeforeEach
  void setup() {
    this.table = new Table((int) Math.sqrt(sampleInputs.size()), sampleInputs);
  }

  @Test
  void testZeroInputsCreateRow() {
    this.table = new Table(0, List.of());
    assertThat(table.createRows(), is(new Row[0]));
  }

  @Test
  void testCorrectInputs() {
    Row[] expected = {
            new Row(List.of("one", "two", "three")),
            new Row(List.of("four", "five", "six")),
            new Row(List.of("seven", "eight", "nine"))
    };

    assertThat(table.createRows(), is(expected));
  }

  @Test
  void testTooManyInputs() {
    assertThrows(IllegalArgumentException.class, () -> new Table(1, List.of("one", "two")));
  }

  @Test
  void testTooManyRowsWanted() {
    assertThrows(IllegalArgumentException.class, () -> new Table(1, List.of()));
  }

}
