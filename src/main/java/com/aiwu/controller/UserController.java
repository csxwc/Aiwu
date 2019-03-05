package com.aiwu.controller;


import com.aiwu.bean.RespBean;
import com.aiwu.repository.UserRepository;
import com.aiwu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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

    @RequestMapping("/check")
    @ResponseBody
    public RespBean check(@RequestBody Map map)
    {
         Integer userId = userService.checkUser((String)map.get("email"),(String)map.get("password"));

        if(userId != -1)
            return new RespBean("success", userId.toString());
        else
            return new RespBean("fail", "登陆失败");
    }

    @RequestMapping("/register")
    @ResponseBody
    public RespBean register(@RequestBody Map map, HttpServletRequest request) {

        String email = map.get("email").toString();
        String code = map.get("code").toString();
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        String desc = map.get("desc").toString();
        String date = map.get("date").toString();
        String gender = map.get("gender").toString();

        System.out.println("gender:" + gender);
        System.out.println("codeJudger:" + codeJudger);
        System.out.println("emailJudger:" + emailJudger);

        if (userService.getByUserName(username) != null)
            return new RespBean("error", "注册失败，用户名已被使用");

        if (code.equals(codeJudger) && email.equals(emailJudger)) {

            userService.insertNewUser(username, email, password, desc, date, gender);
            return new RespBean("success", "注册成功");

        } else {
            return new RespBean("error", "注册失败，错误的邮箱或验证码");
        }

    }

}
