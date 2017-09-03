package com.ge.route.search.cache;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vinay Fulari
 *
 * A simple in-memory cache which holds the station graph.
 */
@Repository
public class StationGraphCache {

    StationGraph stationGraph = null;

    /**
     * Initializes the cache and builds a network of stations from the input data.
     * @param inputData
     */
    public void init(List<String> inputData) {
        stationGraph = new StationGraph();
        inputData.stream().skip(0).forEach(input -> cacheRouteData(input));
    }


    /**
     * Determines whether the given source & destination stations are directly connected.
     * @param sourceStation
     * @param destinationStation
     * @return
     */
    public boolean isDirectlyConnected(Integer sourceStation, Integer destinationStation) {
        return stationGraph.isDirectlyConnected(sourceStation,destinationStation);
    }

    /**
     * A helper function to build the station network
     * @param record
     */
    private void cacheRouteData(String record){
        List<Integer> ids = Arrays.stream(record.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        Integer routeId = ids.get(0);
        for (int i = 1; i < ids.size()-1; i++) {
            if (i == 1)
                stationGraph.addRoute(ids.get(i),ids.get(i+1),routeId,true);
            else
                stationGraph.addRoute(ids.get(i),ids.get(i+1),routeId,false);
        }
    }
}
