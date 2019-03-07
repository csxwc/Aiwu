package com.aiwu.controller;

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
    public void torent(int houseid,int userid,Date startdate,Date enddate) throws ParseException {
        rentService.rent(houseid,userid,startdate,enddate);
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
    public String getusedate(@RequestBody Map map)
    {
        List<Map<String,String>> list = rentService.getusedate((int)map.get("userid"));
        Gson gson = new Gson();
        String str = gson.toJson(list);
        System.out.println("=========================");
        System.out.println(str);
        System.out.println("=========================");
        return str;
    }


}
