package com.sda.rideshare.repository;


import com.sda.rideshare.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<com.sda.rideshare.entity.UserEntity,String> {
    public UserEntity getUserByUsername(String username);

}
