package com.aiwu.controller;


import com.aiwu.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @RequestMapping("/t")
    public String test() {
        return "test";
    }

    @RequestMapping("/reg")
    public String register(@RequestParam String username, String password) {

        userService.insertNewUser(username, password);

        return "register successfully.";
    }

}
