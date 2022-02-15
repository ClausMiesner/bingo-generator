package de.miesner.claus.bingo.io;

import de.miesner.claus.bingo.table.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexExpression.DOCUMENT_END;
import static de.miesner.claus.bingo.util.latex.LatexExpression.DOCUMENT_START;
import static de.miesner.claus.bingo.util.latex.LatexExpression.FILE_SUFFIX;

public class LatexFileWriter {

  private static PrintWriter printWriter;
  private static final String DEFAULT_FILE_NAME = "bingo";
  private static final String FILE_SEPARATOR = System.getProperty("file.separator");

  public static void writeToFile(List<Table> tables, String pathToFile) {
    try {
      printWriter = new PrintWriter(new FileWriter(pathToFile + FILE_SEPARATOR + DEFAULT_FILE_NAME + FILE_SUFFIX));

      writeTexBeginning();

      for (Table table : tables) {
        printWriter.printf(table.toString());
      }

      writeTexEnding();
      printWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void writeTexBeginning() {
    printWriter.printf(DOCUMENT_START);
  }

  private static void writeTexEnding() {
    printWriter.printf(DOCUMENT_END);
  }
}
