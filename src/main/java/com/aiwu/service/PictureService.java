package com.aiwu.service;

import com.aiwu.bean.Picture;
import com.aiwu.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Transactional
    public List<Map<String,String>> getlittlepictures(int houseid)
    {
        List<Map<String,String>> finallist = new ArrayList<>();
        List<Picture> all = pictureRepository.findAllByHouseid(houseid);
        for(int i=0;i<all.size();i++)
        {
            System.out.println(all.get(i).getPicture());
        }
        if(all.size()>5)
        {
            for(int i=0;i<5;i++)
            {
                Map<String,String> amap = new HashMap<>();
                amap.put(String.valueOf(i),all.get(i).getPicture());
                finallist.add(amap);
            }
        }
        else
        {
            for(int i=0;i<all.size();i++)
            {
                Map<String,String> amap = new HashMap<>();
                amap.put(String.valueOf(i),all.get(i).getPicture());
                finallist.add(amap);
            }
        }
        return finallist;
    }
}
