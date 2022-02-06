package de.miesner.claus.bingo.random;


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
    termRandomizer.setup(List.of("one"));

    assertThat(termRandomizer.getNextTerm()).isEqualTo("one");
  }

  @Test
  void testNextTermDoubleValue() {
    when(randomMock.nextInt(2)).thenReturn(0, 0, 1);
    termRandomizer.setup(List.of("one", "two"));

    assertThat(termRandomizer.getNextTerm()).as("No used terms = no restrictions.").isEqualTo("one");
    assertThat(termRandomizer.getNextTerm()).as("One term already used.").isEqualTo("two");
  }

  @Test
  void testSetupWithNoTerms() {
    assertThrows(IllegalArgumentException.class, () -> termRandomizer.setup(List.of()));
  }

  @Test
  void testSetupWithNullAsTerms() {
    assertThrows(IllegalArgumentException.class, () -> termRandomizer.setup(null));
  }

  @Test
  void testResetAllowsSameValues() {
    when(randomMock.nextInt(1)).thenReturn(0);
    termRandomizer.setup(List.of("one"));

    assertThat(termRandomizer.getNextTerm()).as("First call to nextTerm").isEqualTo("one");
    termRandomizer.reset();
    assertThat(termRandomizer.getNextTerm()).as("Call after reset").isEqualTo("one");
  }

}
