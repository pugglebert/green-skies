package model.data;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Test cases for Storage class.
 * @author Ella Johnson
 * @since 22/08/2020
 * @version 1.0
 */
public class StorageTest {

    private Storage storage;

    @Before
    public void setUp() {
        storage = new Storage();
    }

    /**
     * Call setData method with airlines as datatype
     */
    private Set<DataType> createAirlineSet() {
        Set<DataType> testAirlines = new HashSet<>();
        testAirlines.add(new Airline(3478,"Mex-Jet","N","","MJT","MEJETS",
                "Mexico",false));
        testAirlines.add(new Airline(1711,"Centre-Avia","N","J7","CVC","AVIACENTRE",
                "Russia",false));
        return testAirlines;
    }

    /**
     * Test that airlines is set to given values when setData is called with airlines as the datatype.
     */
    @Test
    public void setDataAirlineUpdatedTest() {
        Set<DataType> testAirlines = createAirlineSet();
        storage.setData(testAirlines, "Airline");
        assertEquals(testAirlines, storage.getAirlines());
    }

    /**
     * Test that airport is not changed when setData is called with airlines as the datatype.
     */
    @Test
    public void setDataAirportUnchangedTest() {
        Set<DataType> testAirlines = createAirlineSet();
        storage.setData(testAirlines, "Airline");
        assertNull(storage.getAirports());
    }

    /**
     * Test that routes is not changed when setData is called with airlines as the datatype.
     */
    @Test
    public void setDataRouteUnchangedTest() {
        Set<DataType> testAirlines = createAirlineSet();
        storage.setData(testAirlines, "Airline");
        assertNull(storage.getRoutes());
    }

    /**
     * Test that airports is set to given values when setData is called with airports as the datatype.
     */
    @Test
    public void setDataAirportTest() {
        Set<DataType> testAirports = new HashSet<>();
        testAirports.add(new Airport(11,"Akureyri Airport","Akureyri","Iceland","AEY",
                "BIAR",65.66000366210938,-18.07270050048828,6,0,"N",
                "Atlantic/Reykjavik"));
        testAirports.add(new Airport(59,"Fort Resolution","Fort Resolution","Canada","YFR",
                "CYFR",61.180832,-113.689722,526,-7,"A",
                "America/Edmonton"));
        storage.setData(testAirports, "Airport");
        assertEquals(testAirports, storage.getAirports());
    }

    /**
     * Test that routes is set to given values when setData is called with routes as the datatype.
     */
    @Test
    public void setDataRouteTest() {
        Set<DataType> testRoutes = new HashSet<>();
        testRoutes.add(new Route("FM",4609,"CTU",3395,"SHA",
                3391,"",0,"757 737 738".split(" ")));
        testRoutes.add(new Route("MH",3378,"MYY",3266,"BTU",
                3262,"Y",0,"AT7".split(" ")));
        storage.setData(testRoutes, "Route");
        assertEquals(testRoutes, storage.getRoutes());
    }

    /**
     * Test that an exception is raised when setData is called with an incorrect datatype.
     */
    @Test
    public void setDataInvalidDataTypeTest() {
        Set<DataType> testData = new HashSet<DataType>();
        try {
            storage.setData(testData, "Potato");
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

}