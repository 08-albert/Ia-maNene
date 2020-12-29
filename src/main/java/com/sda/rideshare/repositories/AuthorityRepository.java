package com.sda.rideshare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<com.sda.rideshare.entities.AuthorityEntity,String>{
}
