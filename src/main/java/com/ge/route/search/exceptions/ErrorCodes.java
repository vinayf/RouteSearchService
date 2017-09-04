package com.ge.route.search.exceptions;

/**
 * Error codes used across the service, the corresponding messages are loaded from the errorcode.properties file.
 * @author Vinay Fulari
 */
public enum ErrorCodes {

    ROUTE_COUNT_MISMATCH("GE1000"),
    DUPLICATE_ROUTES("GE1001"),
    DUPLICATE_STATIONS_IN_SINGLE_ROUTE("GE1002"),
    MAX_ROUTES_EXCEEDED("GE1003"),
    MAX_STATIONS_EXCEEDED("GE1004"),
    MAX_STATIONS_PER_ROUTE_EXCEEDED("GE1005"),
    INSUFFICIENT_STATIONS_PER_ROUTE("GE1006"),
    MANDATORY_QUERY_PARAM_MISSING("GE1007"),
    MANDATORY_QUERY_PARAM_REQUIRED_NON_NULL("GE1008");

    public String getValue() {
        return value;
    }

    private String value;

    ErrorCodes(String s) {
        value = s;
    }

    public static ErrorCodes getErrorCode(final String errorCode){
        for( ErrorCodes errCode : ErrorCodes.values() ){
            if( errCode.getValue().equalsIgnoreCase( errorCode )){
                return errCode;
            }
        }
        return null;
    }
}
