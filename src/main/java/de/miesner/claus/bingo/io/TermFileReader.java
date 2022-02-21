package de.miesner.claus.bingo.io;

import de.miesner.claus.bingo.MisconfigurationException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TermFileReader {

  private final String path;

  public TermFileReader(String termFilePath) {
    this.path = termFilePath;
  }

  public List<String> read() throws FileNotFoundException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
    String term;
    List<String> result = new ArrayList<>();
    try {
      while ((term = bufferedReader.readLine()) != null) {
        if (!term.isBlank()) {
          result.add(term);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (isEmptyOrBlank(result)) {
      throw new MisconfigurationException("There weren't any terms found in '" + path + "'.");
    }
    return result;
  }

  private boolean isEmptyOrBlank(List<String> terms) {
    return terms.isEmpty() || containsOnlyBlankStrings(terms);
  }

  private boolean containsOnlyBlankStrings(List<String> terms) {
    return terms.stream().allMatch(String::isBlank);
  }
}
