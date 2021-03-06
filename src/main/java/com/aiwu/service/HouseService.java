package com.aiwu.service;

import com.aiwu.bean.House;
import com.aiwu.repository.HouseRepository;
import com.aiwu.utils.BaseUserRecommender;
import com.aiwu.utils.HiveTool;
import com.aiwu.utils.StatisticsTool;
//import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;




@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private HiveTool hiveTool;

    @Autowired
    private BaseUserRecommender baseUserRecommender;

    @Autowired
    private DataBaseService dataBaseService;

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

    public List<House> findAllHouses() { return houseRepository.findAll(); }

    public House findById(Integer id) { return houseRepository.findAllById(id); }

    @Transactional
    public void loadData() {
        List<House> houses = hiveTool.findHouseList("select * from house");
        while(!houses.isEmpty()) {
            houseRepository.save(houses.remove(0));
        }
    }

    @Transactional
    public List<House> findByCity(String city)
    {
        List<House> houseList = houseRepository.findAllByCity(city);
        return houseList;

    }

    @Transactional
    public String getName(int id)
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

    @Transactional
    public List<House> findbytype(String type,String city)
    {
        List<House> initList = findByCity(city);
        List<House> LL = new ArrayList<House>();
        for(int i=0;i<initList.size();i++)
        {
            if(initList.get(i).getType().equals(type))
                LL.add(initList.get(i));
        }
        return LL;
    }

    @Transactional
    public List<House> findbybed(int bed,String city)
    {
        List<House> initList = findByCity(city);
        List<House> LL = new ArrayList<House>();
        for(int i=0;i<initList.size();i++)
        {
            if(initList.get(i).getBed()==bed)
                LL.add(initList.get(i));
        }
        return LL;
    }


    @Transactional
    public List<House> choose(String city,String type,int guest,int bedroom,int bed,int toilet,int minprice,int maxprice)
    {
        List<House> ancity = findByCity(city);
        System.out.print("按城市搜索结果：");
        System.out.println(ancity.size());

        if(type!=null)
        {
            List<House> houseList = houseRepository.findAllByTypeAndCity(type,city);
            ancity = getsame(ancity,houseList);

            System.out.print("按类型搜索结果：");
            System.out.println(ancity.size());
        }
        if(guest!=-1)
        {

            List<House> houseList = houseRepository.findAllByGuestAndCity(guest,city);

            ancity = getsame(ancity,houseList);

            System.out.print("按客人搜索结果：");
            System.out.println(ancity.size());
        }
        if(bedroom!=-1)
        {

            List<House> houseList = houseRepository.findByCityAndRoom(city,bedroom);

            ancity = getsame(ancity,houseList);

            System.out.print("按卧室数搜索结果：");
            System.out.println(ancity.size());
        }
        if(bed!=-1)
        {

            List<House> houseList = houseRepository.findAllByBedAndCity(bed,city);
            ancity = getsame(ancity,houseList);

            System.out.print("按床位数搜索结果：");
            System.out.println(ancity.size());
        }
        if(toilet!=-1)
        {

            List<House> houseList = houseRepository.findAllByToiletAndCity(toilet,city);

            ancity = getsame(ancity,houseList);

            System.out.print("按卫生间搜索结果：");
            System.out.println(ancity.size());
        }
        if(minprice!=-1||maxprice!=-1)
        {
            List<House> houselist = findbymoney(city,minprice,maxprice);
            ancity = getsame(ancity,houselist);

            System.out.print("按价格搜索结果：");
            System.out.println(ancity.size());
        }
        return  ancity;
    }




    public List<House> getsame(List<House> alist,List<House> blist)//alist为主要
    {
        List<House> finallist = new ArrayList<House>();
        for(int i=0;i<alist.size();i++)
        {
            boolean same = false;
            for (int j = 0; j < blist.size(); j++) {
                if (alist.get(i).getId() == blist.get(j).getId())
                {
                    same = true;
                    break;
                }
            }
            if(same == true)
                finallist.add(alist.get(i));
        }
        return finallist;


    }


    public List<House> intelligentSelect(String city, int price, int hot, int room, int comfort, List<String> strings) {



        float priceWeight = price / 100;
        float heatWeight = hot / 100;
        float sizeWeight = room / 100;
        float comfortable = comfort / 100;

        StringBuffer match = new StringBuffer();
        match.append("select * from house where city like '%" + city + "%'");
        match.append(selectByPriceweight(city, priceWeight));
        match.append(selectByHeatweight(heatWeight));
        match.append(selectBySizeWeight(sizeWeight));
        match.append(selectByComfortable(comfortable));

        String keyword = null;
        for (int i = 0; i < strings.size(); i++) {
            if (strings.get(i).equals("极致性价比"))
                keyword = "便宜";
            else if (strings.get(i).equals("方便出行"))
                keyword = "交通";
            else if (strings.get(i).equals("舒适生活"))
                keyword = "舒";
            else if (strings.get(i).equals("热门景点"))
                keyword = "景";
            else if (strings.get(i).equals("畅想美食"))
                keyword = "食";

            match.append("and introduction like '%" + keyword + "%'");
        }
        match.append(";");
        System.out.println(match);
        List<House> list = dataBaseService.getAll(match.toString());
        //System.out.println(HiveTool.findHouseIdList(match.toString()));
        return list;
    }

    public String selectByComfortable(float comfortable) {
        int minus = 0;

        if (comfortable <= 0.25) {
            return " ";
        } else if (comfortable <= 0.5) {
            minus = 5;
        } else if (comfortable <= 0.75) {
            minus = 2;
        } else {
            minus = 1;
        }

        return " and guest-bed < " + minus;

    }

    /**
     * 独立房间、合住房间、整
     * @param sizeWeight
     * @return
     */
    public String selectBySizeWeight(float sizeWeight) {
        String word = null;

        if (sizeWeight <= 0.25)
            word = "整%' or type like '%独立房间%' or type like '%合住房间";
        else if (sizeWeight <= 0.5)
            word = "整%' or type like '%独立房间";
        else if (sizeWeight <= 0.75)
            word = "整";
        else
            return "";

        return " and (type like '%" + word + "%')";
    }


    public String selectByHeatweight(float heatWeight) {
        int min,max;

        if (heatWeight <= 0.25) {
            min = 0;
            max = 25;
        } else if (heatWeight <= 0.5) {
            min = 26;
            max = 50;
        } else if (heatWeight <= 0.75) {
            min = 51;
            max = 75;
        } else {
            min = 76;
            max = 100;
        }

        return " and booktime between " + min + " and " + max;
    }


    /**
     * 根据价格权重筛选
     * 价格权重越高，对价格越看重，价格区间更窄，价格更低
     * @param city
     * @param priceWeight
     * @return sql语句中的一段
     */
    public String selectByPriceweight(String city, float priceWeight) {

        float realmin = 0;
        float realmax = 0;

        System.out.println(city);
        System.out.println(houseRepository);

        float avg = houseRepository.queryPriceAvg(city);
        float max = houseRepository.queryPriceMax(city);
        float min = houseRepository.queryPriceMin(city);
        float maxavg = houseRepository.queryAvgBetween(city, avg, max);
        float minavg = houseRepository.queryAvgBetween(city, min, avg);


//        float avg = StatisticsTool.countAvgByCity(city);
//        float max = StatisticsTool.countMaxByCity(city);
//        float min = StatisticsTool.countminByCity(city);
//        float maxavg = StatisticsTool.countAvgBetweenByCity(city, avg, max);
//        float minavg = StatisticsTool.countAvgBetweenByCity(city, min, avg);
        float reference = (float) (minavg + (maxavg - minavg) * 0.5);
        float center = (float) minavg + (maxavg - minavg) * (1 - priceWeight);

//        float maxrange = max - min;
//        float realrange = (float) maxrange * (1 - priceWeight);
//        realrange *= 0.5;
//        if (realrange < 100) realrange = 100;
//
//        if (center < reference) {
//            realmax = center + realrange / 2 < maxavg ? center + realrange / 2 : maxavg;
//            realmin = center - (realrange - (realmax - center));
//            if (realmin < min) realmin = min;
//        } else {
//            realmin = center - realrange / 2 > minavg ? center - realrange / 2 : minavg;
//            realmax = center + (realrange - (center - realmin));
//
//        }

        if (priceWeight <= 0.25) {
            realmin = min;
            realmax = minavg;
        } else if (priceWeight < 0.5) {
            realmin = minavg;
            realmax = avg;
        } else if (priceWeight <= 0.75) {
            realmin = minavg;
            realmax = maxavg;
        } else {
            realmin = min;
            realmax = max;
        }

        return "and price between " + realmin + " and " + realmax;

    }


    public List<House> mahoutRecommend(Integer userId) {
        List<House> houses = new ArrayList<House>();

        try {
            List<Integer> list =  baseUserRecommender.recommendBasedOnUser(userId);
            for (Integer hid : list) {
                houses.add(findById(hid));
            }

        } catch (TasteException e) {
            e.printStackTrace();
        }

        return houses;

    }

}
