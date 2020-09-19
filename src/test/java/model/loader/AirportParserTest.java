package model.loader;

import model.data.Airport;
import model.data.Storage;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AirportParserTest {
    private AirportParser airportParser;

    //    private AirportParser parser;
//    private final Method methods[] = AirportParser.class.getDeclaredMethods();
//    private ArrayList<String> testLines;
//
    @Before
    public void setUp() {
        Loader loader = new Loader(new Storage());
        List<Airport> existingLines = new ArrayList<Airport>();
        try{
            ArrayList<String> lines = loader.openFile("../seng202_project/src/test/java/TestFiles/airportsTest.csv");
            airportParser = new AirportParser(lines, existingLines);
        } catch (FileNotFoundException e) {
        }
//        testLines = new ArrayList<String>();
//        testLines.add("1,\"Goroka\",\"Goroka\",\"Papua New Guinea\",\"GKA\",\"AYGA\",-6.081689,145.391881,5282,10,\"U\",\"Pacific/Port_Moresby\"");
//        testLines.add("2,\"Madang\",\"Madang\",\"Papua New Guinea\",\"MAG\",\"AYMD\",-5.207083,145.7887,20,10,\"U\",\"Pacific/Port_Moresby\"");
//        testLines.add("3,\"Mount Hagen\",\"Mount Hagen\",\"Papua New Guinea\",\"HGU\",\"AYMH\",-5.826789,144.295861,5388,10,\"U\",\"Pacific/Port_Moresby\"");
//        parser = new AirportParser(testLines);
    }

    @Test
    public void isAirportIdValidNoDupId(){
        assertEquals(airportParser.isIdValid("4"), true);
    }

    @Test
    public void isAirportIdValidDupId(){
      assertTrue(airportParser.isIdValid("3"));
    }

//    @Test
//    public void isAirportIdValidNotNumber() {
//        assertTrue(airportParser.isIdValid("4a"));
//    }

    @Test
    public void isNameValidWithoutSpace() {
        assertTrue(airportParser.isNameValid("ab"));
    }

    //    @Test
//    public void isNameValidInvalidName(){
//        assertEquals(airportParser.isNameValid("4a"), false);
//    }
// todo change name regex
    @Test
    public void isNameValidWithSpace() {
        assertEquals(airportParser.isNameValid("a b"), true);
    }


    @Test
    public void isCityValidWithoutSpace() {
        assertEquals(airportParser.isCityValid("ab"), true);
    }

//    @Test
//    public void isCityValidInvalidName(){
//        assertEquals(airportParser.isCityValid("4a"), false);
//    }
    // todo change city regex

    @Test
    public void isCityWithSpace() {
        assertEquals(airportParser.isCityValid("a b"), true);
    }

    @Test
    public void isCountryValidWithoutSpace() {
        assertEquals(airportParser.isCountryValid("ab"), true);
    }

    @Test
    public void isCountryValidInvalidName(){
        assertEquals(airportParser.isCountryValid("4a"), false);
    }

    @Test
    public void isCountryWithSpace(){
        assertEquals(airportParser.isCountryValid("a b"), true);
    }

    @Test
    public void isIATAValidNull(){
        assertEquals(airportParser.isIATAValid("null"), true);
    }

    @Test
    public void isIATAValidUnknow(){
        assertEquals(airportParser.isIATAValid("unknow"), false);
    }

    @Test
    public void isIATAValidNotFormated(){
        assertEquals(airportParser.isIATAValid("abcd"), false);
    }

    @Test
    public void isIATAValidWithSpace(){
        assertEquals(airportParser.isIATAValid("a b"), false);
    }

    @Test
    public void isIATAValidValid(){
        assertEquals(airportParser.isIATAValid("ABC"), true);
    }

    @Test
    public void isICAOValidNull(){
        assertEquals(airportParser.isICAOValid("null"), true);
    }

    @Test
    public void isICAOValidUnknow(){
        assertEquals(airportParser.isICAOValid("unknow"), false);
    }

    @Test
    public void isICAOValidNotFormated(){
        assertEquals(airportParser.isICAOValid("abc"), false);
    }

    @Test
    public void isICAOValidWithSpace(){
        assertEquals(airportParser.isICAOValid("a b"), false);
    }

    @Test
    public void isICAOValidValid(){
        assertEquals(airportParser.isICAOValid("ABCD"), true);
    }

    @Test
    public void isLatValidCorrect(){
        assertEquals(airportParser.isLatValid("-6.081689834590001"), true);
    }

    @Test
    public void isLatValidWrong(){
        assertEquals(airportParser.isLatValid("-6.081689834590001ab"), false);
    }

    @Test
    public void isLonValidCorrect(){
        assertEquals(airportParser.isLonValid("145.391998291"), true);
    }

    @Test
    public void isLonValidWrong(){
        assertEquals(airportParser.isLonValid("145.391998291ab"), false);
    }

    @Test
    public void isAltValidCorrect(){
        assertEquals(airportParser.isAltValid("145"), true);
    }

    @Test
    public void isAltValidWrong(){
        assertEquals(airportParser.isAltValid("145.391998291ab"), false);
    }

    @Test
    public void isTZValidCorrect(){
        assertEquals(airportParser.isTZValid("5.5"), true);
    }

    @Test
    public void isTZValidWrong1(){
        assertEquals(airportParser.isTZValid("5a"), false);
    }

    @Test
    public void isTZValidWrong2(){
        assertFalse(airportParser.isTZValid("15"));
    }

    @Test
    public void isTZValidWrong3(){
        assertFalse(airportParser.isTZValid("-13"));
    }

    @Test
    public void isTZValidWrong4(){
        assertTrue(airportParser.isTZValid("-12"));
    }

    @Test
    public void isTZValidWrong5(){
        assertTrue(airportParser.isTZValid("14"));
    }

    @Test
    public void isDSValidCorrect(){
        assertEquals(airportParser.isDSTValid("E"), true);
    }

    @Test
    public void isDSValidWrong1(){
        assertEquals(airportParser.isDSTValid("14"), false);
    }

    @Test
    public void isDSValidWrong2(){
        assertEquals(airportParser.isDSTValid("B"), false);
    }

    @Test
    public void isDBValidCorrect(){
    assertEquals(airportParser.isDBTZValid("a/b"), true);
    }

    @Test
    public void isDBValidWrong1(){
        assertEquals(airportParser.isDBTZValid("ab"), true);
    }

    @Test
    public void isDBValidWrong2(){
        assertEquals(airportParser.isDBTZValid("a1"), false);
    }

}
