package de.miesner.claus.bingo;

import de.miesner.claus.bingo.random.BasicTermRandomizer;
import de.miesner.claus.bingo.table.Table;
import de.miesner.claus.bingo.util.latex.LatexExpression;
import de.miesner.claus.bingo.util.latex.LatexTextAlignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.miesner.claus.bingo.util.latex.LatexExpression.TABLE_COLUMN_SPECIFICATION_OFFSET;
import static org.assertj.core.api.Assertions.assertThat;

class TableCreationIT {

  @BeforeEach
  void setUp() {

  }


  @Test
  void createATable() {
    List<String> possibleTerms = List.of("beer", "wine", "water", "milk", "soda", "tea", "coffee", "mate");
    Table table = Table.builder()
            .withTermRandomizer(new BasicTermRandomizer())
            .withMaxOccurrencesForTerm(2)
            .withPossibleBingoTerms(possibleTerms)
            .withNumberOfRows(4)
            .withColumnSeparator()
            .withTextAlignment(LatexTextAlignment.RIGHT_ALIGN)
            .build();

    String tableStartWithoutColumnSpecification = LatexExpression.TABLE_START.substring(0, TABLE_COLUMN_SPECIFICATION_OFFSET - 1);
    assertThat(table.toString())
            .as("Beginning of LaTex Table is correct.").startsWith(tableStartWithoutColumnSpecification)
            .as("Column specification is correct.").containsIgnoringWhitespaces("|r|r|r|r|")
            .as("Contains every term twice.")
            .containsPattern("beer[\\W,\\w]+beer")
            .containsPattern("wine[\\W,\\w]+wine")
            .containsPattern("water[\\W,\\w]+water")
            .containsPattern("milk[\\W,\\w]+milk")
            .containsPattern("soda[\\W,\\w]+soda")
            .containsPattern("tea[\\W,\\w]+tea")
            .containsPattern("coffee[\\W,\\w]+coffee")
            .containsPattern("mate[\\W,\\w]+mate")
            .as("Ending of LaTex Table is correct.").endsWith(LatexExpression.TABLE_END);


  }
}
