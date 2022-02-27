package de.miesner.claus.bingo.table;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RowTest {

  Row row = new Row(List.of());

  @Test
  void testToStringWithoutWords() {
    String expected = "";
    assertThat(row.toString()).isEqualTo(expected);
  }

  @Test
  void testToStringOneEntry() {
    this.row = new Row(List.of("one"));
    String expected = "one";
    assertThat(row.toString()).isEqualTo(expected);
  }

  @Test
  void testToStringTwoEntries() {
    this.row = new Row(List.of("one", "two"));
    String expected = "one & two";
    assertThat(row.toString()).isEqualTo(expected);
  }

  @Test
  void testToStringMultipleEntries() {
    this.row = new Row(List.of("one", "two", "three", "four"));
    String expected = "one & two & three & four";
    assertThat(row.toString()).isEqualTo(expected);
  }

  @Test
  void testNullEqual() {
    assertThat(row.equals(null)).isFalse();
  }

  @Test
  void testNoEntriesEqual() {
    this.row = new Row(List.of());
    var equalRow = new Row(List.of());
    assertThat(row.equals(equalRow)).isTrue();
  }

  @Test
  void testOneHasEntryOtherNotEqual() {
    this.row = new Row(List.of("one"));
    var differentRow = new Row(List.of());
    assertThat(row.equals(differentRow)).isFalse();
  }

  @Test
  void testBothSameEntriesEqual() {
    this.row = new Row(List.of("one"));
    var equalRow = new Row(List.of("one"));
    assertThat(row.equals(equalRow)).isTrue();
  }
}
