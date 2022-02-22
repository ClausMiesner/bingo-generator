package de.miesner.claus.bingo.io;

import de.miesner.claus.bingo.MisconfigurationException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static de.miesner.claus.bingo.io.TermFileReader.read;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TermFileReaderTest {
  final String pathToResources = "src/test/resources/";

  @Test
  void testFileDoesNotExist() {
    assertThrows(FileNotFoundException.class, () -> read("fileDoesNotExist"));
  }

  @Test
  void testOneTermInFile() throws FileNotFoundException {
    String expected = "firstTerm";

    assertThat(read(pathToResources + "oneTerm")).containsExactly(expected);
  }

  @Test
  void testMultipleTerms() throws FileNotFoundException {
    List<String> expected = List.of("firstTerm", "secondTerm", "thirdTerm");

    assertThat(read(pathToResources + "multipleTerms")).containsExactlyElementsOf(expected);
  }

  @Test
  void testNoTermsInFile() {
    assertThrows(MisconfigurationException.class, () -> read(pathToResources + "noTerms"));
  }

  @Test
  void testEmptyLinesInFile() {
    assertThrows(MisconfigurationException.class, () -> read(pathToResources + "emptyLines"));
  }

  @Test
  void testOnlyBlankTerms() {
    assertThrows(MisconfigurationException.class, () -> read(pathToResources + "blankTerms"));
  }

  @Test
  void testBlankTermsBetweenAreIgnored() throws FileNotFoundException {
    List<String> expected = List.of("one", "two", "three", "four");

    assertThat(read(pathToResources + "termsAndBlanks")).containsExactlyElementsOf(expected);
  }

  @Test
  void testRemoveTrailingWhitespaces() throws FileNotFoundException {
    List<String> expected = List.of("one", "two", "three", "horse power");

    assertThat(read(pathToResources + "termsWithTrailingWhitespaces")).containsExactlyElementsOf(expected);
  }
}
