package model.loader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.*;
import java.util.ArrayList;

public class AirportParserTest {
    private AirportParser parser;
    private final Method methods[] = AirportParser.class.getDeclaredMethods();
    private ArrayList<String> testLines;

    @Before
    public void setUp() {
        testLines = new ArrayList<String>();
        testLines.add("1,\"Goroka\",\"Goroka\",\"Papua New Guinea\",\"GKA\",\"AYGA\",-6.081689,145.391881,5282,10,\"U\",\"Pacific/Port_Moresby\"");
        testLines.add("2,\"Madang\",\"Madang\",\"Papua New Guinea\",\"MAG\",\"AYMD\",-5.207083,145.7887,20,10,\"U\",\"Pacific/Port_Moresby\"");
        testLines.add("3,\"Mount Hagen\",\"Mount Hagen\",\"Papua New Guinea\",\"HGU\",\"AYMH\",-5.826789,144.295861,5388,10,\"U\",\"Pacific/Port_Moresby\"");
        parser = new AirportParser(testLines);
    }

    @Test
    public void testIsIdValid() throws Exception{
        for(Method method: methods) {
            if(method.getName().equals("isIdValid")){
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
    public void testIsNameValid() throws Exception{
        for(Method method: methods) {
            if(method.getName().equals("isNameValid")){
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
    public void testIsCityValid() throws Exception{
        for(Method method: methods) {
            if(method.getName().equals("isCityValid")){
                method.setAccessible(true);
                Object cityWithSpace[] = {"a b"};
                Object cityWithoutSpace[] = {"ab"};
                Object invalidCity[] = {"4a"};
                Object cityWithSpaceTest = method.invoke(parser, cityWithSpace);
                Object cityWithoutSpaceTest = method.invoke(parser, cityWithoutSpace);
                Object invalidCityTest = method.invoke(parser, invalidCity);
                Assert.assertEquals(true, cityWithSpaceTest);
                Assert.assertEquals(true, cityWithoutSpaceTest);
                Assert.assertEquals(false, invalidCityTest);

            }
        }
    }

    @Test
    public void testIsCountryValid() throws Exception{
        for(Method method: methods) {
            if(method.getName().equals("isCountryValid")){
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
    public void testIsIATAValid() throws Exception{
        for(Method method: methods) {
            if(method.getName().equals("isIATAValid")){
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
    public void testIsICAOValid() throws Exception{
        for(Method method: methods) {
            if(method.getName().equals("isICAOValid")){
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
    public void testIsLatValid() throws Exception{
        for(Method method: methods) {
            if(method.getName().equals("isLatValid")){
                method.setAccessible(true);
                Object validLat[] = {"-6.081689834590001"};
                Object invalidLat[] = {"-6.081689834590001ab"};
                Object validLatTest = method.invoke(parser, validLat);
                Object invalidLatTest = method.invoke(parser, invalidLat);
                Assert.assertEquals(true, validLatTest);
                Assert.assertEquals(false, invalidLatTest);

            }
        }
    }

    @Test
    public void testIsLonValid() throws Exception{
        for(Method method: methods) {
            if(method.getName().equals("isLonValid")){
                method.setAccessible(true);
                Object validLon[] = {"145.391998291"};
                Object invalidLon[] = {"145.391998291ab"};
                Object validLonTest = method.invoke(parser, validLon);
                Object invalidLonTest = method.invoke(parser, invalidLon);
                Assert.assertEquals(true, validLonTest);
                Assert.assertEquals(false, invalidLonTest);

            }
        }
    }

    @Test
    public void testIsAltValid() throws Exception{
        for(Method method: methods) {
            if(method.getName().equals("isAltValid")){
                method.setAccessible(true);
                Object validAlt[] = {"145"};
                Object invalidAlt[] = {"145.391998291"};
                Object validAltTest = method.invoke(parser, validAlt);
                Object invalidAltTest = method.invoke(parser, invalidAlt);
                Assert.assertEquals(true, validAltTest);
                Assert.assertEquals(false, invalidAltTest);
            }
        }
    }

    @Test
    public void testIsTZValid() throws Exception{
        for(Method method: methods) {
            if(method.getName().equals("isTZValid")){
                method.setAccessible(true);
                Object validTZ[] = {"5.5"};
                Object invalidTZ[] = {"5a"};
                Object invalidTZ2[] = {"14"};
                Object validTZTest = method.invoke(parser, validTZ);
                Object invalidTZTest = method.invoke(parser, invalidTZ);
                Object invalidTZTest2 = method.invoke(parser, invalidTZ2);
                Assert.assertEquals(true, validTZTest);
                Assert.assertEquals(false, invalidTZTest);
                Assert.assertEquals(false, invalidTZTest2);
            }
        }
    }

    @Test
    public void testIsDSValid() throws Exception{
        for(Method method: methods) {
            if(method.getName().equals("isDSValid")){
                method.setAccessible(true);
                Object validDS[] = {"E"};
                Object invalidDS[] = {"14"};
                Object invalidDS2[] = {"B"};
                Object validDSTest = method.invoke(parser, validDS);
                Object invalidDSTest = method.invoke(parser, invalidDS);
                Object invalidDSTest2 = method.invoke(parser, invalidDS2);
                Assert.assertEquals(true, validDSTest);
                Assert.assertEquals(false, invalidDSTest);
                Assert.assertEquals(false, invalidDSTest2);
            }
        }
    }

    @Test
    public void testIsDBTZValid() throws Exception{
        for(Method method: methods) {
            if(method.getName().equals("isDBTZValid")){
                method.setAccessible(true);
                Object validDBTZ[] = {"a/b"};
                Object validDBTZ2[] = {"ab"};
                Object invalidDBTZ[] = {"a1"};
                Object validDBTZTest = method.invoke(parser, validDBTZ);
                Object validDBTZTest2 = method.invoke(parser, validDBTZ2);
                Object invalidDBTZTest = method.invoke(parser, invalidDBTZ);
                Assert.assertEquals(true, validDBTZTest);
                Assert.assertEquals(true, validDBTZTest2);
                Assert.assertEquals(false, invalidDBTZTest);
            }
        }
    }

}