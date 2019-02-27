package com.aiwu.service;

import com.aiwu.bean.User;
import com.aiwu.bean.UserDetailsImpl;
import com.aiwu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Iterable<User> getAllUsers() {
        return  userRepository.findAll();
    }

    @Transactional
    public User getByUserName(String username) {

        User user = userRepository.findByUsername(username);
        return user;

    }

    @Transactional
    public void insertNewUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwdEncode = bCryptPasswordEncoder.encode(password);
        user.setPassword(pwdEncode);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = getByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("没有该用户");
        }

        return new UserDetailsImpl(user);
    }
}
