package com.aiwu.controller;

import com.aiwu.bean.RespBean;
import com.aiwu.service.CollectionService;
import com.aiwu.service.RatingService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/{userId}")
    public String getCollections(@PathVariable Integer userId) {

        Gson gson = new Gson();
        String collections = gson.toJson(collectionService.getCollectionsByUserId(userId));
        System.out.println(collections);
        return collections;
    }

    @RequestMapping("/iscollected")
    public Boolean isCollected(@RequestBody Map map) {

        return collectionService.isCollected((Integer)map.get("user_id"), (Integer)map.get("house_id"));
    }

    @RequestMapping("/add")
    public RespBean add(@RequestBody Map map) {

        collectionService.addCollection((Integer)map.get("user_id"), (Integer)map.get("house_id"));
        ratingService.addRating((Integer)map.get("user_id"), (Integer)map.get("house_id"), 5);
        return new RespBean("success", "添加收藏成功");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public RespBean delete(@PathVariable Integer id) {

        if (collectionService.deleteCollection(id)) {
            return new RespBean("success", "删除收藏成功");
        } else {
            return new RespBean("error", "删除收藏失败");
        }
    }
}
