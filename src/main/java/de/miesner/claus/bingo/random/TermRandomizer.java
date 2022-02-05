package de.miesner.claus.bingo.random;

import java.util.List;
import java.util.Random;

public class TermRandomizer implements ITermRandomizer {
  private List<String> terms;
  private final Random random = new Random();
  private int highestIndex;

  @Override
  public void setup(List<String> possibleTerms) {
    this.terms = possibleTerms;
    this.highestIndex = terms.size() - 1;
  }

  @Override
  public String getNextTerm() {
    return null;
  }

  @Override
  public void reset() {

  }
}
