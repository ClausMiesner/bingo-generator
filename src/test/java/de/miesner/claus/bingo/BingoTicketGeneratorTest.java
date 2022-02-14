package de.miesner.claus.bingo;


import de.miesner.claus.bingo.random.TermRandomizer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class BingoTicketGeneratorTest {

  private final List<String> exampleTerms = List.of("one", "two", "three", "four", "five");
  private final TermRandomizer mockRandomizer = mock(TermRandomizer.class);

  @Test
  void testNegativeBingoTickets() {
    when(mockRandomizer.getNextTerm()).thenReturn("one", "three", "four", "five");

    assertThat(BingoTicketGenerator.generateTickets(-1, exampleTerms, 2))
            .as("One Table was created even though '-1' were requested.").hasSize(1);
  }

  @Test
  void testNoBingoTicket() {
    when(mockRandomizer.getNextTerm()).thenReturn("one", "three", "four", "five");

    assertThat(BingoTicketGenerator.generateTickets(0, exampleTerms, 2))
            .as("One Table was created even though '0' were requested.").hasSize(1);
  }

  @Test
  void testSingleBingoTicket() {
    when(mockRandomizer.getNextTerm()).thenReturn("one", "three", "four", "five");

    assertThat(BingoTicketGenerator.generateTickets(1, exampleTerms, 2))
            .as("One Table was created.").hasSize(1);
  }
}
