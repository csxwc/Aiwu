package com.aiwu.utils;

import com.aiwu.bean.House;
import com.aiwu.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HiveTool {

    private static String sql = "";

    private static ResultSet res;

    public static void selectData(Statement stmt, String tableName)
            throws SQLException {
        sql = "select * from " + tableName + " limit 10";
        System.out.println("Running:" + sql);
        res = stmt.executeQuery(sql);
        System.out.println("执行" + sql + "运行结果:");
        while (res.next()) {
            System.out.println(res.getString(1) + "\t" + res.getString(2) + "\t" + res.getString(3));
        }
    }


    /**
     * @Description: 返回第一个搜到的结果，保存为map
     * @Param:
     * @return:
     * @Author: congregalis
     * @Date: 2019/3/1
     */
    public static Map<String, Object> findOne(String sql,String...strings) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveConnector.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            if(strings!=null){
                for (int i = 0; i < strings.length; i++) {
                    pstmt.setObject(i + 1, strings[i]);
                }
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                return toEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            HiveConnector.closeConnection(conn);
        }
        return null;
    }

    /**
     * @Description: 返回第一个搜到的string
     * @Param: [sql, strings]
     * @return: java.lang.String
     * @Author: congregalis
     * @Date: 2019/3/1
     */
    public static String findOneString(String sql,String...strings) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveConnector.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            if(strings!=null){
                for (int i = 0; i < strings.length; i++) {
                    pstmt.setObject(i + 1, strings[i]);
                }
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            HiveConnector.closeConnection(conn);
        }
        return null;
    }

    

    /**
     * @Description:
     * @Param: [rs]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: congregalis
     * @Date: 2019/3/1
     */
    private static Map<String, Object> toEntity(ResultSet rs) {

        House house = new House();

        Map<String, Object>map = new HashMap<String, Object>();
        try {
            ResultSetMetaData data = (ResultSetMetaData) rs.getMetaData();//获取 运行sql的查询字段
            for (int i = 1; i <= data.getColumnCount(); i++) {
                String columnName = data.getColumnName(i);
                map.put(columnName, rs.getObject(columnName));
                System.out.println(columnName + ":" +rs.getObject(columnName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static House toHouse(ResultSet rs) {

        House house = new House();

        Map<String, Object>map = new HashMap<String, Object>();
        try {
            ResultSetMetaData data = (ResultSetMetaData) rs.getMetaData();//获取 运行sql的查询字段
            for (int i = 1; i <= data.getColumnCount(); i++) {
                String columnName = data.getColumnName(i);
                map.put(columnName, rs.getObject(columnName));
                System.out.println(columnName + ":" +rs.getObject(columnName));
            }
            house.setId((Integer) map.get("house.hid"));
            house.setName((String) map.get("house.title"));
            house.setProvince((String) map.get("house.province"));
            house.setCity((String) map.get("house.city"));
            house.setWeidu((String) map.get("house.weidu"));
            house.setJingdu((String) map.get("house.jingdu"));
            house.setType((String) map.get("house.type"));
            house.setGuest((Integer) map.get("house.guest"));
            house.setRoom((Integer) map.get("house.bedroom"));
            house.setBed((Integer) map.get("house.bed"));
            house.setToilet((Integer) map.get("house.bathroom"));
            Double price = (Double) map.get("house.price");
            house.setPrice(price.floatValue());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return house;
    }

    private static Integer toHouseId(ResultSet rs) {

        Integer houseId = null;

        Map<String, Object>map = new HashMap<String, Object>();
        try {
            ResultSetMetaData data = (ResultSetMetaData) rs.getMetaData();//获取 运行sql的查询字段
            for (int i = 1; i <= data.getColumnCount(); i++) {
                String columnName = data.getColumnName(i);
                map.put(columnName, rs.getObject(columnName));
                System.out.println(columnName + ":" +rs.getObject(columnName));
            }
            houseId = (Integer) map.get("house.hid");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houseId;
    }

    /** 
     * @Description: 返回查到的houseid
     * @Param: [sql, strings] 
     * @return: java.util.List<java.lang.Integer> 
     * @Author: congregalis 
     * @Date: 2019/3/5 
     */
    public static List<Integer> findHouseIdList(String sql, String...strings) {

        List<Integer> list = new ArrayList<Integer>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveConnector.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            if(strings!=null){
                for (int i = 0; i < strings.length; i++) {
                    pstmt.setObject(i + 1, strings[i]);
                }
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(toHouseId(rs));//解析结果集
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            HiveConnector.closeConnection(conn);
        }
        return list;
        
    }
    
    public static List<House> findHouseList(String sql , String...strings ){
        List<House> list = new ArrayList<House>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveConnector.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            if(strings!=null){
                for (int i = 0; i < strings.length; i++) {
                    pstmt.setObject(i + 1, strings[i]);
                }
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(toHouse(rs));//解析结果集
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            HiveConnector.closeConnection(conn);
        }
        return list;
    }

    /**
     * @Description: 按sql语句返回一个list，strings为sql中的参数，list中元素为map
     * @Param: [sql, strings]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: congregalis
     * @Date: 2019/3/1
     */
    public static List<Map<String, Object>> findList(String sql , String...strings ){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveConnector.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            if(strings!=null){
                for (int i = 0; i < strings.length; i++) {
                    pstmt.setObject(i + 1, strings[i]);
                }
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(toEntity(rs));//解析结果集
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            HiveConnector.closeConnection(conn);
        }
        return list;
    }

    public static List<Map<String, Object>> findListByEqual(String table, String para, String condition) {

        String sql = "select * from " + table + " where " + para + " = ?";
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        list = findList(sql, condition);

        return list;
    }

    public static void main(String[] args) {

//        Connection conn = null;
//        Statement stat = null;

//        try {
//
//            conn = HiveConnector.getConnection();
//            stat = conn.createStatement();
//            stat.execute("use default ");
//            selectData(stat, "city");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            HiveConnector.closeConnection(conn);
//        }
        //System.out.println(findList("select sum(pid) from city where city like '%台%'"));
        //System.out.println(findListByEqual("city", "cid", "9"));
        System.out.println(findHouseIdList("select * from house where city like '%成都%' and price between 430.95795 and 530.95795 and booktime between 26 and 50 and (type like '%整%' or type like '%独立房间%' or type like '%合住房间%') and guest-bed < 5"));

    }

}
