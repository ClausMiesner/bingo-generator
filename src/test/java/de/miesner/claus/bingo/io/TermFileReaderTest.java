package de.miesner.claus.bingo.io;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TermFileReaderTest {
  TermFileReader termFileReader;

  @Test
  void testFileDoesNotExist() {
    termFileReader = new TermFileReader("fileDoesNotExist");
    assertThrows(FileNotFoundException.class, () -> termFileReader.read());
  }

  @Test
  void testOneTermInFile() throws FileNotFoundException {
    termFileReader = new TermFileReader("src/test/resources/oneTerm");
    String expected = "firstTerm";

    assertThat(termFileReader.read()).containsExactly(expected);
  }
}
