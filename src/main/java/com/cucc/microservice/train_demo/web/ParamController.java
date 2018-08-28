package com.cucc.microservice.train_demo.web;


import com.cucc.microservice.train_demo.domain.User;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/train/demo")
public class ParamController {
    @ApiIgnore
    @RequestMapping(
            value = "/params/{pathParam1}/", //URL
            method = RequestMethod.POST//HTTP类型
    )
    public Map<String,  Object> testParams(
            @PathVariable(required = false) String pathParam1,
            @RequestParam(required = false)  String queryParam1,
            @RequestHeader(required = false) String headerParam1,
            @CookieValue(required = false) String cookieParam1,
            @RequestBody(required = false) User user

    ){
        //存放所有类型参数
        Map<String, Object> params = new HashMap<>();

        //query参数
        Map<String, Object> querys = new HashMap<>();
        querys.put("queryParam1", queryParam1);
        params.put("query", querys);

        //header参数
        Map<String, Object> headers = new HashMap<>();
        headers.put("headerParam1", headerParam1);
        params.put("header", headers);

        //cookie参数
        Map<String, Object> cookies = new HashMap<>();
        cookies.put("cookieParam1", cookieParam1);
        params.put("cookie", cookies);

        //路径参数
        Map<String, Object> paths = new HashMap<>();
        paths.put("pathParam1", pathParam1);
        params.put("path", paths);

        //body参数

        params.put("body", user);


        return params;
    }
}
