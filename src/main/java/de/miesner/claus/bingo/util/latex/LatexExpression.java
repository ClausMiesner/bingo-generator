package de.miesner.claus.bingo.util.latex;

public class LatexExpression {

  /**
   * Provides expression to create the start of a table.
   * Has a placeholder "%Column_Specification%" for specifying number and style of columns.
   */
public static final String TABLE_START = "\\begin{table}\n" +
                                          "\\begin{tabular}{}" ;
public static final int TABLE_COLUMN_SPECIFICATION_OFFSET = 29;
public static final char TABLE_COLUMN_SEPARATOR = '|';
public static final String TABLE_ROW_BREAK = "\\\\";
public static final String TABLE_ROW_ENTRY_CONNECTOR = " & ";
public static final String TABLE_END = "\\end{tabular}\n" +
                                        "\\end{table}";
public static final char LEFT_ALIGN = 'l';
public static final char RIGHT_ALIGN = 'r';
public static final char CENTER_ALIGN = 'c';


}
