package model.data;

import javafx.scene.control.CheckBox;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class AirlineTest extends ApplicationTest {

  Airline airline;

  @Before
  public void setUp() throws Exception {
    // given
    airline =
            new Airline(
                    2, "Private flight", "\\N", "-", "N/A", "", "", true);
  }

  @Test
  public void getAirlineIDTest() throws NoSuchFieldException, IllegalAccessException {
    //when
    int airlineID = airline.getAirlineID();
    //then
    final Field field = airline.getClass().getDeclaredField("airlineID");
    field.setAccessible(true);
    assertEquals(field.get(airline), 2);
  }

  @Test
  public void getNameTest() throws NoSuchFieldException, IllegalAccessException {
    //when
    int airlineID = airline.getAirlineID();
    //then
    final Field field = airline.getClass().getDeclaredField("name");
    field.setAccessible(true);
    assertEquals(field.get(airline), "Private flight");
  }

  @Test
  public void getAirlineAliasTest() throws NoSuchFieldException, IllegalAccessException {
    //when
    int airlineID = airline.getAirlineID();
    //then
    final Field field = airline.getClass().getDeclaredField("alias");
    field.setAccessible(true);
    assertEquals(field.get(airline), "\\N");
  }

  @Test
  public void getIATATest() throws NoSuchFieldException, IllegalAccessException {
    //when
    int airlineID = airline.getAirlineID();
    //then
    final Field field = airline.getClass().getDeclaredField("IATA");
    field.setAccessible(true);
    assertEquals(field.get(airline), "-");
  }

  @Test
  public void getICAOTest() throws NoSuchFieldException, IllegalAccessException {
    //when
    int airlineID = airline.getAirlineID();
    //then
    final Field field = airline.getClass().getDeclaredField("ICAO");
    field.setAccessible(true);
    assertEquals(field.get(airline), "N/A");
  }

  @Test
  public void getCallsignTest() throws NoSuchFieldException, IllegalAccessException {
    //when
    int airlineID = airline.getAirlineID();
    //then
    final Field field = airline.getClass().getDeclaredField("callsign");
    field.setAccessible(true);
    assertEquals(field.get(airline), "");
  }

  @Test
  public void getCountryTest() throws NoSuchFieldException, IllegalAccessException {
    //when
    int airlineID = airline.getAirlineID();
    //then
    final Field field = airline.getClass().getDeclaredField("country");
    field.setAccessible(true);
    assertEquals(field.get(airline), "");
  }

  @Test
  public void getActiveStatusTest() throws NoSuchFieldException, IllegalAccessException {
    //when
    int airlineID = airline.getAirlineID();
    //then
    final Field field = airline.getClass().getDeclaredField("activeStatus");
    field.setAccessible(true);
    assertEquals(field.get(airline), true);
  }

  @Test
  public void getSelectTest() throws NoSuchFieldException, IllegalAccessException {
    //when
    airline.initCheckBox();
    //then
    final Field field = airline.getClass().getDeclaredField("select");
    field.setAccessible(true);
    assertEquals(((CheckBox) field.get(airline)).isSelected(), true);
  }

  @Test
  public void setSelectTest() throws NoSuchFieldException, IllegalAccessException {
  }

  @Test
  public void testEqualsTest() throws NoSuchFieldException, IllegalAccessException {
  }
}
