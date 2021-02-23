package com.bootcamp.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Todo")
public class Todo {

    @Id
    @Column(name = "TodoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @PositiveOrZero
    private Long todoId;

    @Column(name = "TodoDescription")
    @NotEmpty(message = "Description should not be empty/null.")
    private String todoDescription;

    @Column(name = "ModifiedOn")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    private LocalDateTime modifiedOn;

    @Column(name = "IsDone")
    @NotNull
    private Boolean isDone;

    public Long getTodoId() {
        return this.todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public String getTodoDescription() {
        return this.todoDescription;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public LocalDateTime getModifiedOn() {
        return this.modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Boolean isIsDone() {
        return this.isDone;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Todo() {
    }

}
