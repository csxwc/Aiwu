package com.aiwu.bean;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Table(name = "house")
public class House {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "jingdu")
    private String jingdu;

    @Column(name = "weidu")
    private String weidu;

    @Column(name = "name")
    private String name;

    @Column(name  = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "type")
    private String type;

    @Column(name = "guest")
    private Integer guest;

    @Column(name = "bedroom")
    private Integer room;

    @Column(name = "bed")
    private Integer bed;

    @Column(name = "toilet")
    private Integer toilet;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "picture")
    private String picture;

    @Column(name = "price")
    private float price;

    @Column(name = "booktime")
    private int booktime;

    public House(String jingdu, String weidu, String name, String province, String city, String type, Integer guest, Integer room, Integer bed, Integer toilet, String introduction, String picture, float price) {
        this.id = 12332;
        this.jingdu = jingdu;
        this.weidu = weidu;
        this.name = name;
        this.province = province;
        this.city = city;
        this.type = type;
        this.guest = guest;
        this.room = room;
        this.bed = bed;
        this.toilet = toilet;
        this.introduction = introduction;
        this.picture = picture;
        this.price = price;
        this.booktime = 0;
    }

    public House()
    {

    }
    public int getBooktime() {
        return booktime;
    }

    public void setBooktime(int booktime) {
        this.booktime = booktime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setJingdu(String jingdu) {
        this.jingdu = jingdu;
    }

    public void setWeidu(String weidu) {
        this.weidu = weidu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGuest(Integer guest) {
        this.guest = guest;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public void setBed(Integer bed) {
        this.bed = bed;
    }

    public void setToilet(Integer toilet) {
        this.toilet = toilet;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getJingdu() {
        return jingdu;
    }

    public String getWeidu() {
        return weidu;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }

    public Integer getGuest() {
        return guest;
    }

    public Integer getRoom() {
        return room;
    }

    public Integer getBed() {
        return bed;
    }

    public Integer getToilet() {
        return toilet;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getPicture() {
        return picture;
    }

    public float getPrice() {
        return price;
    }
}
