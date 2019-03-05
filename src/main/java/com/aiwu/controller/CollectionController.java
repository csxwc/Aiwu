package com.aiwu.controller;

import com.aiwu.bean.Collection;
import com.aiwu.bean.RespBean;
import com.aiwu.service.CollectionService;
import com.google.gson.Gson;
//import org.apache.calcite.rel.core.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @RequestMapping("/{userId}")
    public String getCollections(@PathVariable Integer userId) {

        Gson gson = new Gson();
        String collections = gson.toJson(collectionService.getCollectionsByUserId(userId));
        System.out.println(collections);
        return collections;
    }

    @RequestMapping("/add")
    public RespBean add(@RequestParam Map map) {

        collectionService.addCollection((Integer)map.get("user_id"), (Integer)map.get("house_id"));
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
