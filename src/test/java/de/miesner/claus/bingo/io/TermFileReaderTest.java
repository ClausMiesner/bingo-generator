package de.miesner.claus.bingo.io;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TermFileReaderTest {
  TermFileReader termFileReader;
  String pathToResources = "src/test/resources/";

  @Test
  void testFileDoesNotExist() {
    termFileReader = new TermFileReader("fileDoesNotExist");
    assertThrows(FileNotFoundException.class, () -> termFileReader.read());
  }

  @Test
  void testOneTermInFile() throws FileNotFoundException {
    termFileReader = new TermFileReader(pathToResources + "oneTerm");
    String expected = "firstTerm";

    assertThat(termFileReader.read()).containsExactly(expected);
  }

  @Test
  void testMultipleTerms() throws FileNotFoundException {
    termFileReader = new TermFileReader(pathToResources + "multipleTerms");
    List<String> expected = List.of("firstTerm", "secondTerm", "thirdTerm");

    assertThat(termFileReader.read()).containsExactlyElementsOf(expected);
  }
}
