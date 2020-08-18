package model.loader;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AirlineParserTest {
    private AirlineParser parser;
    private final Method methods[] = AirlineParserTest.class.getDeclaredMethods();
    private ArrayList<String> testLines;


    @Before
    public void setUp() throws Exception {
        testLines = new ArrayList<String>();
        testLines.add("1,\"Private flight\",\\N,\"-\",\"N/A\",\"\",\"\",\"Y\"");
        testLines.add("2,\"135 Airways\",\\N,\"\",\"GNL\",\"GENERAL\",\"United States\",\"N\"");
        testLines.add("3,\"1Time Airline\",\\N,\"1T\",\"RNX\",\"NEXTIME\",\"South Africa\",\"Y\"");
        parser = new AirlineParser(testLines);

    }

    public void testIsIdValid() throws Exception {
        for (Method method : methods) {
            if (method.getName().equals("isIdValid")) {
                method.setAccessible(true);
                Object dupID[] = {"1"};
                Object validID[] = {"4"};
                Object invalidID[] = {"4a"};

                Object dupIDTest = method.invoke(parser, dupID);
                Object validIdTest = method.invoke(parser, validID);
                Object intvalidIdTest = method.invoke(parser, invalidID);

                Assert.assertEquals(false, dupIDTest);
                Assert.assertEquals(true, validIdTest);
                Assert.assertEquals(false, intvalidIdTest);

            }
        }
    }

    @Test
    public void testIsNameValid() throws Exception {
        for (Method method : methods) {
            if (method.getName().equals("isNameValid")) {
                method.setAccessible(true);
                Object nameWithSpace[] = {"a b"};
                Object nameWithoutSpace[] = {"ab"};
                Object invalidName[] = {"4a"};

                Object nameWithSpaceTest = method.invoke(parser, nameWithSpace);
                Object nameWithoutSpaceTest = method.invoke(parser, nameWithoutSpace);
                Object invalidNameTest = method.invoke(parser, invalidName);

                Assert.assertEquals(true, nameWithSpaceTest);
                Assert.assertEquals(true, nameWithoutSpaceTest);
                Assert.assertEquals(false, invalidNameTest);

            }
        }
    }

    @Test
    public void testIsAliasValid() throws Exception {
        for (Method method : methods) {
            if (method.getName().equals("isAliasValid")) {
                method.setAccessible(true);
                Object validAlias[] = {"ab"};
                Object nullAlias[] = {"\\N"};
                Object invalidName1[] = {"4a"};
                Object invalidName2[] = {"\\n"};

                Object validAliasTest = method.invoke(parser, validAlias);
                Object nullAliasTest = method.invoke(parser, nullAlias);
                Object invalidName1Test = method.invoke(parser, invalidName1);
                Object invalidName2Test = method.invoke(parser, invalidName2);

                Assert.assertEquals(true, validAlias);
                Assert.assertEquals(true, nullAlias);
                Assert.assertEquals(false, invalidName1);
                Assert.assertEquals(false, invalidName2);
            }
        }
    }


    @Test
    public void testIsIATAValid() throws Exception {
        for (Method method : methods) {
            if (method.getName().equals("isIATAValid")) {
                method.setAccessible(true);
                Object IATANull[] = {"null"};
                Object IATAUnknow[] = {"unknown"};
                Object IATANotFormated[] = {"abcd"};
                Object IATAWithSpace[] = {"a b"};
                Object IATAValid[] = {"ABC"};
                Object IATANullTest = method.invoke(parser, IATANull);
                Object IATAUnknowTest = method.invoke(parser, IATAUnknow);
                Object IATANotFormatedTest = method.invoke(parser, IATANotFormated);
                Object IATAWithSpaceTest = method.invoke(parser, IATAWithSpace);
                Object IATAValidTest = method.invoke(parser, IATAValid);
                Assert.assertEquals(true, IATANullTest);
                Assert.assertEquals(true, IATAUnknowTest);
                Assert.assertEquals(false, IATANotFormatedTest);
                Assert.assertEquals(false, IATAWithSpaceTest);
                Assert.assertEquals(true, IATAValidTest);
            }
        }
    }

    @Test
    public void testIsICAOValid() throws Exception {
        for (Method method : methods) {
            if (method.getName().equals("isICAOValid")) {
                method.setAccessible(true);
                Object ICAONull[] = {"null"};
                Object ICAOUnknow[] = {"unknown"};
                Object ICAONotFormated[] = {"abc"};
                Object ICAOWithSpace[] = {"a b"};
                Object ICAOValid[] = {"ABCD"};

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


    @Test
    public void testIsCallsignValid() throws Exception {
        for (Method method : methods) {
            if (method.getName().equals("isCallsignValid")) {
                method.setAccessible(true);
                Object validCallsign1[] = {""};
                Object validCallsign2[] = {"ARGENTINA"};
                Object validCallsign3[] = {"ALL NIPPON"};
                Object invalidCallsign[] = {"1TESt"};

                Object validCallsign1Test = method.invoke(parser, validCallsign1);
                Object validCallsign2Test = method.invoke(parser, validCallsign2);
                Object validCallsign3Test = method.invoke(parser, validCallsign3);
                Object invalidCallsignTest = method.invoke(parser, invalidCallsign);

                Assert.assertEquals(true, validCallsign1Test);
                Assert.assertEquals(true, validCallsign2Test);
                Assert.assertEquals(true, validCallsign3Test);
                Assert.assertEquals(false, invalidCallsignTest);
            }
        }
    }


    @Test
    public void testIsCountryValid() throws Exception {
        for (Method method : methods) {
            if (method.getName().equals("isCountryValid")) {
                method.setAccessible(true);
                Object countryWithSpace[] = {"a b"};
                Object countryWithoutSpace[] = {"ab"};
                Object invalidcountry[] = {"4a"};
                Object countryWithSpaceTest = method.invoke(parser, countryWithSpace);
                Object countryWithoutSpaceTest = method.invoke(parser, countryWithoutSpace);
                Object invalidCountryTest = method.invoke(parser, invalidcountry);
                Assert.assertEquals(true, countryWithSpaceTest);
                Assert.assertEquals(true, countryWithoutSpaceTest);
                Assert.assertEquals(false, invalidCountryTest);

            }
        }
    }

    @Test
    public void testIsActiveStatusValid() throws Exception {
        for (Method method : methods) {
            if (method.getName().equals("isActiveStatusValid")) {
                method.setAccessible(true);
                Object validActiveStatus1[] = {"Y"};
                Object validActiveStatus2[] = {"N"};
                Object invalidCallsign1[] = {"No"};
                Object invalidCallsign2[] = {"\\N"};

                Object validActiveStatus1Test = method.invoke(parser, validActiveStatus1);
                Object validActiveStatus2Test = method.invoke(parser, validActiveStatus2);
                Object invalidCallsign1Test = method.invoke(parser, invalidCallsign1);
                Object invalidCallsign2Test = method.invoke(parser, invalidCallsign2);

                Assert.assertEquals(true, validActiveStatus1Test);
                Assert.assertEquals(true, validActiveStatus2Test);
                Assert.assertEquals(true, invalidCallsign1Test);
                Assert.assertEquals(false, invalidCallsign2Test);
            }
        }
    }


    @Test
    public void dataParser() {
    }

    @Test
    public void validater() {
    }

    @Test
    public void getAirlines() {
    }

    @Test
    public void testDataParser() {
    }

    @Test
    public void testValidater() {
    }
}