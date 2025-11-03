package com.chen.spring_boot_mysql.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/hello")
public class hello_controller {
    @GetMapping("")
    public String hello_controller() {
        return "hello";
    }
}
