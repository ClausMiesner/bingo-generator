package de.miesner.claus.bingo.random;


import de.miesner.claus.bingo.MisconfigurationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BasicTermRandomizerTest {

  private BasicTermRandomizer termRandomizer;
  private Random randomMock;
  private final int defaultMaxOccurrences = 1;

  @BeforeEach
  void setup() {
    this.termRandomizer = new BasicTermRandomizer();
    this.randomMock = mock(Random.class);
    this.termRandomizer.setRandomForTesting(randomMock);
  }

  @Test
  void testNextTermNoSetup() {
    assertThrows(IllegalStateException.class, () -> termRandomizer.getNextTerm());
  }

  @Test
  void testNextTermOnlyOneTerm() {
    when(randomMock.nextInt(1)).thenReturn(0);
    termRandomizer.setup(List.of("one"), defaultMaxOccurrences);

    assertThat(termRandomizer.getNextTerm()).isEqualTo("one");
  }

  @Test
  void testNextTermDoubleValue() {
    when(randomMock.nextInt(2)).thenReturn(0, 0, 1);
    termRandomizer.setup(List.of("one", "two"), defaultMaxOccurrences);

    assertThat(termRandomizer.getNextTerm()).as("No used terms = no restrictions.").isEqualTo("one");
    assertThat(termRandomizer.getNextTerm()).as("One term already used.").isEqualTo("two");
  }

  @Test
  void testSetupWithNoTerms() {
    assertThrows(MisconfigurationException.class, () -> termRandomizer.setup(List.of(), defaultMaxOccurrences));
  }

  @Test
  void testSetupWithNullAsTerms() {
    assertThrows(MisconfigurationException.class, () -> termRandomizer.setup(null, defaultMaxOccurrences));
  }

  @Test
  void testResetAllowsSameValues() {
    when(randomMock.nextInt(1)).thenReturn(0);
    termRandomizer.setup(List.of("one"), defaultMaxOccurrences);

    assertThat(termRandomizer.getNextTerm()).as("First call to nextTerm").isEqualTo("one");
    termRandomizer.reset();
    assertThat(termRandomizer.getNextTerm()).as("Call after reset").isEqualTo("one");
  }

  @Test
  void testNextTermDoubleValueAllowed() {
    when(randomMock.nextInt(2)).thenReturn(0, 0, 0, 1);
    termRandomizer.setup(List.of("one", "two"), 2);

    assertThat(termRandomizer.getNextTerm()).as("First occurrence of term allowed.").isEqualTo("one");
    assertThat(termRandomizer.getNextTerm()).as("Second occurrence of term allowed.").isEqualTo("one");
    assertThat(termRandomizer.getNextTerm()).as("Third occurrence of term not allowed.").isEqualTo("two");
  }

}
