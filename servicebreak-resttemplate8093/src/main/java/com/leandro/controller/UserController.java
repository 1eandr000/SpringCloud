package com.leandro.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/find")
    @HystrixCommand(fallbackMethod = "findError")
    public Object find(@RequestParam("id") int id){

        return restTemplate.getForObject("http://service-user8081/find?id="+id, Object.class);
    }

    @RequestMapping ("/login")
    public Object login(@RequestParam String username, @RequestParam String password){
        return restTemplate.getForObject("http://service-user8081/login?username="+username+"&password="+password, Object.class);
    }

    public String findError(int id){
        return "系统异常，请联系管理员！";
    }
}
