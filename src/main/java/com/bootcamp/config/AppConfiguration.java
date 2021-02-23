package com.bootcamp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

//import ph.tsi.crypto.Cryptography;

@Component
@ConfigurationProperties("app.datasource")
public class AppConfiguration {

    @NonNull
    private String username;

    @NonNull
    private String password;
    

    public String getUsername() {
        return "";
        // return Cryptography.decryptToAes256(this.username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return "";
        // return Cryptography.decryptToAes256(this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
