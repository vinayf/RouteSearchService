package com.ge.route.search.service;

import com.ge.route.search.cache.StationGraphCache;
import com.ge.route.search.exceptions.ErrorCodes;
import com.ge.route.search.exceptions.RouteSearchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vinay Fulari
 */

@RestController
@RequestMapping("/api")
public class RouteSearchEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteSearchEndpoint.class.getName());

    @Autowired
    StationGraphCache stationNetworkCache;

    @RequestMapping(value="/direct", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody RouteSearchResponse isConnected(
                                    @RequestParam (value="dep_sid", required = true) Integer departureStationId,
                                    @RequestParam(value="arr_sid", required = true) Integer arrivalStationId){

        if(null == departureStationId || null == arrivalStationId)
            throw new RouteSearchException(ErrorCodes.MANDATORY_QUERY_PARAM_REQUIRED_NON_NULL.getValue());
        LOGGER.debug("Searching for route between "+departureStationId+" & "+arrivalStationId);
        boolean isConnected = stationNetworkCache.isDirectlyConnected(departureStationId,arrivalStationId);
        return new RouteSearchResponse(departureStationId,arrivalStationId,isConnected);
    }
}
