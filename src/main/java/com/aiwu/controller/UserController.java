package com.aiwu.controller;


import com.aiwu.bean.RespBean;
import com.aiwu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private String codeJudger = null;

    private String emailJudger = null;

    @RequestMapping("/t")
    public String test(HttpServletRequest request) {
        return "test:" + request.getSession().getAttribute("code");
    }

    @RequestMapping("/register/mail")
    @ResponseBody
    public RespBean sendCode(@RequestBody Map map, HttpServletRequest request) {

        String email = map.get("email").toString();
        String code = userService.sendCheckCode(email);

        codeJudger = code;
        emailJudger = email;

        return new RespBean("success", "发送验证码成功");
    }

    @RequestMapping("/register")
    @ResponseBody
    public RespBean register(@RequestBody Map map, HttpServletRequest request) {

        String email = map.get("email").toString();
        String code = map.get("code").toString();
        String username = map.get("username").toString();
        String password = map.get("password").toString();

        System.out.println("codeJudger:" + codeJudger);
        System.out.println("emailJudger:" + emailJudger);

        if (code.equals(codeJudger) && email.equals(emailJudger)) {

            userService.insertNewUser(username, email, password);
            return new RespBean("success", "注册成功");

        } else {
            return new RespBean("error", "注册失败，错误的邮箱或验证码");
        }

    }

}
