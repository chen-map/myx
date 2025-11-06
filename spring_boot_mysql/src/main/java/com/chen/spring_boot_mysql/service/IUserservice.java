package com.chen.spring_boot_mysql.service;

import com.chen.spring_boot_mysql.pojo.Users;

import java.util.Optional;

public interface IUserservice {

    Users addUser(Users user);

    Optional<Users> getUserById(int id);

    boolean deleteUserById(Integer id);

}
