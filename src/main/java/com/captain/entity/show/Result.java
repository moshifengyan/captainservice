package com.captain.entity.show;

import java.util.Date;

/**
 * Created by zhangcanhui on 2018/3/9.
 */
public class Result {
    private Integer captaintjid; //车队长通行证号
    private String name; //用户昵称
    private String idcard; //身份证号
    private Integer lockstatus; //'用户状态  1 正常 2 锁定
    private Date regtime;  //注册时间
    private String captainmobile; //车队长手机号码
    private Integer agenttjid;  //所属代理商通行证号
    private String agentmobile; //所属代理商手机号码
    private String agentname;// 所属代理商名称
    private Integer unionid; //天骄联盟id

    public Integer getCaptaintjid() {
        return captaintjid;
    }

    public void setCaptaintjid(Integer captaintjid) {
        this.captaintjid = captaintjid;
    }

    public String getCaptainmobile() {
        return captainmobile;
    }

    public void setCaptainmobile(String captainmobile) {
        this.captainmobile = captainmobile;
    }

    public Integer getAgenttjid() {
        return agenttjid;
    }

    public void setAgenttjid(Integer agenttjid) {
        this.agenttjid = agenttjid;
    }

    public String getAgentmobile() {
        return agentmobile;
    }

    public void setAgentmobile(String agentmbile) {
        this.agentmobile = agentmbile;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public Integer getUnionid() {
        return unionid;
    }

    public void setUnionid(Integer unionid) {
        this.unionid = unionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Integer getLockstatus() {
        return lockstatus;
    }

    public void setLockstatus(Integer lockstatus) {
        this.lockstatus = lockstatus;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }


}
