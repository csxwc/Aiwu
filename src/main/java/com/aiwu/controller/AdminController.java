package com.aiwu.controller;

import com.aiwu.bean.House;
import com.aiwu.bean.RespBean;
import com.aiwu.bean.User;
import com.aiwu.service.HouseService;
import com.aiwu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    HouseService houseService;

    // 用户管理

    @RequestMapping("/users")
    public Iterable<User> findAllUser() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user/name/{username}", method = RequestMethod.GET)
    public User findUserByName(@PathVariable String username) {

        return userService.getByUserName(username);

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable Integer id) {
            return userService.getUserById(id);
    }

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.DELETE)
    public RespBean deleteUserById(@PathVariable Integer uid) {

        User user = findUserById(uid);
        String username = user.getUsername();

        userService.deleteUserById(uid);
        return new RespBean("success", "删除用户 " + username + " 成功");

    }

    // 房源管理

    @RequestMapping(value = "/houses", method = RequestMethod.GET)
    public List<House> findAllHouses() {
        return houseService.findAllHouses();
    }

}
