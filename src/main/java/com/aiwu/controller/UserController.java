package com.aiwu.controller;


import com.aiwu.repository.UserRepository;
import com.aiwu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/t")
    public String test() {
        return "test";
    }

    @RequestMapping("/reg")
    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password) {

        userService.insertNewUser(username, email, password);

        return "register successfully.";
    }

}
