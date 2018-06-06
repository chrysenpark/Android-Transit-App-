package ca.ubc.cs.cpsc210.translink.model;

import ca.ubc.cs.cpsc210.translink.model.exception.StopException;
import ca.ubc.cs.cpsc210.translink.util.LatLon;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static ca.ubc.cs.cpsc210.translink.util.SphericalGeometry.distanceBetween;

/**
 * Manages all bus stops.
 *
 * Singleton pattern applied to ensure only a single instance of this class that
 * is globally accessible throughout application.
 */
// TODO: Task 2: Complete all the methods of this class

public class StopManager implements Iterable<Stop> {
    public static final int RADIUS = 10000;
    private static StopManager instance;
    // Use this field to hold all of the stops.
    // Do not change this field or its type, as the iterator method depends on it
    private Map<Integer, Stop> stopMap;
    private Stop selectedStop;

    /**
     * Constructs stop manager with empty collection of stops and null as the selected stop
     */
    private StopManager() {
        stopMap = new HashMap<>();
        selectedStop = null;

    }

    /**
     * Gets one and only instance of this class
     *
     * @return instance of class
     */
    public static StopManager getInstance() {
        // Do not modify the implementation of this method!
        if (instance == null) {
            instance = new StopManager();
        }

        return instance;
    }

    public Stop getSelected() {
        return selectedStop;
    }

    /**
     * Get stop with given number, creating it and adding it to the collection of all stops if necessary.
     * If it is necessary to create a new stop, then provide it with an empty string as its name,
     * and a default location somewhere in the lower mainland as its location.
     * <p>
     * In this case, the correct name and location of the stop will be provided later
     *
     * @param number the number of this stop
     * @return stop with given number
     */
    public Stop getStopWithNumber(int number) {
        if (stopMap.containsKey(number)) {
            return stopMap.get(number);
        }
        Stop s1 = new Stop(number, "", new LatLon(10, 10));
        stopMap.put(number, s1);
        return s1;
    }

    /**
     * Get stop with given number, creating it and adding it to the collection of all stops if necessary,
     * using the given name and location
     *
     * @param number the number of this stop
     * @param name   the name of this stop
     * @param locn   the location of this stop
     * @return stop with given number
     */
    public Stop getStopWithNumber(int number, String name, LatLon locn) {
        if (stopMap.containsKey(number)) {
            return stopMap.get(number);
        }
        Stop s1 = new Stop(number, name, locn);
        stopMap.put(number, s1);
        return s1;
    }

    /**
     * Set the stop selected by user
     *
     * @param selected stop selected by user
     * @throws StopException when stop manager doesn't contain selected stop
     */
    public void setSelected(Stop selected) throws StopException {
        if (!stopMap.containsKey(selected.getNumber())) {
            throw new StopException("No such stop: " + selected.getNumber() + " " + selected.getName());
        } else {
            this.selectedStop = selected;
        }
    }

    /**
     * Clear selected stop (selected stop is null)
     */
    public void clearSelectedStop() {
        selectedStop = null;

    }

    /**
     * Get number of stops managed
     *
     * @return number of stops added to manager
     */
    public int getNumStops() {
        return stopMap.size();
    }

    /**
     * Remove all stops from stop manager
     */
    public void clearStops() {
        stopMap.clear();

    }

    /**
     * Find nearest stop to given point.  Returns null if no stop is closer than RADIUS metres.
     *
     * @param pt point to which nearest stop is sought
     * @return stop closest to pt but less than RADIUS away; null if no stop is within RADIUS metres of pt
     */
    public Stop findNearestTo(LatLon pt) {
        if (stopMap.isEmpty()) {
            return null;
        } else {
            Stop closestSoFar = null;
            for (Stop stop : this) {
                double distanceToStop = distanceBetween(pt, stop.getLocn());
                if(distanceToStop <= RADIUS)
                    if (closestSoFar == null) {
                        closestSoFar = stop;
                    } else {
                        double distanceToNearest = (distanceBetween(pt, closestSoFar.getLocn()));
                        if (distanceToStop < distanceToNearest) {
                            closestSoFar = stop;
                        }
                    }

            }
            return closestSoFar;
        }
    }


    @Override
    public Iterator<Stop> iterator() {
        // Do not modify the implementation of this method!
        return stopMap.values().iterator();
    }
}
