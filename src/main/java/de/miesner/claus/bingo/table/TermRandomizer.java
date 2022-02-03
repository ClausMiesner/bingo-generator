package de.miesner.claus.bingo.table;

import java.util.List;
import java.util.Random;

public class TermRandomizer {
  private final List<String> terms;
  private final Random random = new Random();
  private final int highestIndex;

  public TermRandomizer(List<String> terms) {
    this.terms = terms;
    this.highestIndex = terms.size() - 1;
  }

  /**
   * @return the next randomized term
   */
  public String getNextTerm() {
    return null;
  }

  /**
   * <p>
   * Resets all statistics of already returned terms.
   * </p>
   */
  public void reset() {

  }
}
