package com.aiwu.bean;

import javax.persistence.*;

@Entity
@Table(name = "lend")
public class Lend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "person_id")
    private Integer person_id;

    @Column(name = "room_id")
    private Integer room_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }
}
