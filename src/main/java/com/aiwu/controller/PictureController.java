package com.aiwu.controller;

import com.aiwu.bean.Picture;
import com.aiwu.repository.PictureRepository;
import com.aiwu.service.HouseService;
import com.aiwu.service.PictureService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;
    @Autowired
    private  PictureRepository pictureRepository;

    @RequestMapping("/getlittle")
    public String getlittle(@RequestBody Map map)
    {
        List<List<String>> list = pictureService.getlittlepictures((int)map.get("houseid"));
        Gson gson = new Gson();
        String str = gson.toJson(list);
        return str;

    }

    @RequestMapping("/num")
    public int getnum()
    {
        List<Picture> list = pictureRepository.findAll();
        return list.size();
    }
}
