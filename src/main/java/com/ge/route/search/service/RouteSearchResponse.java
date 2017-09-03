package com.ge.route.search.service;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Vinay Fulari
 * Search response template.
 */
public class RouteSearchResponse {

    RouteSearchResponse(Integer departureStationId, Integer arrivalStationId, Boolean directBusRouteExists){
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.directBusRouteExists = directBusRouteExists;
    }

    @JsonProperty("dep_sid")
    private Integer departureStationId;

    @JsonProperty("arr_sid")
    private Integer arrivalStationId;

    @JsonProperty("direct_bus_route")
    private Boolean directBusRouteExists;

    public Integer getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(Integer departureStationId) {
        this.departureStationId = departureStationId;
    }
}


