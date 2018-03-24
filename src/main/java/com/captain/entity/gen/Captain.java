package com.captain.entity.gen;

/**
 * Created by zhangcanhui on 2018/3/6.
 */
public class Captain {  //车队长实体信息表
    private Integer tjid;
    private Integer lockstatus;  //车队长账户锁定状态
    private String mobile;
    private String phone1;
    private String phone2;
    private String phone3;
    private Integer agenttjid;  //所属代理商通行证号
    private String companyname;

    public Integer getTjid() {
        return tjid;
    }

    public void setTjid(Integer tjid) {
        this.tjid = tjid;
    }

    public Integer getAgenttjid() {
        return agenttjid;
    }

    public void setAgenttjid(Integer agenttjid) {
        this.agenttjid = agenttjid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Integer getLockstatus() {
        return lockstatus;
    }
    public void setLockstatus(Integer lockstatus) {
        this.lockstatus = lockstatus;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }


}
