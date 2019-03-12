package com.aiwu.service;

import com.aiwu.bean.House;
import com.aiwu.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private HouseService houseService;


    public Connection getConn() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/aiwu?useUnicode=true&characterEncoding=gbk&serverTimezone=GMT";
        String username = "root";
        String password = "lizefeng1998";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public List<House> getAll(String s) {
        Connection conn = getConn();
        List<House> list = new ArrayList<>();
        String sql = s;
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            int index=0;
            System.out.println("============================");
            while (rs.next()) {
                index++;
                House h = houseService.findById(Integer.parseInt(rs.getString(1)));
                list.add(h);
//                System.out.println(rs.getString(1));
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
