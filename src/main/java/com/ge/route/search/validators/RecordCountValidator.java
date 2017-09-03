package com.ge.route.search.validators;

import com.ge.route.search.utils.ServiceConstants;
import com.ge.route.search.exceptions.InvalidDataFileException;
import com.ge.route.search.exceptions.ErrorCodes;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Vinay Fulari
 *
 * Performs a series of validation in input data file content
 *
 */
public class RecordCountValidator implements IValidator {

    @Override
    public boolean validate(List<String> records) throws IOException {

        /* Validate route count */
        final long routCount = records.stream().filter(line -> !StringUtils.isEmpty(line)).skip(1).count();
        Integer inputRouteCount = Integer.valueOf(records.get(0));
        if (routCount != inputRouteCount) {
            throw new InvalidDataFileException(ErrorCodes.ROUTE_COUNT_MISMATCH);
        }

        /* Validate whether route count exceeds supported limit*/
        if (inputRouteCount > ServiceConstants.MAX_SUPPORTED_BUS_ROUTES) {
            throw new InvalidDataFileException(ErrorCodes.MAX_ROUTES_EXCEEDED, 5);
        }

        /* Validate whether stations count exceeds supported limit*/
        final Set<Integer> uniqueStationIds = records.stream()
                .filter(line -> !StringUtils.isEmpty(line))
                .skip(1)
                .map(line -> PATTERN_WHITE_SPACE.splitAsStream(line).skip(1).map(Integer::valueOf).collect(Collectors.toList()))
                .flatMap(stationIds -> stationIds.stream())
                .collect(Collectors.toSet());

        if (uniqueStationIds.size() > ServiceConstants.MAX_SUPPORTED_UNIQUE_STATIONS_ACROSS_ALL_ROUTES) {
            throw new InvalidDataFileException(ErrorCodes.MAX_STATIONS_EXCEEDED);
        }

        /* Validate whether each route has atleast 2 stations.*/
        boolean isRouteContainsLessThanMinStations = records.stream()
                .filter(line -> !StringUtils.isEmpty(line))
                .skip(1)
                .map(line -> PATTERN_WHITE_SPACE.splitAsStream(line).map(Integer::valueOf).collect(Collectors.toList()))
                .anyMatch(routeInfo -> (routeInfo.size() < 3));

        if (isRouteContainsLessThanMinStations) {
            throw new InvalidDataFileException(ErrorCodes.INSUFFICIENT_STATIONS_PER_ROUTE);
        }

        /* Validate whether stations count exceeds supported limit per route */
        boolean isRouteCountMoreThanMaxSupported = records.stream()
                .filter(line -> !StringUtils.isEmpty(line))
                .skip(1)
                .map(line -> PATTERN_WHITE_SPACE.splitAsStream(line).skip(1).map(Integer::valueOf).collect(Collectors.toList()))
                .anyMatch(routeInfo -> (routeInfo.size() > ServiceConstants.MAX_SUPPORTED_STATIONS_PER_BUS_ROUTE));

        if (isRouteCountMoreThanMaxSupported) {
            throw new InvalidDataFileException(ErrorCodes.MAX_STATIONS_PER_ROUTE_EXCEEDED);
        }

        return true;
    }
}
