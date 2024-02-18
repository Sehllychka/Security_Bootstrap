package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService extends UserDetailsService {
    List<User> getUsers();

    void addUser(User user);

    void removeUser(long id);

    void updateUser(User user);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
