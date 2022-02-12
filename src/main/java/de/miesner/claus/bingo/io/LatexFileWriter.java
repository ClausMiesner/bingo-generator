package de.miesner.claus.bingo.io;

import de.miesner.claus.bingo.table.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexExpression.LATEX_FILE_SUFFIX;
import static de.miesner.claus.bingo.util.latex.LatexExpression.LINE_BREAK;

public class LatexFileWriter {

  private PrintWriter printWriter;

  public void writeToFile(List<Table> tables, String fileName) {
    try {
      this.printWriter = new PrintWriter(new FileWriter(fileName + LATEX_FILE_SUFFIX));

      writeTexBeginning();

      for (Table table : tables) {
        printWriter.printf(table.toString());
      }

      writeTexEnding();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeTexEnding() {
    printWriter.printf("\\end{document}" + LINE_BREAK);
  }

  private void writeTexBeginning() {
    printWriter.printf("\\documentclass{article}" + LINE_BREAK);
    printWriter.printf("\\begin{document}" + LINE_BREAK);
  }
}
