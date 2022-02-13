package de.miesner.claus.bingo.util.latex;

public class LatexExpression {

  /**
   * <p>
   * Separator to use in the column design specification of a tabular.
   * Results in printed horizontal divider lines in a table.
   * </p>
   */
  public static final char TABLE_COLUMN_SEPARATOR = '|';
  /**
   * <p>
   * Marks the end of a row in a table.
   * </p>
   */
  public static final String TABLE_ROW_BREAK = "\\\\";

  /**
   * <p>
   * Adds a line break in source code.
   * </p>
   */
  public static final String LINE_BREAK = "\n";

  /**
   * <p>
   * Used in between entries of a table row.
   * </p>
   */
  public static final String TABLE_ROW_ENTRY_CONNECTOR = " & ";
  /**
   * <p>
   * Prints a horizontal line in between rows.
   * </p>
   */
  public static final String TABLE_ROW_SEPARATOR = "\\hline";

  /**
   * <p>
   * Provides expression to create the start of a table.
   * After the {@link de.miesner.claus.bingo.util.latex.LatexExpression#TABLE_COLUMN_SPECIFICATION_OFFSET}
   * insert the style for the columns.
   * </p>
   */
  public static final String TABLE_START = LINE_BREAK + "\\begin{table}\n" +
          "\\begin{tabular}{}";
  /**
   * <p>
   * Offset after which the column design specification has to be inserted into
   * the {@link de.miesner.claus.bingo.util.latex.LatexExpression#TABLE_START}.
   * </p>
   */
  public static final int TABLE_COLUMN_SPECIFICATION_OFFSET = 31;

  /**
   * <p>
   * Expressions to close a table.
   * </p>
   */
  public static final String TABLE_END = "\\end{tabular}\n" +
          "\\end{table}" + LINE_BREAK;

  /**
   * <p>
   * Ending for LaTex file.
   * </p>
   */
  public static final String FILE_SUFFIX = ".tex";

  /**
   * <p>
   * Start of LaTex file.
   * </p>
   */
  public static final String DOCUMENT_START = "\\documentclass{article}" + LINE_BREAK + "\\begin{document}" + LINE_BREAK;

  /**
   * <p>
   * Ending of LaTex file.
   * </p>
   */
  public static final String DOCUMENT_END = "\\end{document}" + LINE_BREAK;
}
