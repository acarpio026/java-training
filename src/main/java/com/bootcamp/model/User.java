package com.bootcamp.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name = "AppUser")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Long userId;

    @Column(name = "Username", nullable = false)
    @NotEmpty(message = "username should not be empty/null.")
    private String username;

    @Column(name = "Password", nullable = false)
    @NotEmpty(message = "password should not be empty/null.")
    private String password;

    @Column(name = "Active", nullable = false)
    private Boolean active;
    
    // @ManyToMany(fetch = FetchType.LAZY)
    // @JoinTable(
    //     name = "USER_ROLES",
    //     joinColumns = @JoinColumn(name="USER_ID"),
    //     inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    // )
    // private Set<Role> roles = new HashSet<>();

    public User() {
    }


    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isActive() {
        return this.active;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
