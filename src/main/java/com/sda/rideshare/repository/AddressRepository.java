package com.sda.rideshare.repository;

import com.sda.rideshare.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<com.sda.rideshare.entity.AddressEntity,String> {
}
