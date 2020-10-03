package model.data;

import javafx.scene.control.CheckBox;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class AirportTest extends ApplicationTest {

  private Airport airport;

  @Before
  public void setUp() {
    airport =
            new Airport(
                    1,
                    "Goroka",
                    "Goroka",
                    "Papua New Guinea",
                    "GKA",
                    "AYGA",
                    -6.081689,
                    145.391881,
                    5282,
                    10,
                    "U",
                    "Port_Moresby");
    airport.initCheckBox();
  }

  @Test
  public void getAirportID() throws NoSuchFieldException, IllegalAccessException {
    final Field field = airport.getClass().getDeclaredField("airportID");
    field.setAccessible(true);
    assertEquals(field.get(airport), 1);
  }

  @Test
  public void getName() throws NoSuchFieldException, IllegalAccessException {
    final Field field = airport.getClass().getDeclaredField("name");
    field.setAccessible(true);
    assertEquals(field.get(airport), "Goroka");
  }

  @Test
  public void getCity() throws NoSuchFieldException, IllegalAccessException {
    final Field field = airport.getClass().getDeclaredField("city");
    field.setAccessible(true);
    assertEquals(field.get(airport), "Goroka");
  }

  @Test
  public void getCountry() throws NoSuchFieldException, IllegalAccessException {
    final Field field = airport.getClass().getDeclaredField("country");
    field.setAccessible(true);
    assertEquals(field.get(airport), "Papua New Guinea");
  }

  @Test
  public void getIATA() throws NoSuchFieldException, IllegalAccessException {
    final Field field = airport.getClass().getDeclaredField("IATA");
    field.setAccessible(true);
    assertEquals(field.get(airport), "GKA");
  }

  @Test
  public void getICAO() throws NoSuchFieldException, IllegalAccessException {
    final Field field = airport.getClass().getDeclaredField("ICAO");
    field.setAccessible(true);
    assertEquals(field.get(airport), "AYGA");
  }

  @Test
  public void getLatitude() throws NoSuchFieldException, IllegalAccessException {
    final Field field = airport.getClass().getDeclaredField("latitude");
    field.setAccessible(true);
    assertEquals(field.get(airport), -6.081689);
  }

  @Test
  public void getLongitude() throws NoSuchFieldException, IllegalAccessException {
    final Field field = airport.getClass().getDeclaredField("longitude");
    field.setAccessible(true);
    assertEquals(field.get(airport), 145.391881);
  }

  @Test
  public void getAltitude() throws NoSuchFieldException, IllegalAccessException {
    final Field field = airport.getClass().getDeclaredField("altitude");
    field.setAccessible(true);
    assertEquals(field.get(airport), 5282);
  }

  @Test
  public void getTimezone() throws NoSuchFieldException, IllegalAccessException {
    final Field field = airport.getClass().getDeclaredField("timezone");
    field.setAccessible(true);
    assertEquals(field.get(airport), 10.0);
  }

  @Test
  public void getDST() throws NoSuchFieldException, IllegalAccessException {
    final Field field = airport.getClass().getDeclaredField("DST");
    field.setAccessible(true);
    assertEquals(field.get(airport), "U");
  }

  @Test
  public void getDataBaseTimeZone() throws NoSuchFieldException, IllegalAccessException {
    final Field field = airport.getClass().getDeclaredField("dataBaseTimeZone");
    field.setAccessible(true);
    assertEquals(field.get(airport), "Port_Moresby");
  }

  @Test
  public void getSelect() throws NoSuchFieldException, IllegalAccessException {
    final Field field = airport.getClass().getDeclaredField("select");
    field.setAccessible(true);
    assertEquals(((CheckBox) field.get(airport)).isSelected(), airport.getSelect().isSelected());
  }

  @Test
  public void setSelect() throws NoSuchFieldException, IllegalAccessException {
    // when
    CheckBox checkBox = new CheckBox();
    checkBox.setSelected(true);
    airport.setSelect(checkBox);
    // then
    final Field field = airport.getClass().getDeclaredField("select");
    field.setAccessible(true);
    assertTrue(((CheckBox) field.get(airport)).isSelected());
  }

  @Test
  public void testEquals() {
    Airline airline = new Airline(2, "Private flight", "\\N", "-", "N/A", "", "", true);
    Airport airport1 =
            new Airport(
                    1,
                    "Goroka",
                    "Goroka",
                    "Papua New Guinea",
                    "GKA",
                    "AYGA",
                    -6.081689,
                    145.391881,
                    5282,
                    10,
                    "U",
                    "Port_Moresby");
    assertEquals(airport, airport1);
    assertNotEquals(airport, airline);
  }
}
