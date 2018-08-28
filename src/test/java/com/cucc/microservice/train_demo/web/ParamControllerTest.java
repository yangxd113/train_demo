package com.cucc.microservice.train_demo.web;

import com.cucc.microservice.train_demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParamControllerTest {
    @Value("${base.url}")
    String baseUrl;
    @Autowired
    TestRestTemplate testRestTemplate;
    @Test
    public void testParams() throws Exception {
        User user = new User();
        user.setAge(26);
        user.setName("liu");
        ResponseEntity<Map> responseEntity = testRestTemplate.postForEntity(baseUrl + "/params/{pathValue}/?queryParam1={queryValue}", user, Map.class,  "iampath", "iamquery");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Map<String, Object> bodyResult = responseEntity.getBody();
        Map<String, Object> paths = (Map<String, Object>) bodyResult.get("path");
        assertEquals("iampath", paths.get("pathParam1"));
    }

}