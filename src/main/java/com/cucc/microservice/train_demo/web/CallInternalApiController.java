package com.cucc.microservice.train_demo.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/train/demo")
public class CallInternalApiController {

    RestTemplate restTemplate = new RestTemplate();


    @RequestMapping(
            value = "/calltest",
            method = RequestMethod.GET
    )
    public String callTestAllready(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://10.125.128.22/test/allready/call2", String.class);
        return responseEntity.getBody();
    }
}
