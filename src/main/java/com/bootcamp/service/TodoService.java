package com.bootcamp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.bootcamp.model.Todo;
import com.bootcamp.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<Todo> getTodoList(Boolean includeDone) {
        return includeDone ? repository.findAll() : repository.findByIsDone(false);
    }

    public ResponseEntity<String> updateTodo(Todo item) {
        try {

            // update modification date & time to current date & time
            item.setModifiedOn(LocalDateTime.now());

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Todo>> violations = validator.validate(item);

            if (violations != null && !violations.isEmpty()) {
                String errMessage = "";
                for (ConstraintViolation<Todo> violation : violations) {
                    if (errMessage != "") {
                        errMessage = errMessage + ", ";
                    }
                    errMessage = errMessage + violation.getMessage();
                }
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errMessage);
            }

            if (item.getTodoId() < 1) {  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not existing record."); }

            Optional<Todo> oldData = repository.findById(item.getTodoId());
            if (oldData == null || !oldData.isPresent()) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not existing record."); }

            repository.save(item);
            return ResponseEntity.status(HttpStatus.OK).body("Success");

        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }


    public ResponseEntity<String> deleteTodo(long todoId) {
        try {

            if (todoId < 1) {  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not existing record."); }

            Optional<Todo> oldData = repository.findById(todoId);
            if (oldData == null || !oldData.isPresent()) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not existing record."); }

            repository.deleteById(todoId);
            return ResponseEntity.status(HttpStatus.OK).body("Success");

        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    public ResponseEntity<String> addTodo(Todo item) {
        try {

            // update modification date & time to current date & time
            item.setModifiedOn(LocalDateTime.now());

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Todo>> violations = validator.validate(item);

            if (violations != null && !violations.isEmpty()) {
                String errMessage = "";
                for (ConstraintViolation<Todo> violation : violations) {
                    if (errMessage != "") {
                        errMessage = errMessage + ", ";
                    }
                    errMessage = errMessage + violation.getMessage();
                }
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errMessage);
            }

            if (item.getTodoId() > 0) {  return ResponseEntity.status(HttpStatus.CONFLICT).body("Record already exist."); }

            repository.save(item);
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");

        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }


}
