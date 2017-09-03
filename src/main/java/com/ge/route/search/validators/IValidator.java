package com.ge.route.search.validators;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Vinay Fulari
 */
public interface IValidator {
    Pattern PATTERN_WHITE_SPACE = Pattern.compile("\\s+");
    boolean validate(List<String> records) throws IOException;
}
