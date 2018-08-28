package com.cucc.microservice.train_demo.web;

import com.cucc.microservice.train_demo.domain.Rsp;
import com.cucc.microservice.train_demo.domain.ServiceConstant;
import com.cucc.microservice.train_demo.domain.ServiceResponse;
import com.cucc.microservice.train_demo.domain.User;
import com.cucc.microservice.train_demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/train/demo")
public class UserController {


    @Autowired
    UserService userService;



    @ApiOperation(
            value = "用户查询",
            notes = "查询所有用户"
    )
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> users(){
        List<User> users = new ArrayList<>();

        User user1 = new User();
        User user2 = new User();

        user1.setName("liu");
        user1.setAge(26);
        user2.setName("zhao");
        user2.setAge(18);

        users.add(user1);
        users.add(user2);

        return users;
    }


    @ApiOperation(
            value = "用户查询",
            notes = "符合微服务应答规范"
    )
    @RequestMapping(value = "/norm/users", method = RequestMethod.GET)
    public ServiceResponse users2(){
        List<Object> users = new ArrayList<>();

        User user1 = new User();
        User user2 = new User();

        user1.setName("liu");
        user1.setAge(26);
        user2.setName("zhao");
        user2.setAge(18);

        users.add(user1);
        users.add(user2);

        Rsp rsp = new Rsp();
        rsp.setData(users);
        rsp.setRspCode("0000");
        rsp.setRspDesc("success");

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.getSuccessResponse(rsp);

        return serviceResponse;
    }

    @ApiOperation(
            value = "增加一个用户",
            notes = "用户信息通过body传递"
    )
    @RequestMapping(
            value = "/users",
            method = RequestMethod.POST
    )
    public ServiceResponse save(
            @RequestBody User user
    ){
        try {
            userService.saveUser(user);
            Rsp rsp = new Rsp();
            rsp.setRspCode("0000");
            rsp.setRspDesc("success");

            ServiceResponse serviceResponse = new ServiceResponse();
            serviceResponse.getSuccessResponse(rsp);
            return  serviceResponse;
        }catch (Exception e){
            ServiceResponse serviceResponse = new ServiceResponse();
            serviceResponse.getErrorResponse(ServiceConstant.STATUS_SYSERROR, ServiceConstant.STATUS_SYSERROR,new Rsp());
            return serviceResponse;
        }
    }
}
