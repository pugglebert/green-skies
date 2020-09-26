package controller.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AirportStatsCalculator extends GeneralStatsCalculator {

    private ArrayList<String> mostVisitedSrcAirports = new ArrayList<>();
    /**
     * The destination airports which were the most visited.
     */
    private ArrayList<String> mostVisitedDestAirports = new ArrayList<>();
    /**
     * The routes which were most travelled.
     */
    private ArrayList<String> leastVisitedSrcAirports = new ArrayList<>();
    /**
     * The destination airports which were the most visited.
     */
    private ArrayList<String> leastVisitedDestAirports = new ArrayList<>();

    /**
     * Calculates the source airport(s) that was the most visited, based on the user's flight history
     * entries.
     *
     * @param srcAirportCounts A count of the number of times each source airport has been visited
     *     with the name of the aiport as the key.
     */
    public void updateMostVisitedSrcAirports(HashMap<String, Integer> srcAirportCounts) {
        int currSrcAirportMax = 0;
        for (Map.Entry<String, Integer> entry : srcAirportCounts.entrySet()) {
            if (entry.getValue() > currSrcAirportMax) {
                currSrcAirportMax = entry.getValue();
                mostVisitedSrcAirports.clear();
                mostVisitedSrcAirports.add(entry.getKey());
            } else if (entry.getValue() == currSrcAirportMax) {
                currSrcAirportMax = entry.getValue();
                mostVisitedSrcAirports.add(entry.getKey());
            }
        }
    }

    /**
     * Calculates the destionation airport(s) that was the most visited, based on the user's flight
     * history entries.
     *
     * @param destAirportCounts A count of how many times each destination airport has been visited
     *     with the names as the key.
     */
    public void updateMostVisitedDestAirports(HashMap<String, Integer> destAirportCounts) {
        int currDestAirportMax = 0;
        for (Map.Entry<String, Integer> entry : destAirportCounts.entrySet()) {
            if (entry.getValue() > currDestAirportMax) {
                currDestAirportMax = entry.getValue();
                mostVisitedDestAirports.clear();
                mostVisitedDestAirports.add(entry.getKey());
            } else if (entry.getValue() == currDestAirportMax) {
                currDestAirportMax = entry.getValue();
                mostVisitedDestAirports.add(entry.getKey());
            }
        }
    }

    /**
     * Calculates the source airport(s) that was the least visited, based on the user's flight history
     * entries.
     *
     * @param srcAirportCounts A count of how many times each airport has been visited with the name
     *     of the airport as the key.
     */
    public void updateLeastVisitedSrcAirports(HashMap<String, Integer> srcAirportCounts) {
        int currSrcAirportMax = 1;
        for (Map.Entry<String, Integer> entry : srcAirportCounts.entrySet()) {
            if (entry.getValue() < currSrcAirportMax) {
                currSrcAirportMax = entry.getValue();
                leastVisitedSrcAirports.clear();
                leastVisitedSrcAirports.add(entry.getKey());
            } else if (entry.getValue() == currSrcAirportMax) {
                currSrcAirportMax = entry.getValue();
                leastVisitedSrcAirports.add(entry.getKey());
            }
        }
    }

    /**
     * Calculates the destination airport(s) that was the least visited, based on the user's flight
     * history entries.
     *
     * @param destAirportCounts A count of how many times each destination airport has been visited
     *     with the name of the airport as the key.
     */
    public void updateLeastVisitedDestAirports(HashMap<String, Integer> destAirportCounts) {
        int currDestAirportMax = 1;
        for (Map.Entry<String, Integer> entry : destAirportCounts.entrySet()) {
            if (entry.getValue() < currDestAirportMax) {
                currDestAirportMax = entry.getValue();
                leastVisitedDestAirports.clear();
                leastVisitedDestAirports.add(entry.getKey());
            } else if (entry.getValue() == currDestAirportMax) {
                currDestAirportMax = entry.getValue();
                leastVisitedDestAirports.add(entry.getKey());
            }
        }
    }

    /**
     * This method empties all the airport arrays.
     */
    public void resetAirportArrays() {
        mostVisitedDestAirports.clear();
        mostVisitedSrcAirports.clear();
        leastVisitedSrcAirports.clear();
        leastVisitedDestAirports.clear();
    }

    public ArrayList<String> getMostVisitedSrcAirports() {
        return mostVisitedSrcAirports;
    }

    public ArrayList<String> getMostVisitedDestAirports() {
        return mostVisitedDestAirports;
    }

    public ArrayList<String> getLeastVisitedSrcAirports() {
        return leastVisitedSrcAirports;
    }

    public ArrayList<String> getLeastVisitedDestAirports() {
        return leastVisitedDestAirports;
    }

}
