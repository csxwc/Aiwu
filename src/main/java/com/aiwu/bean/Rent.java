package com.aiwu.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rent")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "start")
    private Date start;

    @Column(name = "end")
    private Date end;

    @Column(name = "room_id")
    private Integer room_id;

    @Column(name = "person_id")
    private Integer person_id;

    @Column(name = "score")
    private float score;

    @Column(name = "remark")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
