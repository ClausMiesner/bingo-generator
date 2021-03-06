package de.miesner.claus.bingo;

import de.miesner.claus.bingo.io.TermFileReader;

import java.io.FileNotFoundException;
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
    List<String> terms = loadTermsFromCommandLineOrFile(args);

    try {
      writeToFile(generateTickets(numberOfTickets, terms, numberOfRowsPerTicket), pathToFile);
    } catch (MisconfigurationException e) {
      System.out.println("There was an unexpected behavior. The following is in misconfiguration: " + e.getMessage());
    } catch (IllegalStateException e) {
      System.out.println("There was an unexpected behavior. The application is in a corrupted state. " + e.getMessage());
    }
  }

  private static List<String> loadTermsFromCommandLineOrFile(String[] args) {
    if (isReadFromFileMode(args)) {
      return readTermsFromFile(args);
    } else {
      return addTermsFromCommandLine(args);
    }
  }

  private static boolean isReadFromFileMode(String[] args) {
    return args.length == 3;
  }

  private static List<String> readTermsFromFile(String[] args) {
    try {
      return readTermsFromFile(args[2]);
    } catch (FileNotFoundException e) {
      System.out.println("There was an unexpected behavior. The term file wasn't found at '" + args[2] + "'.");
    }
    return List.of();
  }

  private static List<String> readTermsFromFile(String pathToFile) throws FileNotFoundException {
    return TermFileReader.read(pathToFile);
  }

  private static List<String> addTermsFromCommandLine(String[] args) {
    return new ArrayList<>(Arrays.asList(args).subList(3, args.length));
  }
}
