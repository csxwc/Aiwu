package com.aiwu.bean;

import javax.persistence.*;

@Entity
@Table(name = "house")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
