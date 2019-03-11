package com.aiwu.controller;

import com.aiwu.bean.House;
import com.aiwu.repository.HouseRepository;
import com.aiwu.repository.PictureRepository;
import com.aiwu.service.HouseService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/house")
public class HouseController {


    @Autowired
    private HouseService houseService;
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private PictureRepository pictureRepository;

    @RequestMapping("/find")
    public String findcity(@RequestBody Map map)
    {
        List<House> list = houseService.choose((String)map.get("city"),(String)map.get("type"),(int)map.get("guest"),(int)map.get("bedroom"),(int)map.get("bed"),(int)map.get("toilet"),(int)map.get("minprice"),(int)map.get("maxprice") );

        Gson gson = new Gson();
        String str = gson.toJson(list);
        return str;

    }



    @RequestMapping("/load")
    public void loadData() {
        houseService.loadData();
    }

    @RequestMapping("/update")
    public void update()
    {
//        List<House> list1 = houseRepository.findAllByProvince("上海市");
//        for(int i=0;i<list1.size();i++)
//        {
//            House h = list1.get(i);
//            h.setCity("上海");
//            houseRepository.save(h);
//        }
//        System.out.println(1);
//        List<House> list2 = houseRepository.findAllByProvince("上海");
//        for(int i=0;i<list2.size();i++)
//        {
//            House h = list2.get(i);
//            h.setCity("上海");
//            houseRepository.save(h);
//        }
//        System.out.println(2);
//        List<House> list3 = houseRepository.findAllByProvince("北京");
//        for(int i=0;i<list3.size();i++)
//        {
//            House h = list3.get(i);
//            h.setCity("北京");
//            houseRepository.save(h);
//        }
//        System.out.println(3);
//        List<House> list4 = houseRepository.findAllByProvince("北京市");
//        for(int i=0;i<list4.size();i++)
//        {
//            House h = list4.get(i);
//            h.setCity("北京");
//            houseRepository.save(h);
//        }
//        System.out.println(4);
//        List<House> list5 = houseRepository.findAllByProvince("重庆");
//        for(int i=0;i<list5.size();i++)
//        {
//            House h = list5.get(i);
//            h.setCity("重庆");
//            houseRepository.save(h);
//        }
//        System.out.println(5);
//        List<House> list6 = houseRepository.findAllByProvince("重庆市");
//        for(int i=0;i<list6.size();i++)
//        {
//            House h = list6.get(i);
//            h.setCity("重庆");
//            houseRepository.save(h);
//        }
//        System.out.println(6);
        List<House> l = houseRepository.findAll();
        System.out.println(l.size());
//        for (int i=0;i<l.size();i++)
//        {
//            System.out.println(i);
//            House h = l.get(i);
//            h.setBooktime((int)(Math.random()*100));
//            houseRepository.save(h);
//        }

    }


    @RequestMapping("/getallinfo")
    public void getallinfo(@RequestBody Map map)
    {
        House house = houseRepository.findAllById((int)map.get("houseid"));
        Gson gson = new Gson();
        String str = gson.toJson(house);
        System.out.println(str);
    }

    //String city, float priceWeight, float heatWeight, float sizeWeight, float comfortable, String...strings
    @RequestMapping("insel")
    public String intelligentSelect(@RequestBody Map map) {

         List<House> houses = houseService.intelligentSelect(map.get("city").toString(), (int)map.get("price"),
                (int)map.get("hot"), (int)map.get("room"), (int)map.get("comfort"), (List<String>) map.get("theme"));

        Gson gson = new Gson();
        String str = gson.toJson(houses);
        return str;

    }

    @RequestMapping("/count")
    public void count()
    {
        List<String> city  =new ArrayList<>();
        List<Integer> num = new ArrayList<>();
        List<House> all = houseRepository.findAll();
        for(int i=0;i<all.size();i++)
        {
            boolean have = false;
            for(int k=0;k<city.size();k++)
                if(city.get(k).equals(all.get(i).getCity()))
                {
                    have = true;
                    break;
                }
            if(have==false)
            {
                city.add(all.get(i).getCity());
                num.add(0);
            }
        }
        System.out.println(city.size());
        System.out.println(num.size());
        for(int i=0;i<all.size();i++)
        {
            String acity = all.get(i).getCity();
            for(int j=0;j<city.size();j++)
            {
                if(city.get(j).equals(acity))
                {
                    int t = num.get(j)+1;
                    num.set(j,t);
                    break;
                }
            }

        }
        System.out.print("[");
        for(int i =0;i<num.size();i++)
        {

            if(num.get(i)>60)
            {
                System.out.print("'"+city.get(i)+"',");
                //System.out.println(num.get(i));
            }

        }
        System.out.println("]");

        System.out.print("[");
        for(int i =0;i<num.size();i++) {
            if (num.get(i) > 60) {

                float sum=0;
                for (int j = 0; j < all.size(); j++) {
                    if (all.get(j).getCity().equals(city.get(i))) {
                        sum += all.get(j).getBooktime();
                    }
                }
                    System.out.print( sum+ ",");


            }

        }
        System.out.print("]");
    }


}
