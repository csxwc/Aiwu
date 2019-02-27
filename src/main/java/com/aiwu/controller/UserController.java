package com.aiwu.controller;


import com.aiwu.repository.UserRepository;
import com.aiwu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/t")
    public String test(HttpServletRequest request) {
        return "test:" + request.getSession().getAttribute("code");
    }

    @RequestMapping("/register/mail")
    public String sendCode(@RequestParam String email, HttpServletRequest request) {

        String code = userService.sendCheckCode(email);
        System.out.println(code);

        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        session.setAttribute("email", email);

        return "ok";
    }

    @RequestMapping(value = "/register")
    public String register(@RequestParam String username, @RequestParam String email,
                           @RequestParam String password, @RequestParam String code,
                           HttpServletRequest request) {

        if (code.equals(request.getSession().getAttribute("code"))
                && email.equals(request.getSession().getAttribute("email"))) {

            userService.insertNewUser(username, email, password);
            return "register successfully.";

        } else {
            return "error code or email.";
        }
    }

}
