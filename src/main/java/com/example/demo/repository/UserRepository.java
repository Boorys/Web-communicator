package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.apache.tomcat.jni.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserId(String userId);
    UserEntity findByEmail(String email);
    UserEntity findByFirstName(String firstName);

}
