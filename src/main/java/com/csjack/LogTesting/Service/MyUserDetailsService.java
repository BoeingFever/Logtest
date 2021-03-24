package com.csjack.LogTesting.Service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // pass the user name from parameter, and expect by the name the program load
        // the user info either from database or somewhere else
        // for now we use a hard code user

//        return null;
        return new User("foo","foo", new ArrayList<>());
    }
}
