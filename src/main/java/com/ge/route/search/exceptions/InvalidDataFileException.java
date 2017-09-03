package com.ge.route.search.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vinay Fulari
 */
public class InvalidDataFileException extends RouteSearchException{

    private static final Logger log = LoggerFactory.getLogger(InvalidDataFileException.class.getName());

    public InvalidDataFileException(ErrorCodes errCodeEnum, Object ... vars) {
        super(errCodeEnum.getValue(), vars);
    }
}
