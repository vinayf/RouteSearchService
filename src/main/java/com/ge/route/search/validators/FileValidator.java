package com.ge.route.search.validators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vinay Fulari
 * Performs a series of file level validations.
 */
public class FileValidator {

    private static List<IValidator> validators;

    /*
    Loading the available validators
     */
    static {
        validators = new ArrayList<>();
        validators.add(new DuplicateRecordValidator());
        validators.add(new RecordCountValidator());
    }

    /**
     *  Validates file and access levels.
     * @param dataFilePath
     * @return
     * @throws IOException
     */
    public static boolean validateFileMeta(String dataFilePath) throws IOException{
        final File inputDataFile = new File(dataFilePath);

        if (!inputDataFile.exists() || inputDataFile.isDirectory()) {
            throw new FileNotFoundException("File not found at location "+inputDataFile.getCanonicalPath());
        }

        if (!inputDataFile.canRead()) {
            throw new SecurityException("File not readable at location "+inputDataFile.getCanonicalPath());
        }
        return true;
    }

    /**
     * Runs all the registered validators to validate the content of input data file.
     * @param records
     * @throws IOException
     */
    public static void validateContent(List<String> records) throws IOException {
        for (IValidator validator : validators){
            validator.validate(records);
        }
    }


}
