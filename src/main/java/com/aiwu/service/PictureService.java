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
    public List<List<String>> getlittlepictures(int houseid)
    {
        List<List<String>> finallist = new ArrayList<>();
        List<Picture> all = pictureRepository.findAllByHouseid(houseid);
        for(int i=0;i<all.size();i++)
        {
            System.out.println(all.get(i).getPicture());
        }
        if(all.size()>8)
        {
            for(int i=0;i<8;i++)
            {
                List<String> amap = new ArrayList<>();
                amap.add(all.get(i).getPicture());
                finallist.add(amap);
            }
        }
        else
        {
            for(int i=0;i<all.size();i++)
            {
                Map<String,String> amap = new HashMap<>();
                amap.put(String.valueOf(i),all.get(i).getPicture());
                // finallist.add(amap);
            }
        }

        return finallist;
    }

}
