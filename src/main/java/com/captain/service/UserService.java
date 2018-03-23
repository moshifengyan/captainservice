package com.captain.service;

import com.captain.entity.gen.JsonResult;
import com.captain.entity.show.CaptainCommon;
import com.captain.entity.show.UserCommon;

/**
 * Created by zhangcanhui on 2018/3/22.
 */
public interface UserService {
    public JsonResult selectCaptain(Integer tjid, String name, String idcard, Integer lockstatus, String agent, Integer unionid, Integer limit, Integer offset);
    public CaptainCommon selectCaptainCommon(Integer captaintjid);
    public JsonResult<UserCommon> selectUserCommon(Integer tjid);
    public String insertCaptain(Integer captaintjid, String mobile, String phone1, String phone2, String phone3, Integer agenttjid, String companyname);
    public JsonResult<UserCommon> selectUser1(String str);
    public JsonResult<UserCommon> selectUser2(Integer tjid);
    public  boolean isChinaPhoneLegal(String str);
    public  boolean isNumeric(String str);
}
