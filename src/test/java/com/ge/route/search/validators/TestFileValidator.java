package com.ge.route.search.validators;

import com.ge.route.search.exceptions.InvalidDataFileException;
import com.ge.route.search.exceptions.ErrorCodes;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vinay Fulari
 */
public class TestFileValidator {

    private static String dataDirPath = "src/test/resources/test-route-data/";

    @Test
    public void testRouteCountMismatch() throws IOException {
            List<String> records = Files.lines(Paths.get(dataDirPath+"GE1000")).map(line -> line.trim()).collect(Collectors.toList());
            try{
                FileValidator.validateContent(records);
            }catch (InvalidDataFileException e){
                Assert.assertEquals(e.getErrorCode(), ErrorCodes.ROUTE_COUNT_MISMATCH.getValue());
            }
    }

    @Test
    public void testDuplicateRoute() throws IOException {
        List<String> records = Files.lines(Paths.get(dataDirPath+"GE1001")).map(line -> line.trim()).collect(Collectors.toList());
        try{
            FileValidator.validateContent(records);
        }catch (InvalidDataFileException e){
            Assert.assertEquals(e.getErrorCode(), ErrorCodes.ROUTE_COUNT_MISMATCH.getValue());
        }
    }

    @Test
    public void testDuplicateStationInSingleRoute() throws IOException {
        List<String> records = Files.lines(Paths.get(dataDirPath+"GE1002")).map(line -> line.trim()).collect(Collectors.toList());
        try{
            FileValidator.validateContent(records);
        }catch (InvalidDataFileException e){
            Assert.assertEquals(e.getErrorCode(), ErrorCodes.DUPLICATE_STATIONS_IN_SINGLE_ROUTE.getValue());
        }
    }

    @Test
    public void testInsufficientStations() throws IOException {
        List<String> records = Files.lines(Paths.get(dataDirPath+"GE1006")).map(line -> line.trim()).collect(Collectors.toList());
        try{
            FileValidator.validateContent(records);
        }catch (InvalidDataFileException e){
            Assert.assertEquals(e.getErrorCode(), ErrorCodes.INSUFFICIENT_STATIONS_PER_ROUTE.getValue());
        }
    }

    @Test
    public void testMaxRoute() throws IOException {
        List<String> records = Files.lines(Paths.get(dataDirPath+"GE1003")).map(line -> line.trim()).collect(Collectors.toList());
        try{
            FileValidator.validateContent(records);
        }catch (InvalidDataFileException e){
            Assert.assertEquals(e.getErrorCode(), ErrorCodes.MAX_ROUTES_EXCEEDED.getValue());
        }
    }

    @Test
    public void testMaxStation() throws IOException {
        List<String> records = Files.lines(Paths.get(dataDirPath+"GE1004")).map(line -> line.trim()).collect(Collectors.toList());
        try{
            FileValidator.validateContent(records);
        }catch (InvalidDataFileException e){
            Assert.assertEquals(e.getErrorCode(), ErrorCodes.MAX_STATIONS_EXCEEDED.getValue());
        }
    }

    @Test
    public void testMaxStationPerRoute() throws IOException {
        List<String> records = Files.lines(Paths.get(dataDirPath+"GE1005")).map(line -> line.trim()).collect(Collectors.toList());
        try{
            FileValidator.validateContent(records);
        }catch (InvalidDataFileException e){
            Assert.assertEquals(e.getErrorCode(), ErrorCodes.MAX_STATIONS_PER_ROUTE_EXCEEDED.getValue());
        }
    }

}
