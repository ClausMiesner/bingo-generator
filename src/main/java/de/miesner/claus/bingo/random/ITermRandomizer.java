package de.miesner.claus.bingo.random;

import java.util.List;

public interface ITermRandomizer {

  /**
   * <p>
   * This sets up the randomizer with the list of possible terms and prepares it
   * for returning terms on {@link #getNextTerm()} calls.
   * </p>
   *
   * @param possibleTerms List of possible terms
   */
  void setup(List<String> possibleTerms);

  /**
   * @return the next random term
   */
  String getNextTerm();

  /**
   * <p>
   * Resets the randomizer to a fresh state, which guarantees returning terms
   * as promised by configuration. Call before reusing.
   * </p>
   */
  void reset();
}
