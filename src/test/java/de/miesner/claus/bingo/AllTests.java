package de.miesner.claus.bingo;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectPackages({
        "de.miesner.claus.bingo.table",
        "de.miesner.claus.bingo.io",
        "de.miesner.claus.bingo.util.latex",
        "de.miesner.claus.bingo.random"})
@SelectClasses({
        BingoTicketGeneratorTest.class,
        TableCreationIT.class})
@SuiteDisplayName("All tests Test Suite")
public class AllTests {
}
