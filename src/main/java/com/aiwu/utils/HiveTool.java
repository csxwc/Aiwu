package com.aiwu.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println(findOne("select * from house"));

    }

}
