package com.imooc.springquartz.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
//haha
    @RequestMapping(produces = "application/json;charset=UTF-8",value="login",method = {RequestMethod.GET})
    @ResponseBody
    private  String hello(
            @RequestParam(value = "username", required = false)String username,
            @RequestParam(value = "password", required = false)String password
    ){
        return "Hello 哈  "+username+",Your password is: "+password;

    }

}