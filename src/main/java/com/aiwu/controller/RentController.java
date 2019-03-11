package com.aiwu.controller;

import com.aiwu.bean.Rent;
import com.aiwu.bean.User;
import com.aiwu.repository.UserRepository;
import com.aiwu.service.RentService;
import com.google.gson.Gson;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService rentService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/torent")
    public void torent(@RequestBody Map map) throws ParseException {
        rentService.rent((int)map.get("houseid"),(int)map.get("userid"),new Date((long)map.get("startdate")),new Date((long)map.get("enddate")));
    }


    @RequestMapping("/getused")
    public String getused(@RequestBody Map map) throws ParseException {
        List<Map<String,String>> list = rentService.gethaveuse((int)map.get("userid"));
        Gson gson = new Gson();
        String str = gson.toJson(list);
        return str;
    }

    @RequestMapping("/getnotused")
    public String getnotused(@RequestBody Map map) throws ParseException {
        List<Map<String,String>> list = rentService.getnotuse((int)map.get("userid"));
        Gson gson = new Gson();
        String str = gson.toJson(list);
        System.out.println(str);
        return str;
    }

    @RequestMapping("/getusedate")
    //public List<Map<String,Date>> getusedate(@RequestBody Map map)
    public String getusedate(@RequestBody Map map)
    {
        List<Map<String,String>> list = rentService.getusedate((int)map.get("houseid"));
        Gson gson = new Gson();
        String str = gson.toJson(list);
        System.out.println("=========================");
        System.out.println(str);
        System.out.println("=========================");
        System.out.println("12312");
        return str;
    }

    @RequestMapping("/isrent")
    public boolean isrent(@RequestBody Map map) throws ParseException {
        boolean a = rentService.isrent((int)map.get("houseid"));
        //System.out.print(a);

        return a;
    }




}
