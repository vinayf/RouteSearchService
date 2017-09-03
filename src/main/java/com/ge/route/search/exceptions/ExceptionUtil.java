package com.ge.route.search.exceptions;

import com.ge.route.search.utils.ServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * Util class which loads errorcode.properties
 * @author Vinay Fulari
 */
public class ExceptionUtil {

    private static final Logger log = LoggerFactory.getLogger( ExceptionUtil.class );

    private static Properties errorMsgProperties = new Properties();
    public static final String DEFAULT_ERR_CODE = "GE5000";
    static {
        try (InputStream stream = ExceptionUtil.class.getResourceAsStream(ServiceConstants.ERROR_CODE_PROPERTIES_FILE)){
            errorMsgProperties.load(stream);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RouteSearchException("Error while loading error codes", e);
        }
    }


    public static String getErrorMsgByCode(String code, Object ... variables) {
        return StringUtils.isEmpty(errorMsgProperties.getProperty(code)) ?
                errorMsgProperties.getProperty(DEFAULT_ERR_CODE) :
                MessageFormat.format(errorMsgProperties.getProperty(code), variables);
    }

    public static String getErrorCode(String code){
        if(StringUtils.isEmpty( code )){
            return DEFAULT_ERR_CODE;
        }
        return errorMsgProperties.containsKey( code ) ? code: DEFAULT_ERR_CODE;
    }

    public static String getErrorInfo(String errorCode) {
        return "http://ge.com/apidocs/#"+errorCode;
    }

}
