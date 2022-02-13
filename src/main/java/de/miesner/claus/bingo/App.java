package de.miesner.claus.bingo;

import java.util.List;

import static de.miesner.claus.bingo.BingoTicketGenerator.generateTickets;
import static de.miesner.claus.bingo.io.LatexFileWriter.writeToFile;

public class App {

  public static void main(String[] args) {
    List<String> terms = List.of("one", "two", "three", "four", "five");
    writeToFile(generateTickets(4, terms, 3), "bingo");
  }
}
