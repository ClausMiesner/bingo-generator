package de.miesner.claus.bingo.util.latex;

public class LatexExpression {

  /**
   * <p>
   * Provides expression to create the start of a table.
   * After the {@link de.miesner.claus.bingo.util.latex.LatexExpression#TABLE_COLUMN_SPECIFICATION_OFFSET}
   * insert the style for the columns.
   * </p>
   */
public static final String TABLE_START = "\\begin{table}\n" +
                                          "\\begin{tabular}{}" ;
  /**
   * <p>
   *   Offset after which the column design specification has to be inserted into
   *   the {@link de.miesner.claus.bingo.util.latex.LatexExpression#TABLE_START}.
   * </p>
   */
public static final int TABLE_COLUMN_SPECIFICATION_OFFSET = 30;
  /**
   * <p>
   *   Separator to use in the column design specification of a tabular.
   *   Results in printed horizontal divider lines in a table.
   * </p>
   */
public static final char TABLE_COLUMN_SEPARATOR = '|';
  /**
   * <p>
   *   Marks the end of a row in a table.
   * </p>
   */
public static final String TABLE_ROW_BREAK = "\\\\";
  /**
   * <p>
   *   Used in between entries of a table row.
   * </p>
   */
public static final String TABLE_ROW_ENTRY_CONNECTOR = " & ";
  /**
   * <p>
   *   Prints a horizontal line in between rows.
   * </p>
   */
public static final String TABLE_ROW_SEPARATOR = "\\hline";
  /**
   * <p>
   *   Expressions to close a table.
   * </p>
   */
public static final String TABLE_END = "\\end{tabular}\n" +
                                        "\\end{table}";
  /**
   * <p>
   *   Specifies the text alignment to the left.
   * </p>
   */
public static final char LEFT_ALIGN = 'l';
  /**
   * <p>
   *   Specifies the text alignment to the right.
   * </p>
   */
public static final char RIGHT_ALIGN = 'r';
  /**
   * <p>
   *   Specifies the text alignment to center.
   * </p>
   */
public static final char CENTER_ALIGN = 'c';
  /**
   * <p>
   *   Adds a line break in source code.
   * </p>
   */
public static final String LINE_BREAK = "\n";
}
