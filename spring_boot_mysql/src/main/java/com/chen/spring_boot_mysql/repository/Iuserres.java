package com.chen.spring_boot_mysql.repository;

import com.chen.spring_boot_mysql.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Iuserres extends CrudRepository<Users,Integer> {
}
