package com.aiwu.controller;

import com.aiwu.bean.House;
import com.aiwu.repository.HouseRepository;
import com.aiwu.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HouseController {


    @Autowired
    private HouseService houseService;

    @RequestMapping("/find")
    public String findcity()
    {
        List<House> thisList;
        thisList = houseService.findbymoney("西安", 50, 200);

        return "index";

    }



}
