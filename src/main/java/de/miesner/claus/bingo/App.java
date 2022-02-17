package de.miesner.claus.bingo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.miesner.claus.bingo.BingoTicketGenerator.generateTickets;
import static de.miesner.claus.bingo.io.LatexFileWriter.writeToFile;

public class App {

  public static void main(String[] args) {
    int numberOfTickets = Integer.parseInt(args[0]);
    int numberOfRowsPerTicket = Integer.parseInt(args[1]);
    String pathToFile = args[2];
    List<String> terms = new ArrayList<>(Arrays.asList(args).subList(3, args.length));

    writeToFile(generateTickets(numberOfTickets, terms, numberOfRowsPerTicket), pathToFile);
  }
}
