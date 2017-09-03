package com.ge.route.search.exceptions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vinay Fulari
 *
 * A global error response template
 */
public class RouteSearchErrorResponse {

    private static Logger log = LoggerFactory.getLogger( RouteSearchErrorResponse.class );
    private static final String DEFAULT_ERROR_RESPONSE = "{\"code\":\"GE5000\",\"message\":\"Unknown Error\"}";

    public RouteSearchErrorResponse() {}

    public RouteSearchErrorResponse setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public RouteSearchErrorResponse setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public RouteSearchErrorResponse setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
        return this;
    }

    public RouteSearchErrorResponse createException(){
        return this;
    }

    @JsonProperty("code")
    public String getErrorCode() {
        return errorCode;
    }

    @JsonProperty("message")
    public String getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("info")
    public String getErrorInfo() {
        return errorInfo;
    }

    public String print() {
        try {
            return new ObjectMapper().
                    setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.ANY)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("Error while preparing the error response JSON", e);
        }
        return DEFAULT_ERROR_RESPONSE;
    }

    private String errorCode;
    private String errorMessage;
    private String errorInfo;

}
