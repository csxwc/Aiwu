package com.aiwu.service;

import com.aiwu.bean.House;
import com.aiwu.bean.Lend;
import com.aiwu.repository.HouseRepository;
import com.aiwu.repository.LendRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class LentService {

    @Autowired
    private LendRepository lendRepository;
    @Autowired
    private HouseRepository houseRepository;

    @Transactional
    public List<Lend> getalllent(int personid)
    {
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 10;
            }

            @Override
            public int getPageSize() {
                return 10;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                Sort sort = new Sort(Sort.Direction.DESC, "id");
                return sort;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        List<Lend> lendList = new ArrayList<Lend>();

        try {
            Page<Lend> pages = lendRepository.findAllByPersonId(personid,pageable);
            Iterator<Lend> it = pages.iterator();

            while (it.hasNext()) {
                lendList.add(((Lend) it.next()));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return lendList;
    }


    //“我的发布”界面显示的信息
    //城市  标题  被租次数  价格  取消出租（按钮）  进入详情（按钮）

    @Transactional
    public List<Map<String,String>> getlend(int personid)
    {
//        System.out.println("===========");
        List<Map<String,String>> finallist = new ArrayList<>();
//        System.out.println(personid);
        List<Lend> all = lendRepository.findAllByPersonId(personid);
//        String str = new Gson().toJson(all);
//        System.out.println(str);
//        System.out.println("28347293648263847");
//        System.out.println(all.size());
        for(int i=0;i<all.size();i++)
        {
            Map<String,String> amap = new HashMap<>();
            //城市  标题  被租次数  价格  取消出租（按钮）  进入详情（按钮）
            House ahouse = houseRepository.findAllById(all.get(i).getRoomId());
            amap.put("city",ahouse.getCity());
            amap.put("title",ahouse.getName());
            amap.put("booktime",String.valueOf(ahouse.getBooktime()));
            amap.put("price",String.valueOf(ahouse.getPrice()));
            finallist.add(amap);
        }

        return finallist;
    }

//    @Transactional
//    public List<Lend> findById(int id){
//        return lendRepository.findAllByPersonId(id);
//    }


    //房主添加住房信息
    @Transactional
    public void putinfo(House house,int personid)
    {
        houseRepository.save(house);
        lendRepository.save(new Lend(personid,house.getId()));
    }




}
