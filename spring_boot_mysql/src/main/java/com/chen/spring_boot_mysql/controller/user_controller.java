package com.chen.spring_boot_mysql.controller;

import com.chen.spring_boot_mysql.pojo.Users;
import com.chen.spring_boot_mysql.service.IUserservice;
import com.chen.spring_boot_mysql.service.Userservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @GetMapping("/find/{userId}") // 路径参数为userId
    public ResponseEntity<?> getUserById(@PathVariable("userId") Integer id) {
        // 调用Service层的查询方法（返回Optional<Users>，避免空指针）
        Optional<Users> user = Userservice.getUserById(id);

        // 处理查询结果
        if (user.isPresent()) {
            // 若用户存在，返回200状态码+用户信息（自动转为JSON）
            return ResponseEntity.ok(user.get());
        } else {
            // 若用户不存在，返回404状态码+提示信息
            return ResponseEntity.notFound().build();
            // 或返回自定义提示：return ResponseEntity.status(404).body("用户ID不存在");
        }
    }
    //删除用户
    @DeleteMapping("/delete/{id}") // 路径参数为id（表示要删除的用户ID）
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        boolean isDeleted =Userservice.deleteUserById(id);
        if (isDeleted) {
            // 若删除成功，返回200状态码+提示
            return ResponseEntity.ok("用户ID=" + id + " 删除成功");
        } else {
            // 若用户不存在，返回404状态码+提示
            return ResponseEntity.notFound().build();
            // 或自定义提示：return ResponseEntity.status(404).body("用户ID不存在，删除失败");
        }
    }





}
