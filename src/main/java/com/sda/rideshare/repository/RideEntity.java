package com.sda.rideshare.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideEntity extends JpaRepository<com.sda.rideshare.entity.RideEntity,String> {
}
