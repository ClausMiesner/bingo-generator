package de.miesner.claus.bingo.random;

import com.google.common.annotations.VisibleForTesting;
import de.miesner.claus.bingo.MisconfigurationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BasicTermRandomizer implements TermRandomizer {
  private List<String> terms;
  private final Map<String, Integer> usedTerms = new HashMap<>();
  private Random random = new Random();
  private int highestIndex;
  private int maxOccurrencesForTerm;

  @Override
  public void setup(List<String> possibleTerms, int maxOccurrencesForTerm) {
    if (possibleTerms == null || possibleTerms.isEmpty()) {
      throw new MisconfigurationException("No terms found. There need to be possible terms in order to set up the "
              + getClass().getSimpleName() + ".");
    }
    this.terms = possibleTerms;
    this.highestIndex = terms.size() - 1;
    setMaxOccurrencesForTerm(maxOccurrencesForTerm);
    reset();
  }

  private void setMaxOccurrencesForTerm(int maxOccurrencesForTerm) {
    if (maxOccurrencesForTerm <= 0) {
      this.maxOccurrencesForTerm = 1;
      return;
    }
    this.maxOccurrencesForTerm = maxOccurrencesForTerm;
  }

  @Override
  public String getNextTerm() {
    if (terms == null) {
      throw new IllegalStateException("No valid setup was run. No possible terms set up. " +
              "Make sure to run a setup before asking for the next term.");
    }

    int exclusiveUpperBound = highestIndex + 1;

    String nextTerm;
    do {
      int nextIndex = random.nextInt(exclusiveUpperBound);
      nextTerm = terms.get(nextIndex);
    } while (isNotAvailableForAnotherOccurrence(nextTerm));

    addNextTermToUsedOrIncreaseOccurrences(nextTerm);
    return nextTerm;
  }

  private boolean isNotAvailableForAnotherOccurrence(String nextTerm) {
    int currentOccurrences = usedTerms.getOrDefault(nextTerm, 0);
    return currentOccurrences >= maxOccurrencesForTerm;
  }

  private void addNextTermToUsedOrIncreaseOccurrences(String nextTerm) {
    if (usedTerms.containsKey(nextTerm)) {
      increaseOccurrences(nextTerm);
      return;
    }
    addTermToUsed(nextTerm);
  }

  private void increaseOccurrences(String nextTerm) {
    int currentOccurrences = usedTerms.get(nextTerm);
    usedTerms.replace(nextTerm, currentOccurrences + 1);
  }

  private void addTermToUsed(String nextTerm) {
    usedTerms.put(nextTerm, 1);
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
