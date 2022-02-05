package de.miesner.claus.bingo.table;

import de.miesner.claus.bingo.random.ITermRandomizer;

import javax.annotation.Nullable;
import java.util.List;

public class PassThroughTermRandomizer implements ITermRandomizer {

  private List<String> terms;
  private int maxIndex;
  private int nextIndex = -1;

  public PassThroughTermRandomizer(List<String> possibleTerms) {
    this.terms = possibleTerms;
    setMaxIndex();
  }

  private void setMaxIndex() {
    this.maxIndex = terms.size() - 1;
  }

  @Override
  public void setup(List<String> possibleTerms) {
    this.terms = possibleTerms;
    setMaxIndex();
  }

  @Override
  @Nullable
  public String getNextTerm() {
    if (nextIndex++ > maxIndex) {
      return null;
    }
    return terms.get(nextIndex);
  }

  @Override
  public void reset() {
  }
}
