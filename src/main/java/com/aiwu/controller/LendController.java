package com.aiwu.controller;

import com.aiwu.bean.House;
import com.aiwu.service.LentService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lend")
public class LendController {

    @Autowired
    private LentService lentService;


    //输出某个人出租的所有房间信息
    @RequestMapping("/getlend")
    public String getall(@RequestBody Map map)
    {
        
        List<Map<String,String>> list = lentService.getlend((int)map.get("userid"));
        Gson gson = new Gson();
        String str = gson.toJson(list);
        System.out.println(str);
        return "index";
    }

    //房主添加住房信息
    @RequestMapping("/addhouse")
    public String addinfo(@RequestBody Map map)
    {
        House house = new House((String)map.get("jingdu"),(String)map.get("weidu"),(String)map.get("name"),(String)map.get("province"),(String)map.get("city"),(String)map.get("type"),(int)map.get("guest"),(int)map.get("room"),(int)map.get("bed"),(int)map.get("toilet"),(String)map.get("introduction"),"暂无",(int)map.get("price"));
        lentService.putinfo(house,(int)map.get("personid"));

        return "index";
    }


//    @RequestMapping("/addhouse")
//    public String addinfo()
//    {
//        System.out.print("1232341234");
//        House house = new House("123","123","mkk","shanxi","xian","Zhengtao",1,1,1,1,"anjdwnjdnqjwl","暂无",1);
//        lentService.putinfo(house,1);
//        System.out.print("============================");
//        return "index";
//    }

}
