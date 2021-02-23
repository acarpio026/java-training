package com.bootcamp.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.bootcamp.model.User;
import com.bootcamp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository; 
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Long getUserCount() {
        return repository.getUserCount();
    }

    public ResponseEntity<String> addUser(User item) {
        try {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<User>> violations = validator.validate(item);

            if (violations != null && !violations.isEmpty()) {
                String errMessage = "";
                for (ConstraintViolation<User> violation : violations) {
                    if (errMessage != "") {
                        errMessage = errMessage + ", ";
                    }
                    errMessage = errMessage + violation.getMessage();
                }
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errMessage);
            }

            if (item.getUserId() > 0) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Record already exist.");
            }

            
            item.setPassword(new BCryptPasswordEncoder().encode(item.getPassword()));
            repository.save(item);
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");

        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }

    }






}
