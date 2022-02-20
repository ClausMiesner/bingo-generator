package de.miesner.claus.bingo;

import de.miesner.claus.bingo.random.BasicTermRandomizer;
import de.miesner.claus.bingo.random.TermRandomizer;
import de.miesner.claus.bingo.table.Table;
import de.miesner.claus.bingo.table.TableBuilder;

import java.util.ArrayList;
import java.util.List;

public class BingoTicketGenerator {

  private static final int DEFAULT_NUMBER_OF_ROWS = 5;
  private static final int DEFAULT_MAX_OCCURRENCES = 1;
  private static final int DEFAULT_NUMBER_OF_TICKETS = 1;

  public static List<Table> generateTickets(int numberOfTickets, List<String> terms) {
    return generateTickets(numberOfTickets, terms, DEFAULT_NUMBER_OF_ROWS);
  }

  public static List<Table> generateTickets(int numberOfTickets, List<String> terms, int numberOfRowsPerTicket) {
    return generateTickets(numberOfTickets,
            terms, numberOfRowsPerTicket, DEFAULT_MAX_OCCURRENCES,
            new BasicTermRandomizer(),
            true);
  }

  public static List<Table> generateTickets(int numberOfTickets,
                                            List<String> terms, int numberOfRowsPerTicket,
                                            int maxNumberOfOccurrences,
                                            TermRandomizer termRandomizer,
                                            boolean hasColumnSeparator) {
    if (numberOfTickets <= 0) {
      numberOfTickets = DEFAULT_NUMBER_OF_TICKETS;
    }
    List<Table> tables = new ArrayList<>(numberOfTickets);

    for (int i = 0; i < numberOfTickets; i++) {
      tables.add(generateSingleBingoTicket(terms, numberOfRowsPerTicket, maxNumberOfOccurrences, termRandomizer, hasColumnSeparator));
    }
    return tables;
  }

  private static Table generateSingleBingoTicket(List<String> terms,
                                                 int numberOfRowsPerTicket,
                                                 int maxNumberOfOccurrences,
                                                 TermRandomizer termRandomizer,
                                                 boolean hasColumnSeparator) {
    TableBuilder tableBuilder = Table.builder()
            .withPossibleBingoTerms(terms)
            .withMaxOccurrencesForTerm(maxNumberOfOccurrences)
            .withNumberOfRows(numberOfRowsPerTicket)
            .withTermRandomizer(termRandomizer);
    if (hasColumnSeparator) {
      return tableBuilder.withColumnSeparator().build();
    }
    return tableBuilder.build();
  }
}
