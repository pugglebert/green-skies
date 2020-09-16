package model.data;

import javafx.scene.control.CheckBox;
import java.util.Arrays;

// TODO: check all method comments start with "This method ..."

/**
 * The Route class for containing all data for one unique flight route.
 *
 * @author Hayley Krippner
 * @version 1.0
 * @since 2020-08-11
 */
public class Route implements DataType {
  // TODO: write comments for these attributes

  private final String airlineName;
  private final int airlineID;
  private final String sourceAirport;
  private final int sourceAirportID;
  private final String destinationAirport;
  private final int destinationAirportID;
  private final String codeShare;
  private final int numOfStops;
  private final String[] equipment;
  private final String firstEquipment;
  private double emissions;
  private double distance;
    private int timesTaken = 0;
  private CheckBox select;

  /** The Route constructor. */
  public Route(
      String airlineName,
      int airlineID,
      String sourceAirport,
      int sourceAirportID,
      String destinationAirport,
      int destinationAirportID,
      String codeShare,
      int numOfStops,
      String[] equipment) {
    this.airlineName = airlineName;
    this.airlineID = airlineID;
    this.sourceAirport = sourceAirport;
    this.sourceAirportID = sourceAirportID;
    this.destinationAirport = destinationAirport;
    this.destinationAirportID = destinationAirportID;
    this.codeShare = codeShare;
    this.numOfStops = numOfStops;
    this.equipment = equipment;
    if (equipment.length > 0) {
      firstEquipment = equipment[0];
    } else {
      firstEquipment = "";
    }
  }
  // TODO: write comment for this method

  public void initCheckBox() {
    this.select = new CheckBox();
  }

  /**
   * Getter for the name of the airline of that is used during the Flight.
   *
   * @return airlineID.
   */
  public String getAirlineName() {
    return airlineName;
  }

  /**
   * Getter for the ID of the airline of that is used during the Flight.
   *
   * @return airlineID.
   */
  public int getAirlineID() {
    return airlineID;
  }

  /**
   * Getter for the name of the source airport of the Flight.
   *
   * @return sourceAirport.
   */
  public String getSourceAirport() {
    return sourceAirport;
  }

  /**
   * Getter for the ID of the source airport of the Flight.
   *
   * @return sourceAirportID.
   */
  public int getSourceAirportID() {
    return sourceAirportID;
  }

  /**
   * Getter for the name of the destination airport of the Flight.
   *
   * @return destinationAirport.
   */
  public String getDestinationAirport() {
    return destinationAirport;
  }

  /**
   * Getter for the ID of the destination airport of the Flight.
   *
   * @return destinationAirportID.
   */
  public int getDestinationAirportID() {
    return destinationAirportID;
  }

  /**
   * Getter for the codeShare for the Flight.
   *
   * @return codeShare.
   */
  public String getCodeShare() {
    return codeShare;
  }

  /**
   * Getter for the number of stops the Flight has.
   *
   * @return numOfStops.
   */
  public int getNumOfStops() {
    return numOfStops;
  }

  /**
   * Getter for the equipment used during the Flight.
   *
   * @return equipment.
   */
  public String[] getEquipment() {
    return equipment;
  }

  /** Getter for the first entry in the equipment array. */
  public String getFirstEquipment() {
    return firstEquipment;
  }

  /**
   * Getter for the total carbon emissions of the route.
   *
   * @return a double for the amount of CO2 if it has been calculated, or null if it has not. yet
   *     been calculated.
   */
  public double getEmissions() {
    return emissions;
  }

  /**
   * Setter for the total carbon emissions of the route.
   *
   * @param emissions a double for the emissions calculated for that route.
   */
  public void setEmissions(double emissions) {
    this.emissions = emissions;
  }

  /**
   * Getter for the total distance of the route.
   *
   * @return null if distance has not yet been calculated, otherwise returns a double for the
   *     distance the route covers.
   */
  public double getDistance() {
    return distance;
  }

  /**
   * Set distance to the value calculated.
   *
   * @param distance a double for the distance calculated.
   */
  public void setDistance(double distance) {
    this.distance = distance;
  }

    /**
     * Get the number of times that the user has recorded taking this flight.
     */
    public int getTimesTaken() {return timesTaken;}

  /**
   * Set timesTaken to the given value.
   *
   * @param timesTaken the value to set timesTaken to.
   */
  public void setTimesTaken(int timesTaken) {
    this.timesTaken = timesTaken;
  }

  /**
   * Returns true if object has the same attributes as the Route from which the method is called,
   * false otherwise.
   *
   * @param o object to be compared to the route calling the method.
   * @return true if the two objects attributes are the same, false if there are any differences.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Route) {
      Route another = (Route) o;
      return (this.airlineName.equals(another.getAirlineName())
          && this.airlineID == another.getAirlineID()
          && this.sourceAirport.equals(another.getSourceAirport())
          && this.sourceAirportID == another.getSourceAirportID()
          && this.destinationAirport.equals(another.getDestinationAirport())
          && this.destinationAirportID == another.getDestinationAirportID()
          && this.codeShare.equals(another.getCodeShare())
          && this.numOfStops == another.getNumOfStops()
          && Arrays.equals(this.equipment, another.getEquipment()));
    } else {
      return false;
    }
  }

  // TODO: write comment for this method

  public CheckBox getSelect() {
    return select;
  }
  // TODO: write comment for this method

  public void setSelect(CheckBox select) {
    this.select = select;
  }
  // TODO: write comment for this method

  public String toString() {
    String routeString =
        "Airline name: "
            + airlineName
            + "\n"
            + "Airline ID: "
            + airlineID
            + "\n"
            + "Source Airport: "
            + sourceAirport
            + "\n"
            + "Source Airport ID: "
            + sourceAirportID
            + "\n"
            + "Destination Airport: "
            + destinationAirport
            + "\n"
            + "Destination Airport ID: "
            + destinationAirportID
            + "\n"
            + "Code Share: "
            + codeShare
            + "\n"
            + "Number of Stops: "
            + numOfStops
            + "\n"
            + "Equipment: "
            + equipment
            + "\n"
            + "First Equipment: "
            + firstEquipment
            + "\n"
            + "Carbon Emissions: "
            + emissions
            + "\n"
            + "Distance: "
            + distance
            + "\n"
            + "Times Taken: "
            + timesTaken
            + "\n";
    return routeString;
  }

  //    public static void main(String[] args){
  //        Route test = new Route("dsf", 12, "asd", 45, "asd",123,"asd",32, new String[]{"21"});
  //    }
}
