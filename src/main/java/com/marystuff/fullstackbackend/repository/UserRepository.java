package com.marystuff.fullstackbackend.repository;

import com.marystuff.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//the data type of the primary key ID is Long
public interface UserRepository extends JpaRepository<User, Long> {

}
