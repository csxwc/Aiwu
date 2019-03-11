package com.aiwu.controller;

import com.aiwu.bean.House;
import com.aiwu.bean.Picture;
import com.aiwu.repository.HouseRepository;
import com.aiwu.repository.PictureRepository;
import com.aiwu.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private HouseRepository houseRepository;


    @RequestMapping("/wan")
    public void wan()
    {
        List<House> houses = houseRepository.findAll();
        int max =0 ;int min = 1000000;
        for(int i=0;i<houses.size();i++)
        {
            System.out.println(i+"=========目前最大："+max+"===============目前最小："+min);

            int index = houses.get(i).getId();
            List<Picture> list = pictureRepository.findAllByHouseid(index);
            if(list.size()>max)
            {
                max=list.size();
            }
            if(list.size()<min)
                min = list.size();
        }
    }


    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @Autowired
    RatingService ratingService;

    @RequestMapping("/t")
    public void test(@RequestParam Integer uid, @RequestParam Integer hid, @RequestParam int s) {

        ratingService.addRating(uid, hid, s);
        System.out.println(uid+ " "+ hid+ " "+s);
    }

}
