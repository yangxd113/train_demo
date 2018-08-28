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

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;
    @Value("${base.url}")
    String baseUrl;
    @Test
    public void sayHello() throws Exception {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(baseUrl + "/hello", String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Hello World!", responseEntity.getBody());
    }

}