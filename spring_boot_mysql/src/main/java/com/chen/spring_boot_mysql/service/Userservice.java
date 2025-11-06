package com.chen.spring_boot_mysql.service;

import com.chen.spring_boot_mysql.pojo.Users;
import com.chen.spring_boot_mysql.repository.Iuserres;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Optional<Users> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean deleteUserById(Integer id) {
        // 先判断用户是否存在
        if (userRepository.existsById(id)) {
            // 若存在，执行删除（JpaRepository内置deleteById方法）
            userRepository.deleteById(id);
            return true; // 删除成功
        } else {
            // 若不存在，返回false
            return false;
        }
    }
}
