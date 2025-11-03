package com.chen.spring_boot_mysql.controller;

import com.chen.spring_boot_mysql.pojo.Users;
import com.chen.spring_boot_mysql.service.IUserservice;
import com.chen.spring_boot_mysql.service.Userservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/user")



public class user_controller {

    @Autowired
    private IUserservice Userservice;

    //@Autowired
    //com.chen.spring_boot_mysql.service.Userservice userservice;

    @PostMapping("/test")
    public String testPost() {
        return "POST Test Success!";
    }
    //增加用户
    @PostMapping("/add")

    public String add(@Valid @RequestBody Users user) {
        Userservice.addUser(user);
        return "add success!";

    }


    //查询


    //删除用户






}
