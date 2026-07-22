package com.leandro.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
public class UserController {
//    方法三
    @Autowired
    private RestTemplate restTemplate;

//  方法二
//    @Resource
//    LoadBalancerClient loadBalancerClient;

    @RequestMapping("/find")
    @HystrixCommand(fallbackMethod = "findError")
    public String find(@RequestParam("id") int id){
//        RestTemplate rt=new RestTemplate();
//        方法一
//        String result = rt.getForObject("http://localhost:8081/hello?userName="+userName,String.class);

//        方法二
//        ServiceInstance instance=loadBalancerClient.choose("service_user8081");
//        String url="http://"+instance.getHost()+":"+instance.getPort()+"/hello?userName="+userName;
//        String result=rt.getForObject(url,String.class);

//        方法三
        String result=restTemplate.getForObject("http://service-user8081/find?id="+id,String.class);

        return result;
    }

    public String findError(int id){
        return "系统异常，请联系管理员！";
    }
}
