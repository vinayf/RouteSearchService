package com.ge.route.search;

import com.ge.route.search.cache.StationGraphCache;
import com.ge.route.search.validators.FileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vinay Fulari
 *
 * Performs input validation and initializes the cache during boot up.
 */
@Component
public class AppRunner implements CommandLineRunner{

    private static final Logger LOGGER = LoggerFactory.getLogger(AppRunner.class.getName());

    @Autowired
    private StationGraphCache stationNetworkCache;

    /**
     *
     * @param strings
     * @throws Exception
     */
    @Override
    public void run(String... strings) throws Exception {
        if (strings.length==0)
            throw new FileNotFoundException("Please provide data input file path as argument to the service.");
        String dataFilePath = strings[0];
        List<String> records = null;
        if (FileValidator.validateFileMeta(dataFilePath)){
            records = Files.lines(Paths.get(dataFilePath)).map(line -> line.trim()).collect(Collectors.toList());
            FileValidator.validateContent(records);
        }

        stationNetworkCache.init(records);
    }
}
