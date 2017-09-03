package com.ge.route.search.service;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.Map;

import com.ge.route.search.exceptions.ErrorCodes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Vinay Fulari
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = com.ge.route.search.RouteSearchBootstrapService.class)
public class TestRouteSearchEndpoint {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testDirectlyConnectedRoutes() {
        final String endpoint = String.format("http://localhost:%s/api/direct?dep_sid=1&arr_sid=2", port);
        final ResponseEntity<Map> response = testRestTemplate.getForEntity(endpoint, Map.class);

        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        then(response.getBody().get("dep_sid")).isEqualTo(1);
        then(response.getBody().get("arr_sid")).isEqualTo(2);
        then(response.getBody().get("direct_bus_route")).isEqualTo(true);
    }

    @Test
    public void testNotDirectlyConnectedRoutes() {
        final String endpoint = String.format("http://localhost:%s/api/direct?dep_sid=1&arr_sid=4", port);
        final ResponseEntity<Map> response = testRestTemplate.getForEntity(endpoint, Map.class);

        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        then(response.getBody().get("dep_sid")).isEqualTo(1);
        then(response.getBody().get("arr_sid")).isEqualTo(4);
        then(response.getBody().get("direct_bus_route")).isEqualTo(false);
    }

    @Test
    public void testNoDepQueryParam() {
        final String endpoint = String.format("http://localhost:%s/api/direct?arr_sid=4", port);
        final ResponseEntity<Map> response = testRestTemplate.getForEntity(endpoint, Map.class);

        then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        then(response.getBody().get("code")).isEqualTo(ErrorCodes.MANDATORY_QUERY_PARAM_MISSING.getValue());
    }

    @Test
    public void testNoArrQueryParam() {
        final String endpoint = String.format("http://localhost:%s/api/direct?dep_sid=1", port);
        final ResponseEntity<Map> response = testRestTemplate.getForEntity(endpoint, Map.class);

        then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        then(response.getBody().get("code")).isEqualTo(ErrorCodes.MANDATORY_QUERY_PARAM_MISSING.getValue());
    }

    @Test
    public void testNoQueryParams() {
        final String endpoint = String.format("http://localhost:%s/api/direct", port);
        final ResponseEntity<Map> response = testRestTemplate.getForEntity(endpoint, Map.class);

        then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        then(response.getBody().get("code")).isEqualTo(ErrorCodes.MANDATORY_QUERY_PARAM_MISSING.getValue());
    }
}
