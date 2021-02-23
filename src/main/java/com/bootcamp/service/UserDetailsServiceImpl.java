package com.bootcamp.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.bootcamp.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService service){
        this.userService =service;
    } 
 

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Username " + username + " not found.");
        } else if (!user.isActive()){
            throw new BadCredentialsException("User is not active");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), loadAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> loadAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>(); 
        //user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return authorities;
    }
}
