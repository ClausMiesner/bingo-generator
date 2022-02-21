package de.miesner.claus.bingo.io;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TermFileReaderTest {
  TermFileReader termFileReader;

  @Test
  void testFileDoesNotExist() {
    termFileReader = new TermFileReader("fileDoesNotExist");
    assertThrows(FileNotFoundException.class, () -> termFileReader.read());
  }


}
