package com.aiwu.utils;

import com.aiwu.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticsTool {

    @Autowired
    private static HouseRepository houseRepository;

    public static float countAvgByCity(String city) {

        System.out.println(city + houseRepository.queryPriceAvg(city));
//        return Float.parseFloat(HiveTool.findOneString("select avg(price) from house where city = ?", city));
        return houseRepository.queryPriceAvg(city);
    }

    public static float countAvgBetweenByCity(String city, float min, float max) {
//        return Float.parseFloat(HiveTool.findOneString("select avg(price) from house where city = ? and price between "
//                + min +" and "+ max, city));
        return houseRepository.queryAvgBetween(city, min, max);
    }

    public static float countMaxByCity(String city) {

//        return Float.parseFloat(HiveTool.findOneString("se?lect max(price) from house where city = ?", city));
        return houseRepository.queryPriceMax(city);
    }

    public static float countminByCity(String city) {

//        return Float.parseFloat(HiveTool.findOneString("select min(price) from house where city = ?", city));
        return houseRepository.queryPriceMin(city);
    }

    public static void main(String[] args) {

        System.out.println();
    }
}

