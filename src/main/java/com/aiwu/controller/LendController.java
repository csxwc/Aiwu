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
    public String addinfo()
    {
        House house = new House("123","123","test","陕西","咸阳","独立公寓",2,1,2,1,"no","no",150);
        lentService.putinfo(house,1);
        return "index";
    }

}
