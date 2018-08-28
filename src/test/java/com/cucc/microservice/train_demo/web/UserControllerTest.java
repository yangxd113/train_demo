package com.cucc.microservice.train_demo.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Value("${base.url}")
    String baseUrl;
    @Autowired
    TestRestTemplate testRestTemplate;
    @Test
    public void users() throws Exception {
       ResponseEntity<List> responseEntity = testRestTemplate.getForEntity(baseUrl + "/users", List.class);
       assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
       assertEquals(2, responseEntity.getBody().size());
    }

}