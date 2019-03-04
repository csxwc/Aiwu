package com.aiwu.service;

import com.aiwu.bean.House;
import com.aiwu.bean.Lend;
import com.aiwu.repository.LendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class LentService {

//    @Autowired
//    private LendRepository lendRepository;
//
//    @Transactional
//    public List<Lend> getalllent(String personid)
//    {
//        Pageable pageable = new Pageable() {
//            @Override
//            public int getPageNumber() {
//                return 10;
//            }
//
//            @Override
//            public int getPageSize() {
//                return 10;
//            }
//
//            @Override
//            public long getOffset() {
//                return 0;
//            }
//
//            @Override
//            public Sort getSort() {
//                Sort sort = new Sort(Sort.Direction.DESC, "id");
//                return sort;
//            }
//
//            @Override
//            public Pageable next() {
//                return null;
//            }
//
//            @Override
//            public Pageable previousOrFirst() {
//                return null;
//            }
//
//            @Override
//            public Pageable first() {
//                return null;
//            }
//
//            @Override
//            public boolean hasPrevious() {
//                return false;
//            }
//        };
//        List<Lend> lendList = new ArrayList<Lend>();
//
//        try {
//            Page<Lend> pages = lendRepository.findAllByPersonId(personid,pageable);
//            Iterator<Lend> it = pages.iterator();
//
//            while (it.hasNext()) {
//                lendList.add(((Lend) it.next()));
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return lendList;
//    }


    //“我的发布”界面显示的信息
    //城市  标题  被租次数  价格  取消出租（按钮）  进入详情（按钮）

//    @Transactional
//    public



}
