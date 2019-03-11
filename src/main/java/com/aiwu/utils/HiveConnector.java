package com.aiwu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HiveConnector {

    private final static String DRIVER="org.apache.hive.jdbc.HiveDriver";
    private final static String URL="jdbc:hive2://192.168.101.18:10000/default";
    private final static String ROOT="root";
    private final static String PASSWORD="xjtuse";

    public HiveConnector() {
    }

    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(DRIVER);//加载驱动
            conn =  DriverManager.getConnection(URL, ROOT, PASSWORD);//获取链接
            System.out.println("=====获取链接成功=====");
        } catch (Exception e) {
            System.out.println("=====获取链接失败=====");
            e.printStackTrace();
        }
        return conn;
    }
    public static  void closeConnection(Connection conn){
        try {
            System.out.println("=====关闭链接成功=====");
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();

        closeConnection(conn);
    }

}
