package model.loader;

import model.data.Airport;
import model.data.Storage;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This class tests airportParser.
 */
public class AirportParserTest {
  private AirportParser airportParser;

  /**
   * This method sets up evironment before each test case.
   */
  @Before
  public void setUp() {
    Loader loader = new Loader(new Storage());
    List<Airport> existingLines = new ArrayList<Airport>();
    try {
      ArrayList<String> lines = loader.openFile("../seng202_project/src/test/java/TestFiles/airportsTest.csv");
      airportParser = new AirportParser(lines, existingLines);
    } catch (FileNotFoundException e) {
    }
  }

  /** test if the airportId is valid with a new airport id number that is not exists in storage. */
  @Test
  public void isAirportIdValidNoDupId() {
    assertTrue(airportParser.isIdValid("4"));
  }


  @Test
  public void isAirportIdValidDupId() {
    assertTrue(airportParser.isIdValid("3"));
  }

  @Test
  public void isNameValidWithoutSpace() {
    assertTrue(airportParser.isNameValid("ab"));
  }

  @Test
  public void isNameValidWithSpace() {
    assertTrue(airportParser.isNameValid("a b"));
  }

  @Test
  public void isCityValidWithoutSpace() {
    assertTrue(airportParser.isCityValid("ab"));
  }

  @Test
  public void isCityWithSpace() {
    assertTrue(airportParser.isCityValid("a b"));
  }

  @Test
  public void isCountryValidWithoutSpace() {
    assertTrue(airportParser.isCountryValid("ab"));
  }

  @Test
  public void isCountryValidInvalidName() {
    assertFalse(airportParser.isCountryValid("4a"));
  }

  @Test
  public void isCountryWithSpace() {
    assertTrue(airportParser.isCountryValid("a b"));
  }

  @Test
  public void isIATAValidNull() {
    assertTrue(airportParser.isIATAValid("null"));
  }

  @Test
  public void isIATAValidUnknow() {
    assertFalse(airportParser.isIATAValid("unknow"));
  }

  @Test
  public void isIATAValidNotFormated() {
    assertFalse(airportParser.isIATAValid("abcd"));
  }

  @Test
  public void isIATAValidWithSpace() {
    assertFalse(airportParser.isIATAValid("a b"));
  }

  @Test
  public void isIATAValidValid() {
    assertTrue(airportParser.isIATAValid("ABC"));
  }

  @Test
  public void isICAOValidNull() {
    assertTrue(airportParser.isICAOValid("null"));
  }

  @Test
  public void isICAOValidUnknow() {
    assertFalse(airportParser.isICAOValid("unknow"));
  }

  @Test
  public void isICAOValidNotFormated() {
    assertFalse(airportParser.isICAOValid("abc"));
  }

  @Test
  public void isICAOValidWithSpace() {
    assertFalse(airportParser.isICAOValid("a b"));
  }

  @Test
  public void isICAOValidValid() {
    assertTrue(airportParser.isICAOValid("ABCD"));
  }

  @Test
  public void isLatValidCorrect() {
    assertTrue(airportParser.isLatValid("-6.081689834590001"));
  }

  @Test
  public void isLatValidWrong() {
    assertFalse(airportParser.isLatValid("-6.081689834590001ab"));
  }

  @Test
  public void isLonValidCorrect() {
    assertTrue(airportParser.isLonValid("145.391998291"));
  }

  @Test
  public void isLonValidWrong() {
    assertFalse(airportParser.isLonValid("145.391998291ab"));
  }

  @Test
  public void isAltValidCorrect() {
    assertTrue(airportParser.isAltValid("145"));
  }

  @Test
  public void isAltValidWrong() {
    assertFalse(airportParser.isAltValid("145.391998291ab"));
  }

  @Test
  public void isTZValidCorrect() {
    assertTrue(airportParser.isTZValid("5.5"));
  }

  @Test
  public void isTZValidWrong1() {
    assertFalse(airportParser.isTZValid("5a"));
  }

  @Test
  public void isTZValidWrong2() {
    assertFalse(airportParser.isTZValid("15"));
  }

  @Test
  public void isTZValidWrong3() {
    assertFalse(airportParser.isTZValid("-13"));
  }

  @Test
  public void isTZValidWrong4() {
    assertTrue(airportParser.isTZValid("-12"));
  }

  @Test
  public void isTZValidWrong5() {
    assertTrue(airportParser.isTZValid("14"));
  }

  @Test
  public void isDSValidCorrect() {
    assertTrue(airportParser.isDSTValid("E"));
  }

  @Test
  public void isDSValidWrong1() {
    assertFalse(airportParser.isDSTValid("14"));
  }

  @Test
  public void isDSValidWrong2() {
    assertFalse(airportParser.isDSTValid("B"));
  }

  @Test
  public void isDBValidCorrect() {
    assertTrue(airportParser.isDBTZValid("a/b"));
  }

  @Test
  public void isDBValidWrong1() {
    assertTrue(airportParser.isDBTZValid("ab"));
  }

  @Test
  public void isDBValidWrong2() {
    assertFalse(airportParser.isDBTZValid("a1"));
  }
}
