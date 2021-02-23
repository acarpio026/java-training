package com.bootcamp.model;

import java.util.Objects;

public class FrmLogin {
    
    private String username;
    private String password;



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

    public FrmLogin() {
    }

    public FrmLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public FrmLogin username(String username) {
        setUsername(username);
        return this;
    }

    public FrmLogin password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FrmLogin)) {
            return false;
        }
        FrmLogin frmLogin = (FrmLogin) o;
        return Objects.equals(username, frmLogin.username) && Objects.equals(password, frmLogin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }
}