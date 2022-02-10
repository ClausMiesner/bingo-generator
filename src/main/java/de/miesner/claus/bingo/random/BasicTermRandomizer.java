package de.miesner.claus.bingo.random;

import com.google.common.annotations.VisibleForTesting;

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
      throw new IllegalArgumentException("There need to be possible terms in order to setup the "
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
      throw new IllegalStateException("No valid setup was run. No possibleTerms setup.");
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
