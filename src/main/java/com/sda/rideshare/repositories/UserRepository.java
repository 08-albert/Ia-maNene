package com.sda.rideshare.repositories;


import com.sda.rideshare.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<com.sda.rideshare.entities.UserEntity,Integer> {
    public UserEntity getUserByUsername(String username);

}