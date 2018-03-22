package com.captain.entity.show;

import java.util.Date;

/**
 * Created by 代天 on 2018/3/19.
 */
public class NeedCheck {
    String name;

    String mobile;

    String address;

    Date joinuninodate;

    Byte checkstatus;

    Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getJoinuninodate() {
        return joinuninodate;
    }

    public void setJoinuninodate(Date joinuninodate) {
        this.joinuninodate = joinuninodate;
    }

    public Byte getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Byte checkstatus) {
        this.checkstatus = checkstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
