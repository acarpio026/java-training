package com.bootcamp.repository;

import java.util.List;

import com.bootcamp.model.Todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{

    @Query(value = "SELECT * FROM todo t WHERE t.is_done = ?1", nativeQuery = true)
    List<Todo> findByIsDone(Boolean isDone);
    
}
