package com.aiwu.bean;


import javax.persistence.*;

@Entity
@Table(name = "collection")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "room_id")
    private Integer roomId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPerson_id() {
        return personId;
    }

    public void setPerson_id(Integer person_id) {
        this.personId = person_id;
    }

    public Integer getRoom_id() {
        return roomId;
    }

    public void setRoom_id(Integer room_id) {
        this.roomId = room_id;
    }
}
