package com.leandro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
//    方法三
    @Autowired
    private RestTemplate restTemplate;

//  方法二
//    @Resource
//    LoadBalancerClient loadBalancerClient;

    @RequestMapping("/find")
    public Object find(@RequestParam("id") int id){
//        RestTemplate rt=new RestTemplate();
//        方法一
//        String result = rt.getForObject("http://localhost:8081/hello?userName="+userName,String.class);

//        方法二
//        ServiceInstance instance=loadBalancerClient.choose("service_user8081");
//        String url="http://"+instance.getHost()+":"+instance.getPort()+"/hello?userName="+userName;
//        String result=rt.getForObject(url,String.class);

//        方法三
        return restTemplate.getForObject("http://service-user8081/find?id="+id, Object.class);

    }

    @RequestMapping("/login")
    public Object login(@RequestParam String username, @RequestParam String password){
        return restTemplate.getForObject("http://service-user8081/login?username="+username+"&password="+password, Object.class);
    }
}
