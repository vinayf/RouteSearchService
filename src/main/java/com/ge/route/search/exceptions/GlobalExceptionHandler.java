package com.ge.route.search.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vinay Fulari
 *
 * A global exception handler to intercept/wrap the exceptions across the service.
 *
 */
@ControllerAdvice
@RestController
@Component
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger( GlobalExceptionHandler.class );

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RouteSearchErrorResponse handle(MissingServletRequestParameterException exception) {

        RouteSearchErrorResponse errorResponse = new RouteSearchErrorResponse().
                setErrorCode(ErrorCodes.MANDATORY_QUERY_PARAM_MISSING.getValue()).
                setErrorMessage(ExceptionUtil.getErrorMsgByCode(ErrorCodes.MANDATORY_QUERY_PARAM_MISSING.getValue())).
                setErrorInfo(ExceptionUtil.getErrorInfo(ErrorCodes.MANDATORY_QUERY_PARAM_MISSING.getValue()));

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RouteSearchException.class)
    public RouteSearchErrorResponse handleBaseException(RouteSearchException e){
        String dsErrCode = ExceptionUtil.getErrorCode(e.getErrorCode());
        RouteSearchErrorResponse errorResponse = new RouteSearchErrorResponse().
                setErrorCode( dsErrCode ).
                setErrorMessage(ExceptionUtil.getErrorMsgByCode( dsErrCode, e.getVars() )).
                setErrorInfo(ExceptionUtil.getErrorInfo( dsErrCode ));

        return errorResponse;
    }
}
