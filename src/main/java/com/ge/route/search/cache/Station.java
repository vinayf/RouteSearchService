package com.ge.route.search.cache;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Vinay Fulari
 */
class Station {

    private int stationId;
    private int routeId;
    private List<Integer> adjacentStations = new LinkedList<Integer>();

    Station(int stationId, int routeId){
        this.stationId = stationId;
        this.routeId = routeId;
    }

    public void connectStation(Integer stationId) {
        this.adjacentStations.add(stationId);
    }

    public boolean isConnected(Integer station){
        return this.adjacentStations.contains(station);
    }

    public int getStationId() {
        return stationId;
    }

    public int getRouteId() {
        return routeId;
    }
}