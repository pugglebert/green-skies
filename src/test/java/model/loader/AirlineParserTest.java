package model.loader;

import model.data.Airline;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Test cases for routePaser.
 * @author Nathan
 */
public class AirlineParserTest {
  //todo change to using loader
  private AirlineParser parser;
  private final Method[] methods = AirlineParserTest.class.getDeclaredMethods();

  @Before
  public void setUp() throws Exception {

    ArrayList<String> testLines = new ArrayList<>();

    BufferedReader br = new BufferedReader(new FileReader("src/test/java/TestFiles/airlines.csv"));
    int count = 0;
    String line;
    br.readLine(); // header
    while ((line = br.readLine()) != null && count < 3) {
      line = line.replaceAll("[\"]", "");
      testLines.add(line);
      count++;
    }
    List<Airline> existingLines = new ArrayList<>();
    parser = new AirlineParser(testLines, existingLines);

  }
  /** Verify that isIDValid returns true for ID that is not contain any alphabetic character and not already in the database. */
  @Test
  public void testIsIdValid() throws Exception {
    for (Method method : methods) {
      if (method.getName().equals("isIdValid")) {
        method.setAccessible(true);
        Object dupID = "1";
        Object validID = "4";
        Object invalidID = "4a";

        Boolean dupIDTest = (Boolean) method.invoke(parser, dupID);
        Boolean validIdTest = (Boolean) method.invoke(parser, validID);
        Boolean intvalidIdTest = (Boolean) method.invoke(parser, invalidID);

        Assert.assertEquals(false, dupIDTest);
        Assert.assertEquals(true, validIdTest);
        Assert.assertEquals(false, intvalidIdTest);
      }
    }
  }

  /** Verify that isNameValid returns true for name that not started with numerals.  */
  @Test
  public void testIsNameValid() throws Exception {
    for (Method method : methods) {
      if (method.getName().equals("isNameValid")) {
        method.setAccessible(true);
        Object nameWithSpace = "a b";
        Object nameWithoutSpace = "ab";
        Object invalidName = "4a";

        Boolean nameWithSpaceTest = (Boolean) method.invoke(parser, nameWithSpace);
        Boolean nameWithoutSpaceTest = (Boolean) method.invoke(parser, nameWithoutSpace);
        Boolean invalidNameTest = (Boolean) method.invoke(parser, invalidName);

        Assert.assertEquals(true, nameWithSpaceTest);
        Assert.assertEquals(true, nameWithoutSpaceTest);
        Assert.assertEquals(false, invalidNameTest);
      }
    }
  }

  /** Verify that isAliasValid return true only alphabetic character and null value "\N". */
  @Test
  public void testIsAliasValid() throws Exception {
    for (Method method : methods) {
      if (method.getName().equals("isAliasValid")) {
        method.setAccessible(true);
        Object validAlias = "ab";
        Object nullAlias = "\\N";
        Object invalidName1 = "4a";
        Object invalidName2 = "\\n";

        Boolean validAliasTest = (Boolean) method.invoke(parser, validAlias);
        Boolean nullAliasTest = (Boolean) method.invoke(parser, nullAlias);
        Boolean invalidName1Test = (Boolean) method.invoke(parser, invalidName1);
        Boolean invalidName2Test = (Boolean) method.invoke(parser, invalidName2);

        Assert.assertEquals(true, validAliasTest);
        Assert.assertEquals(true, nullAliasTest);
        Assert.assertEquals(false, invalidName1Test);
        Assert.assertEquals(false, invalidName2Test);
      }
    }
  }

  /** Verify that isIATAValid return only when IATA is 2 characters. */
  @Test
  public void testIsIATAValid() throws Exception {
    for (Method method : methods) {
      if (method.getName().equals("isIATAValid")) {
        method.setAccessible(true);
        Object IATANull = "null";
        Object IATAUnknow = "unknown";
        Object IATANotFormated = "abcd";
        Object IATAWithSpace = "a b";
        Object IATAInValid = "ABC";

        Boolean IATANullTest = (Boolean) method.invoke(parser, IATANull);
        Boolean IATAUnknowTest = (Boolean) method.invoke(parser, IATAUnknow);
        Boolean IATANotFormatedTest = (Boolean) method.invoke(parser, IATANotFormated);
        Boolean IATAWithSpaceTest = (Boolean) method.invoke(parser, IATAWithSpace);
        Boolean IATAInValidTest = (Boolean) method.invoke(parser, IATAInValid);

        Assert.assertEquals(true, IATANullTest);
        Assert.assertEquals(true, IATAUnknowTest);
        Assert.assertEquals(false, IATANotFormatedTest);
        Assert.assertEquals(false, IATAWithSpaceTest);
        Assert.assertEquals(true, IATAInValidTest);
      }
    }
  }

  /** Verify that isICAOValid return true only when ICAO is 3 characters. */
  @Test
  public void testIsICAOValid() throws Exception {
    for (Method method : methods) {
      if (method.getName().equals("isICAOValid")) {
        method.setAccessible(true);
        Object ICAONull = "null";
        Object ICAOUnknow = "unknown";
        Object ICAONotFormated = "abc";
        Object ICAOWithSpace = "a b";
        Object ICAOValid = "ABCD";

        Object ICAONullTest = method.invoke(parser, ICAONull);
        Object ICAOUnknowTest = method.invoke(parser, ICAOUnknow);
        Object ICAONotFormatedTest = method.invoke(parser, ICAONotFormated);
        Object ICAOWithSpaceTest = method.invoke(parser, ICAOWithSpace);
        Object ICAOValidTest = method.invoke(parser, ICAOValid);

        Assert.assertEquals(true, ICAONullTest);
        Assert.assertEquals(true, ICAOUnknowTest);
        Assert.assertEquals(false, ICAONotFormatedTest);
        Assert.assertEquals(false, ICAOWithSpaceTest);
        Assert.assertEquals(true, ICAOValidTest);
      }
    }
  }

  /** Verify that isCallsignValid return true only when callsign is a string not starting with numerals . */
  @Test
  public void testIsCallsignValid() throws Exception {
    for (Method method : methods) {
      if (method.getName().equals("isCallsignValid")) {
        method.setAccessible(true);
        String validCallsign1 = "";
        String validCallsign2 = "ARGENTINA";
        String validCallsign3 = "ALL NIPPON";
        String invalidCallsign = "1TESt";

        Boolean validCallsign1Test = (Boolean) method.invoke(parser, validCallsign1);
        Boolean validCallsign2Test = (Boolean) method.invoke(parser, validCallsign2);
        Boolean validCallsign3Test = (Boolean) method.invoke(parser, validCallsign3);
        Boolean invalidCallsignTest = (Boolean) method.invoke(parser, invalidCallsign);

        Assert.assertEquals(true, validCallsign1Test);
        Assert.assertEquals(true, validCallsign2Test);
        Assert.assertEquals(true, validCallsign3Test);
        Assert.assertEquals(false, invalidCallsignTest);
      }
    }
  }

  /** Verify that isCountryValid return true only when country is a string not started with numerals . */
  @Test
  public void testIsCountryValid() throws Exception {
    for (Method method : methods) {
      if (method.getName().equals("isCountryValid")) {
        method.setAccessible(true);
        Object countryWithSpace = "a b";
        Object countryWithoutSpace = "ab";
        Object invalidcountry = "4a";

        Boolean countryWithSpaceTest = (Boolean) method.invoke(parser, countryWithSpace);
        Boolean countryWithoutSpaceTest = (Boolean) method.invoke(parser, countryWithoutSpace);
        Boolean invalidCountryTest = (Boolean) method.invoke(parser, invalidcountry);

        Assert.assertEquals(true, countryWithSpaceTest);
        Assert.assertEquals(true, countryWithoutSpaceTest);
        Assert.assertEquals(false, invalidCountryTest);
      }
    }
  }

  /** Verify that isActiveStatusValid return true only when activeStatus is "Y" or "N"*/
  @Test
  public void testIsActiveStatusValid() throws Exception {
    for (Method method : methods) {
      if (method.getName().equals("isActiveStatusValid")) {
        method.setAccessible(true);
        String validActiveStatus1 = "Y";
        String validActiveStatus2 = "N";
        String invalidCallsign1 = "No";
        String invalidCallsign2 = "\\N";

        Boolean validActiveStatus1Test = (Boolean) method.invoke(parser, validActiveStatus1);
        Boolean validActiveStatus2Test = (Boolean) method.invoke(parser, validActiveStatus2);
        Boolean invalidCallsign1Test = (Boolean) method.invoke(parser, invalidCallsign1);
        Boolean invalidCallsign2Test = (Boolean) method.invoke(parser, invalidCallsign2);

        Assert.assertEquals(true, validActiveStatus1Test);
        Assert.assertEquals(true, validActiveStatus2Test);
        Assert.assertEquals(false, invalidCallsign1Test);
        Assert.assertEquals(false, invalidCallsign2Test);
      }
    }
  }

  /** The data parser should add null to the index that not been accumulate by an Airline yet */
  @Test
  public void dataParser() {
    Airline temp0 = (Airline) parser.parserData.get(0);
    Assert.assertNull(temp0);
    Airline temp1 = (Airline) parser.parserData.get(1);
    Assert.assertEquals("Private flight", temp1.getName());
    Airline temp2 = (Airline) parser.parserData.get(2);
    Assert.assertEquals("135 Airways", temp2.getName());
  }

  @Test
  public void validater() {
    String[] invalidValider = {"-1", "Unknown", "\\N", "-", "N/A", "\\N", "\\N", "Y"};
    Assert.assertFalse(parser.validater(invalidValider));
  }
}
