package controller.analysis;

import model.data.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteStatsCalculator extends GeneralStatsCalculator {

    /**
     * The routes which produce the most emissions.
     */
    private ArrayList<Route> mostEmissionsRoutes = new ArrayList<>();
    /**
     * The routes which produce the least emissions.
     */
    private ArrayList<Route> leastEmissionsRoutes = new ArrayList<>();
    /**
     * The routes which are of the most distance.
     */
    private ArrayList<Route> mostDistanceRoutes = new ArrayList<>();
    /**
     * The routes which are of the least distance.
     */
    private ArrayList<Route> leastDistanceRoutes = new ArrayList<>();
    /**
     * The source airports which were the most visited.
     */
    /**
     * The routes which were most travelled.
     */
    private ArrayList<Route> mostTravelledRoutes = new ArrayList<>();
    /**
     * The routes which were the least travelled.
     */
    private ArrayList<Route> leastTravelledRoutes = new ArrayList<>();

    /**
     * This method updates the current most travelled flight route.
     *
     * @param routeHistoryEntries , the current route record that is being added to user's flight
     *     history.
     */
    public void updateMostTravelledRoute(List<Route> routeHistoryEntries) {
        if (routeHistoryEntries.size() >= 1) {
            if (routeHistoryEntries.size() == 1) {
                mostTravelledRoutes.add(routeHistoryEntries.get(0));
            } else {
                quickSort(routeHistoryEntries, 0, routeHistoryEntries.size() - 1);
                int maxRouteCounter =
                        routeHistoryEntries.get(routeHistoryEntries.size() - 1).getTimesTaken();
                int firstOccuranceIndex = binarySearch(routeHistoryEntries, maxRouteCounter);
                for (int i = firstOccuranceIndex; i < routeHistoryEntries.size(); i++) {
                    mostTravelledRoutes.add(routeHistoryEntries.get(i));
                }
            }
        }
    }

    /**
     * This method updates the current least travelled flight route, provided the route is already in
     * the flight history.
     *
     * @param routeHistoryEntries, the current route record that is being added to user's flight
     *     history.
     */
    public void updateLeastTravelledRoute(List<Route> routeHistoryEntries) {
        if (routeHistoryEntries.size() >= 1) {
            if (routeHistoryEntries.size() == 1) {
                leastTravelledRoutes.add(routeHistoryEntries.get(0));
            } else {
                quickSort(routeHistoryEntries, 0, routeHistoryEntries.size() - 1);
                int minRouteCounter = routeHistoryEntries.get(0).getTimesTaken();
                int firstOccuranceIndex = binarySearch(routeHistoryEntries, minRouteCounter);
                for (int i = 0; i < firstOccuranceIndex + 1; i++) {
                    leastTravelledRoutes.add(routeHistoryEntries.get(i));
                }
            }
        }
    }

    /**
     * This method updates the which route produces the most emissions.
     *
     * @param currentRouteRecord
     */
    public void updateMostEmissionsRoute(Route currentRouteRecord) {
        if (currentRouteRecord.getEmissions() > 0.0) {
            if (this.mostEmissionsRoutes.size() == 0) {
                mostEmissionsRoutes.add(currentRouteRecord);
            } else if (currentRouteRecord.getEmissions() > mostEmissionsRoutes.get(0).getEmissions()) {
                mostEmissionsRoutes.clear();
                mostEmissionsRoutes.add(currentRouteRecord);
            } else if (currentRouteRecord.getEmissions() == mostEmissionsRoutes.get(0).getEmissions()) {
                Boolean found = false;
                for (Route route : mostEmissionsRoutes) {
                    if (route.getAirlineID() == currentRouteRecord.getAirlineID()) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    mostEmissionsRoutes.add(currentRouteRecord);
                }
            }
        }
    }

    /**
     * This method updates the which route produces the least emissions. The currentRouteRecord is
     * checked against the current route with the least emissions so far.
     *
     * @param currentRouteRecord
     */
    public void updateLeastEmissionsRoute(Route currentRouteRecord) {
        if (currentRouteRecord.getEmissions() > 0.0) {
            if (leastEmissionsRoutes.size() == 0) {
                leastEmissionsRoutes.add(currentRouteRecord);
            } else if (currentRouteRecord.getEmissions() < leastEmissionsRoutes.get(0).getEmissions()) {
                leastEmissionsRoutes.clear();
                leastEmissionsRoutes.add(currentRouteRecord);
            } else if (currentRouteRecord.getEmissions() == leastEmissionsRoutes.get(0).getEmissions()) {
                Boolean found = false;
                for (Route route : leastEmissionsRoutes) {
                    if (route.getAirlineID() == currentRouteRecord.getAirlineID()) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    leastEmissionsRoutes.add(currentRouteRecord);
                }
            }
        }
    }

    /**
     * This method updates which route is of the greatest distance. The currentRouteRecord is checked
     * against the current route with the most emissions so far.
     *
     * @param currentRouteRecord
     */
    public void updateMostDistanceRoute(Route currentRouteRecord) {
        if (currentRouteRecord.getDistance() > 0.0) {
            if (mostDistanceRoutes.isEmpty() == true) {
                mostDistanceRoutes.add(currentRouteRecord);
            } else if (currentRouteRecord.getDistance() > mostDistanceRoutes.get(0).getDistance()) {
                mostDistanceRoutes.clear();
                mostDistanceRoutes.add(currentRouteRecord);
            } else if (currentRouteRecord.getDistance() == mostDistanceRoutes.get(0).getDistance()) {
                Boolean found = false;
                for (Route route : mostDistanceRoutes) {
                    if (route.getAirlineID() == currentRouteRecord.getAirlineID()) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    mostDistanceRoutes.add(currentRouteRecord);
                }
            }
        }
    }

    /**
     * This method updates which route is of the least distance. The currentRouteRecord is checked
     * against the current route with the least emissions so far.
     *
     * @param currentRouteRecord
     */
    public void updateLeastDistanceRoute(Route currentRouteRecord) {
        if (currentRouteRecord.getDistance() > 0.0) {

            if (leastDistanceRoutes.isEmpty() == true) {
                leastDistanceRoutes.add(currentRouteRecord);
            } else if (currentRouteRecord.getDistance() < leastDistanceRoutes.get(0).getDistance()) {
                leastDistanceRoutes.clear();
                leastDistanceRoutes.add(currentRouteRecord);
            } else if (currentRouteRecord.getDistance() == leastDistanceRoutes.get(0).getDistance()) {
                Boolean found = false;
                for (Route route : leastDistanceRoutes) {
                    if (route.getAirlineID() == currentRouteRecord.getAirlineID()) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    leastDistanceRoutes.add(currentRouteRecord);
                }
            }
        }
    }

    /**
     * This method empties all the route arrays.
     */
    public void resetRoutesArrays() {
        leastTravelledRoutes.clear();
        mostTravelledRoutes.clear();
        mostEmissionsRoutes.clear();
        leastEmissionsRoutes.clear();
        mostDistanceRoutes.clear();
        leastDistanceRoutes.clear();
    }

    public ArrayList<Route> getMostEmissionsRoutes() {
        return mostEmissionsRoutes;
    }

    public ArrayList<Route> getLeastEmissionsRoutes() {
        return leastEmissionsRoutes;
    }

    public ArrayList<Route> getMostDistanceRoutes() {
        return mostDistanceRoutes;
    }

    public ArrayList<Route> getLeastDistanceRoutes() {
        return leastDistanceRoutes;
    }

    public ArrayList<Route> getMostTravelledRoutes() {
        return mostTravelledRoutes;
    }

    public ArrayList<Route> getLeastTravelledRoutes() {
        return leastTravelledRoutes;
    }

}