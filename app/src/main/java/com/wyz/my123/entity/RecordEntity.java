package com.wyz.my123.entity;

import java.util.Date;

public class RecordEntity {
    private Integer id;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 练习时间
     */
    private Date doDate;
    /**
     * 练习分数
     */
    private Integer doNumber;
    /**
     * 本次练习排名
     */
    private Integer rank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getDoDate() {
        return doDate;
    }

    public void setDoDate(Date doDate) {
        this.doDate = doDate;
    }

    public Integer getDoNumber() {
        return doNumber;
    }

    public void setDoNumber(Integer doNumber) {
        this.doNumber = doNumber;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "RecordEntity{" +
                "id=" + id +
                ", uid=" + uid +
                ", doDate=" + doDate +
                ", doNumber=" + doNumber +
                ", rank=" + rank +
                '}';
    }
}
