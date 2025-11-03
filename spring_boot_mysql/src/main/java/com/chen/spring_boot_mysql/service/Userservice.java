package com.chen.spring_boot_mysql.service;

import com.chen.spring_boot_mysql.pojo.Users;
import com.chen.spring_boot_mysql.repository.Iuserres;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Userservice implements IUserservice {

    @Autowired
    Iuserres userRepository;


    @Override



    public Users addUser(Users user) {
        Users u = new Users();
        BeanUtils.copyProperties(user,u);
        return userRepository.save(u);
    }


}
