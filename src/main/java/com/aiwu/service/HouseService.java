package com.aiwu.service;

import com.aiwu.bean.House;
import com.aiwu.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.*;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Transactional
    public void InsertHosue(House house)
    {
        houseRepository.save(house);
    }

    @Transactional
    public void DeleteHouse(String id)
    {
        houseRepository.deleteById(id);
    }

    @Transactional
    public List<House> findByCity(String city)
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
                Sort sort = new Sort(Sort.Direction.DESC, "city");
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
        List<House> houseList = new ArrayList<House>();

        try {
           Page<House> pages = houseRepository.findAllByCity(city,pageable);
            Iterator<House> it = pages.iterator();

            while (it.hasNext()) {
                houseList.add(((House) it.next()));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return houseList;

    }

    @Transactional
    public String getName(String id)
    {
        return houseRepository.findAllById(id).getName();
    }

    @Transactional
    public List<House> findbymoney(String city,int minmoney,int maxmoney)
    {
        List<House> initList = findByCity(city);
        List<House> LL = new ArrayList<House>();
        for(int i=0;i<initList.size();i++)
        {
            if(initList.get(i).getPrice()>=minmoney&&initList.get(i).getPrice()<=maxmoney)
                LL.add(initList.get(i));
        }
        return LL;
    }




}
