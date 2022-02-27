package de.miesner.claus.bingo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.miesner.claus.bingo.BingoTicketGenerator.generateTickets;
import static de.miesner.claus.bingo.io.LatexFileWriter.writeToFile;

class SystemTest {

  final int numberOfTickets = 5;
  final String pathToFile = "/Users/Claus/Desktop";
  final List<String> terms = List.of(
          "Hund gelb zwei", "Katze blau eins", "Maus rot drei", "Elefant", "Bieber",
          "Waschbär", "Nashorn", "Igel", "Schwein", "Kuh",
          "Huhn", "Ratte", "Lama", "Pferd", "Reh",
          "Dachs", "Ente", "Fuchs", "Wildschwein", "Mader",
          "Eule", "Specht", "Uhu", "Schnabeltier", "Otter",
          "Schnecke", "Spinne", "Ameise", "Frosch", "Tausendfüssler",
          "zwölfZeichen", "thisIsAVeryLongWordToCheckHowManyCharsFitInOneCellAndWeNeedToGoEvenLonger");

  @Test
  void createBingoFile() {
    writeToFile(generateTickets(numberOfTickets, terms), pathToFile);
  }
}
