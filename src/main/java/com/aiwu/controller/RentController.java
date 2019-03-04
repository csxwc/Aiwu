package com.aiwu.controller;

import com.aiwu.service.RentService;
import com.google.gson.Gson;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService rentService;

    @RequestMapping("/torent")
    public void torent(int houseid,int userid,Date startdate,Date enddate) throws ParseException {
        rentService.rent(houseid,userid,startdate,enddate);
    }



    @RequestMapping("/getused")
    public String getused(@RequestParam Map map) throws ParseException {
        List<Map<String,String>> list = rentService.gethaveuse((int)map.get("userid"));
        Gson gson = new Gson();
        String str = gson.toJson(list);
        return str;
    }

    @RequestMapping("/getnotused")
    public String getnotused(@RequestParam Map map) throws ParseException {
        List<Map<String,String>> list = rentService.getnotuse((int)map.get("userid"));
        Gson gson = new Gson();
        String str = gson.toJson(list);
        System.out.println(str);
        return str;
    }

    @RequestMapping("/getusedate")
    public String getusedate(Integer roomid)
    {
        List<Map<String,String>> list = rentService.getusedate(1);
        Gson gson = new Gson();
        String str = gson.toJson(list);
        System.out.println("=========================");
        System.out.println(str);
        System.out.println("=========================");
        return str;
    }

}
