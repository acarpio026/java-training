package com.bootcamp.repository;

import com.bootcamp.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {    
    
    @Query(nativeQuery = true, value = "SELECT * FROM app_user AU WHERE AU.username = ?1")
    public User findByUsername(String username);

    @Query(nativeQuery = true, value="SELECT COUNT(*) FROM app_user")
    public Long getUserCount();

}