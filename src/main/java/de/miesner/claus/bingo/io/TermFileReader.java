package de.miesner.claus.bingo.io;

import de.miesner.claus.bingo.MisconfigurationException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TermFileReader {

  public static List<String> read(String path) throws FileNotFoundException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
    List<String> result = new ArrayList<>();

    String term;
    try {
      while ((term = bufferedReader.readLine()) != null) {
        if (isPresent(term)) {
          result.add(term.strip());
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

  private static boolean isPresent(String s) {
    return !s.isBlank();
  }

  private static boolean isEmptyOrBlank(List<String> terms) {
    return terms.isEmpty() || containsOnlyBlankStrings(terms);
  }

  private static boolean containsOnlyBlankStrings(List<String> terms) {
    return terms.stream().allMatch(String::isBlank);
  }
}
