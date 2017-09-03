package com.ge.route.search.exceptions;

/**
 * @author Vinay Fulari
 */
public class RouteSearchException extends RuntimeException {

    private String errorCode;
    private Object[] vars;

    public RouteSearchException() { }

    public RouteSearchException(String errorCode, Object... variables) {
        super( errorCode );
        this.errorCode = errorCode;
        this.vars = variables;
    }

    public RouteSearchException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = message;
    }

    public RouteSearchException(Throwable cause) {
        super(cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object[] getVars() {
        return vars;
    }

    public void setVars(Object[] vars) {
        this.vars = vars;
    }
}
