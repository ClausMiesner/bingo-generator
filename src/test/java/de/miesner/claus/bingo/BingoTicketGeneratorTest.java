package de.miesner.claus.bingo;


import de.miesner.claus.bingo.random.TermRandomizer;
import de.miesner.claus.bingo.util.latex.LatexTextAlignment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class BingoTicketGeneratorTest {

  private final List<String> exampleTerms = List.of("one", "two", "three", "four", "five");
  private final TermRandomizer mockRandomizer = mock(TermRandomizer.class);
  private BingoTicketGenerator ticketGenerator;

  @Test
  void testNegativeBingoTickets() {
    this.ticketGenerator = new BingoTicketGenerator(exampleTerms, 1, -1, 2, mockRandomizer, true, LatexTextAlignment.CENTER_ALIGN);
    when(mockRandomizer.getNextTerm()).thenReturn("one", "three", "four", "five");

    assertThat(ticketGenerator.generateBingoTickets())
            .as("One Table was created even though '-1' were requested.").hasSize(1);
  }

  @Test
  void testNoBingoTicket() {
    this.ticketGenerator = new BingoTicketGenerator(exampleTerms, 1, 0, 2, mockRandomizer, true, LatexTextAlignment.CENTER_ALIGN);
    when(mockRandomizer.getNextTerm()).thenReturn("one", "three", "four", "five");

    assertThat(ticketGenerator.generateBingoTickets())
            .as("One Table was created even though '0' were requested.").hasSize(1);
  }

  @Test
  void testSingleBingoTicket() {
    this.ticketGenerator = new BingoTicketGenerator(exampleTerms, 1, 1, 2, mockRandomizer, true, LatexTextAlignment.CENTER_ALIGN);
    when(mockRandomizer.getNextTerm()).thenReturn("one", "three", "four", "five");

    assertThat(ticketGenerator.generateBingoTickets())
            .as("One Table was created.").hasSize(1);
  }
}
