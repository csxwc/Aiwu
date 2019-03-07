package com.aiwu.utils;

import org.springframework.stereotype.Component;

@Component
public class StatisticsTool {

    public static float countAvgByCity(String city) {

        return Float.parseFloat(HiveTool.findOneString("select avg(price) from house where city = ?", city));
    }

    public static float countAvgBetweenByCity(String city, float min, float max) {
        return Float.parseFloat(HiveTool.findOneString("select avg(price) from house where city = ? and price between "
                + min +" and "+ max, city));
    }

    public static float countMaxByCity(String city) {

        return Float.parseFloat(HiveTool.findOneString("select max(price) from house where city = ?", city));
    }

    public static float countminByCity(String city) {

        return Float.parseFloat(HiveTool.findOneString("select min(price) from house where city = ?", city));
    }

    public static void main(String[] args) {

        System.out.println();
    }
}

