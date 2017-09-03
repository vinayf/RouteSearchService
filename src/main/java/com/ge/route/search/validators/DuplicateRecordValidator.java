package com.ge.route.search.validators;

import com.ge.route.search.exceptions.InvalidDataFileException;
import com.ge.route.search.exceptions.ErrorCodes;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Vinay Fulari
 * Performs a series of validation to detect duplicate records.
 */
public class DuplicateRecordValidator implements IValidator {

    @Override
    public boolean validate(List<String> records) throws IOException {

        Set<Integer> uniqueRoutes = records.stream()
                .filter(line -> !StringUtils.isEmpty(line))
                .skip(1)
                .map(line -> PATTERN_WHITE_SPACE.split(line)[0])
                .map(Integer::valueOf)
                .collect(Collectors.toSet());

        if (uniqueRoutes.size() != Integer.valueOf(records.get(0))) {
            throw new InvalidDataFileException(ErrorCodes.ROUTE_COUNT_MISMATCH);
        }

        boolean isDuplicateStationPresent = records.stream()
                .filter(line -> !StringUtils.isEmpty(line))
                .skip(1)
                .map(line -> PATTERN_WHITE_SPACE.splitAsStream(line).skip(1).map(Integer::valueOf).collect(Collectors.toList()))
                .anyMatch(stationIds -> (stationIds.size() != (new HashSet<Integer>(stationIds)).size()));

        if (isDuplicateStationPresent) {
            throw new InvalidDataFileException(ErrorCodes.DUPLICATE_STATIONS_IN_SINGLE_ROUTE);
        }
        return false;
    }
}
