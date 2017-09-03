package com.ge.route.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

/**
 * @author Vinay Fulari
 */
@SpringBootApplication(scanBasePackages={"com.ge.route.search"})
public class RouteSearchBootstrapService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteSearchBootstrapService.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(RouteSearchBootstrapService.class, args);
    }
}
