package model.loader;

import model.data.Airline;
import model.data.Storage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test cases for routePaser.
 *
 * @author Nathan
 */
public class AirlineParserTest {
  // todo change to using loader
  private AirlineParser airlineParser;
  Loader loader = new Loader(new Storage());
  List<Airline> existingLines = new ArrayList<>();
  @Before
  public void setUp() {


    try {
      ArrayList<String> lines =
              loader.openFile("../seng202_project/src/test/java/TestFiles/airlinesTest.csv");
      ArrayList<String> lines_200_Error =
              loader.openFile("../seng202_project/src/test/java/TestFiles/airports.csv");

      airlineParser = new AirlineParser(lines, existingLines);

    } catch (FileNotFoundException ignored) {
    }
  }

  @Test
  public void isAddAirlineValidDupAirlineID() {
    airlineParser.addAirLine(3, new Airline(3, "Airline Name", "Airline Alias", "IA", "ICA", "Airline Callsign", "Airline Country", true));
  }




  /**
   * Test if method accept new airport id number that is not exist in the storage
   */
  @Test
  public void isAirlineIdValidNoDupId() {
    assertTrue(airlineParser.isIdValid("4"));
  }
  /**
   * Test if method reject duplicate airlineID.
   */
  @Test
  public void isAirlineIdValidDupId() {
    assertFalse(airlineParser.isIdValid("3"));
  }

  /**
   * Test if method reject airportID that is not numerals
   */
  @Test
  public void isAirlineIdInvalid() {
    assertFalse(airlineParser.isIdValid("One"));
  }

  /**
   * Test if method accept valid airline name without space.
   */
  @Test
  public void isNameValidWithoutSpace() {
    assertTrue(airlineParser.isNameValid("ab"));
  }

  /**
   * Test if method accept valid airline name with space.
   */
  @Test
  public void isNameValidWithSpace() {
    assertTrue(airlineParser.isNameValid("a b"));
  }
  /**
   * Test if method accept valid airline name started with numerals.
   */
  @Test
  public void isNameValidStartedWithNumerals() {
    assertTrue(airlineParser.isNameValid("4b"));
  }

  /**
   * Test if method accept valid alias name.
   */
  @Test
  public void isAliasValidWithoutSpace() {
    assertTrue(airlineParser.isAliasValid("abcdefghi"));
  }

  /**
   * Test if method reject invalid alias that have special character.
   */
  @Test
  public void isAliasInvalid() {
    assertFalse(airlineParser.isAliasValid("a%b"));
  }

  @Test
  public void isIATAValidUnknow() {
    assertFalse(airlineParser.isIATAValid("unknown"));
  }

  /**
   * Test if method reject unexpected formation of code.
   */
  @Test
  public void isIATAValidNotFormated() {
    assertFalse(airlineParser.isIATAValid("abcd"));
  }

  /**
   * Test if method reject IATA code with space.
   */
  @Test
  public void isIATAValidWithSpace() {
    assertFalse(airlineParser.isIATAValid("a b"));
  }

  /**
   * Test if method accept IATA code with correct fomation.
   */
  @Test
  public void isIATAValidValid() {
    assertTrue(airlineParser.isIATAValid("AB"));
  }

  /**
   * Test if method reject IATA code that is null.
   */
  @Test
  public void isICAOValidNull() {
    assertTrue(airlineParser.isICAOValid("\\N"));
  }

  /**
   * Test if method accept ICAO code that is unknown
   */
  @Test
  public void isICAOValidUnknow() {
    assertTrue(airlineParser.isICAOValid("\\N"));
  }

  /**
   * Test if method reject ICAO code with incorrect fomation.
   */
  @Test
  public void isICAOValidNotFormated() {
    assertFalse(airlineParser.isICAOValid("abc"));
  }

  /**
   * Test if method reject invalid ICAO code with space.
   */
  @Test
  public void isICAOValidWithSpace() {
    assertFalse(airlineParser.isICAOValid("a b"));
  }

  /**
   * Test if method accpet ICAO code with space.
   */
  @Test
  public void isICAOValidValid() {
    assertTrue(airlineParser.isICAOValid("ASS"));
  }

  /**
   *
   */
  @Test
  public void isCallsignValid() {
    assertTrue(airlineParser.isCallsignValid("AN"));
  }

  /**
   *
   */
  @Test
  public void isCallsignValidStartedWithNumerals() {
    assertFalse(airlineParser.isCallsignValid("1ab"));
  }

  /**
   * Test if method accept valid country without space.
   */
  @Test
  public void isCountryValidWithoutSpace() {
    assertTrue(airlineParser.isCountryValid("ab"));
  }

  /**
   * Test if method reject invalid coutry name.
   */
  @Test
  public void isCountryValidInvalidName() {
    assertFalse(airlineParser.isCountryValid("4a"));
  }

  /**
   * Test if method accept valid country name with space.
   */
  @Test
  public void isCountryWithSpace() {
    assertTrue(airlineParser.isCountryValid("a b"));
  }

  /**
   * Test if method reject invalid country name with space.
   */
  @Test
  public void isCountryInvalid() {
    assertFalse(airlineParser.isCountryValid("a%b"));
  }

  /**
   * Test if method accept airline with activeStatus field "Y" or "N"
   */
  @Test
  public void isActivaStatusValid() {
    assertTrue(airlineParser.isActiveStatusValid("Y"));
    assertTrue(airlineParser.isActiveStatusValid("N"));
  }

  /**
   * Test if method reject airline with activeStatus field not "Y" and "N"
   */
  @Test
  public void isActivaStatusInvalid() {
    assertFalse(airlineParser.isActiveStatusValid("Yes"));
    assertFalse(airlineParser.isActiveStatusValid("No"));
  }


  @Test
  public void isValidaterValidAllInvalid() {
    Airline testAirline = new Airline(-1, "N@me", "@LI@S", "NotIATA", "NotICAO", "1Callsign", "1Country", true);
    airlineParser.addAirLine(-1, testAirline);
//    assertNotSame();
  }

  @Test
  public void isvalidaterValidNotEnoughPara() {
    String[] invalidValider = {"-1", "N@me", "@LI@S", "NotIATA", "NotICAO", "1Callsign", "1Country"};
  }


  @Test
  public void isAddAirlineValidDupID () {
    Airline testAirline = new Airline(3, "N@me", "@LI@S", "NotIATA", "NotICAO", "1Callsign", "1Country", true);
    airlineParser.addAirLine(3, testAirline);
    assertNotSame(testAirline, airlineParser.parserData.get(3));
  }

//  /** The data parser should add null to the index that not been accumulate by an Airline yet */
//  @Test
//  public void dataParser() {
//    Airline temp0 = (Airline) parser.parserData.get(0);
//    Assert.assertNull(temp0);
//    Airline temp1 = (Airline) parser.parserData.get(1);
//    Assert.assertEquals("Private flight", temp1.getName());
//    Airline temp2 = (Airline) parser.parserData.get(2);
//    Assert.assertEquals("135 Airways", temp2.getName());
//  }
//

}
