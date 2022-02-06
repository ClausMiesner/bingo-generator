package de.miesner.claus.bingo.random;

import com.google.common.annotations.VisibleForTesting;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BasicTermRandomizer implements ITermRandomizer {
  private List<String> terms;
  private final Set<String> usedTerms = new HashSet<>();
  private Random random = new Random();
  private int highestIndex;

  @Override
  public void setup(List<String> possibleTerms) {
    if (possibleTerms == null || possibleTerms.isEmpty()) {
      throw new IllegalArgumentException("There need to be possible terms in order to setup the "
              + getClass().getSimpleName() + ".");
    }
    this.terms = possibleTerms;
    this.highestIndex = terms.size() - 1;
    reset();
  }

  @Override
  public String getNextTerm() {
    if (terms == null) {
      throw new IllegalStateException("No valid setup was run. No possibleTerms setup.");
    }
    int exclusiveUpperBound = highestIndex + 1;
    int nextIndex = random.nextInt(exclusiveUpperBound);
    String nextTerm = terms.get(nextIndex);
    while (usedTerms.contains(nextTerm)) {
      nextIndex = random.nextInt(exclusiveUpperBound);
      nextTerm = terms.get(nextIndex);
    }
    usedTerms.add(nextTerm);
    return nextTerm;
  }

  @Override
  public void reset() {
    usedTerms.clear();
  }

  @VisibleForTesting
  void setRandomForTesting(Random random) {
    this.random = random;
  }
}
