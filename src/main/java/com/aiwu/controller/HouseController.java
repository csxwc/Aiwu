package com.aiwu.controller;

import com.aiwu.bean.House;
import com.aiwu.service.HouseService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/house")
public class HouseController {


    @Autowired
    private HouseService houseService;

    @RequestMapping("/find")
    public String findcity(@RequestParam Map map)
    {
        List<House> list = houseService.choose((String)map.get("city"),(String)map.get("type"),(int)map.get("guest"),(int)map.get("bedroom"),(int)map.get("bed"),(int)map.get("toilet"),(int)map.get("minprice"),(int)map.get("maxprice") );

        Gson gson = new Gson();
        String str = gson.toJson(list);
        return str;
    }

    @RequestMapping("/load")
    public void loadData() {
        houseService.loadData();
    }



}
