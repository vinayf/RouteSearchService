package com.ge.route.search.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vinay Fulari
 *
 * A simple implementation of an undirected graph of station nodes, a more sophisticated graph implmentation could be used to support various
 * different search queries like to search all available paths, shortest path, paths with less connecting stops etc.
 *
 *
 */
public class StationGraph {

    //look up map to determine the starting station for a particular route in the network of stations.
    private Map<Integer,Station> routeSourceLookupMap = new HashMap<Integer, Station>();

    //a quick look up map to access a particular station
    private Map<Integer,Station> stationLookupMap = new HashMap<Integer, Station>();

    /**
     *  Adds a new station to the network since it is an undirected graph a two way connection is maintained.
     * @param sourceStationId
     * @param destinationStationId
     * @param routeId
     * @param isStartingPoint
     */
    public void addRoute(int sourceStationId, int destinationStationId, int routeId, boolean isStartingPoint){

        Station sourceStation = stationLookupMap.get(sourceStationId);
        Station destinationStation = stationLookupMap.get(destinationStationId);

        if(null == sourceStation){
            sourceStation = new Station(sourceStationId, routeId);
            stationLookupMap.put(sourceStationId,sourceStation);
        }
        if(null == destinationStation){
            destinationStation = new Station(destinationStationId, routeId);
            stationLookupMap.put(destinationStationId,destinationStation);
        }
        if (isStartingPoint){
            routeSourceLookupMap.put(routeId, sourceStation);
        }

        stationLookupMap.get(sourceStationId).connectStation(destinationStationId);
        stationLookupMap.get(destinationStationId).connectStation(sourceStationId);
    }

    /**
     * Determines whether the given source & destination stations are directly connected.
     * @param sourceStation
     * @param destinationStation
     * @return
     */
    public boolean isDirectlyConnected(Integer sourceStation, Integer destinationStation){
        if(null == sourceStation || !stationLookupMap.containsKey(sourceStation) || !stationLookupMap.containsKey(destinationStation)){
            return false;
        }

        if (stationLookupMap.get(sourceStation).isConnected(destinationStation)){
            return true;
        }else{
            return false;
        }
    }
}




