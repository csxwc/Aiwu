package com.aiwu.service;

import com.aiwu.bean.House;
import com.aiwu.bean.Rent;
import com.aiwu.repository.HouseRepository;
import com.aiwu.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private HouseRepository houseRepository;


    @Transactional
    public void rent(int houseid,int userid,Date startdate,Date enddate) throws ParseException {
        Rent r = new Rent(startdate,enddate,houseid,userid);

        rentRepository.save(r);

    }

    @Transactional
    public List<Rent> getall(int id)
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
                Sort sort = new Sort(Sort.Direction.DESC, "start");
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
        List<Rent> rentList = new ArrayList<Rent>();

        try {
            Page<Rent> pages = rentRepository.findAllByPersonid(id,pageable);
            Iterator<Rent> it = pages.iterator();

            while (it.hasNext()) {
                rentList.add(((Rent) it.next()));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rentList;
    }

    @Transactional
    public List<List<String>> gethaveuse(int personid) throws ParseException {
        List<List<String>> finallist = new ArrayList<>();
        List<Rent> all = getall(personid);
        List<Rent> have = new ArrayList<Rent>() ;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<all.size();i++)
        {
            String str1 = sdf.format(new Date());
            Date today = sdf.parse(str1);
            String str2 = sdf.format(all.get(i).getEnd());
            Date endday = sdf.parse(str2);
            if(calc_days(today,endday)<0)
            {
                have.add(all.get(i));
            }
        }
        for(int i=0;i<have.size();i++)
        {
            List<String> stringlist = new ArrayList<>();
            //housename,city,type,starttime,endtime
            House ahouse = houseRepository.findAllById(have.get(i).getId());
            stringlist.add(ahouse.getName());
            stringlist.add(ahouse.getCity());
            stringlist.add(ahouse.getType());
            stringlist.add(sdf.format(have.get(i).getStart()));
            stringlist.add(sdf.format(have.get(i).getEnd()));
            finallist.add(stringlist);
        }
        return finallist;
    }






    public int calc_days(Date date1,Date date2) throws ParseException
    {//date1是在前的时间，date2是在后的时间
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        //跨年的情况会出现问题哦
//        //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 1
//        Date date1=sdf.parse("2019-12-31");
//        Date date2=sdf.parse("2020-01-01");
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
}