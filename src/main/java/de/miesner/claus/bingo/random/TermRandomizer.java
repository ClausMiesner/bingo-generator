package de.miesner.claus.bingo.random;

import java.util.List;

public interface TermRandomizer {

  /**
   * <p>
   * This sets up the randomizer with the list of possible terms and their max occurrences
   * and prepares it for returning terms on {@link #getNextTerm()} calls.
   * </p>
   *
   * @param possibleTerms         List of possible terms
   * @param maxOccurrencesForTerm Max number a term may occur in the table
   */
  void setup(List<String> possibleTerms, int maxOccurrencesForTerm);

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
