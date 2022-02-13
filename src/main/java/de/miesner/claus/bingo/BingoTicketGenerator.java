package de.miesner.claus.bingo;

import de.miesner.claus.bingo.random.TermRandomizer;
import de.miesner.claus.bingo.table.Table;
import de.miesner.claus.bingo.table.TableBuilder;
import de.miesner.claus.bingo.util.latex.LatexTextAlignment;

import java.util.ArrayList;
import java.util.List;

public class BingoTicketGenerator {

  private final List<String> terms;
  private final int maxNumberOfOccurrences;
  private final int numberOfTickets;
  private final int numberOfRowsPerTicket;
  private final TermRandomizer termRandomizer;
  private final boolean hasColumnSeparator;
  private final LatexTextAlignment textAlignment;


  public BingoTicketGenerator(List<String> terms,
                              int maxNumberOfOccurrences,
                              int numberOfTickets,
                              int numberOfRowsPerTicket,
                              TermRandomizer termRandomizer,
                              boolean hasColumnSeparator,
                              LatexTextAlignment textAlignment) {
    this.terms = terms;
    this.maxNumberOfOccurrences = maxNumberOfOccurrences;

    if (numberOfTickets <= 0) {
      numberOfTickets = 1;
    }
    this.numberOfTickets = numberOfTickets;
    this.numberOfRowsPerTicket = numberOfRowsPerTicket;
    this.termRandomizer = termRandomizer;
    this.hasColumnSeparator = hasColumnSeparator;
    this.textAlignment = textAlignment;
  }

  public List<Table> generateBingoTickets() {
    List<Table> tables = new ArrayList<>(numberOfTickets);

    for (int i = 0; i < numberOfTickets; i++) {
      tables.add(generateSingleBingoTicket());
    }
    return tables;
  }

  private Table generateSingleBingoTicket() {
    TableBuilder tableBuilder = Table.builder()
            .withPossibleBingoTerms(terms)
            .withMaxOccurrencesForTerm(maxNumberOfOccurrences)
            .withNumberOfRows(numberOfRowsPerTicket)
            .withTermRandomizer(termRandomizer)
            .withTextAlignment(textAlignment);
    if (hasColumnSeparator) {
      return tableBuilder.withColumnSeparator().build();
    }
    return tableBuilder.build();
  }
}
