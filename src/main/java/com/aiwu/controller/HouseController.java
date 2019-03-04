package com.aiwu.controller;

import com.aiwu.bean.House;
import com.aiwu.service.HouseService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/house")
public class HouseController {


    @Autowired
    private HouseService houseService;

    @RequestMapping("/find")
    public String findcity()
    {
        List<House> list = houseService.choose("西安" ,"整套公寓",1,1,1,1,100,333);
        Gson gson = new Gson();
        String str = gson.toJson(list);
        System.out.println(str);
        return "index";
    }



}
