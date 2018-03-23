package com.captain.service.Impl;

import com.captain.entity.gen.Agent;
import com.captain.entity.gen.Captain;
import com.captain.entity.gen.JsonResult;
import com.captain.entity.gen.User;
import com.captain.entity.show.CaptainCommon;
import com.captain.entity.show.Result;
import com.captain.entity.show.UserAndCaptain;
import com.captain.entity.show.UserCommon;
import com.captain.mapper.CaptainMapper;
import com.captain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by zhangcanhui on 2018/3/8.
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    CaptainMapper captainMapper;

//    public User selectUser(Integer tjid, String name) {
//        return userMapper.selectUser(tjid, name);
//    }

//    public List<Result> findUser(Integer tjid, String name, String idcard, Integer status) {
//        List<User> list = userMapper.findUser1(tjid, name, idcard, status);
//        List<Result> list1 = new ArrayList<>();  //必须对集合类分配内存(需要new出来)才能使用
//        for (User user : list) {
//            if(user!=null) {
//                Result result = new Result();
//                result.setCaptain_tjid(user.getTjid());
//                Captain captain = userMapper.findCaptain(user.getTjid());
//                Agent agent = userMapper.findAgent(captain.getAgenttjid());
//                result.setName(user.getName());
//                result.setIdcard(user.getIdcard());
//                result.setRegtime(user.getRegtime());
//                result.setStatus(user.getStatus());
//                result.setAgent_tjid(captain.getAgenttjid());
//                result.setAgent_mobile(agent.getMobile());
//                result.setAgent_name(agent.getName());
//                list1.add(result);
//            }
//        }
//        return list1;
//    }
    public JsonResult selectCaptain(Integer tjid, String name, String idcard, Integer lockstatus, String agent, Integer unionid, Integer limit, Integer offset){
        //判断带参数有效性检测
        List<UserAndCaptain> list1= captainMapper.findUserAndCaptain(tjid,name,idcard,lockstatus,agent,unionid,limit,offset);
        List<Result> list2=new ArrayList<>();
        for(UserAndCaptain userAndCaptain:list1){
            if(userAndCaptain!=null){
                Result result=new Result();
                result.setCaptaintjid(userAndCaptain.getTjid());
                result.setName(userAndCaptain.getName());
                result.setIdcard(userAndCaptain.getIdcard());
                result.setLockstatus(userAndCaptain.getLockstatus());
                result.setCaptainmobile(userAndCaptain.getMobile());
                result.setRegtime(userAndCaptain.getRegtime());
                result.setUnionid(userAndCaptain.getUnionid());
                result.setAgenttjid(userAndCaptain.getAgenttjid());
                User user= captainMapper.findUser(userAndCaptain.getAgenttjid());
                if(user!=null)
                    result.setName(user.getName());
                Agent agent1= captainMapper.findAgent(userAndCaptain.getAgenttjid());
                if(agent1!=null)
                    result.setAgentmobile(agent1.getMobile());
                list2.add(result);

                
            }
        }
        return new JsonResult(1,"ok",list2);
    }
    public CaptainCommon selectCaptainCommon(Integer captaintjid){
        UserAndCaptain userAndCaptain = captainMapper.selectUserAndCaptain(captaintjid);
        CaptainCommon captainCommon=new CaptainCommon();
        if(userAndCaptain!=null){
            captainCommon.setCaptaintjid(userAndCaptain.getTjid());
            captainCommon.setName(userAndCaptain.getName());
            captainCommon.setIdcard(userAndCaptain.getIdcard());
            captainCommon.setCompanyname(userAndCaptain.getCompanyname());
            captainCommon.setMobile(userAndCaptain.getMobile());
            captainCommon.setPhone1(userAndCaptain.getPhone1());
            captainCommon.setPhone2(userAndCaptain.getPhone2());
            captainCommon.setPhone3(userAndCaptain.getPhone3());
        }
        return captainCommon;
    }

    public JsonResult<UserCommon> selectUserCommon(Integer tjid){
        Captain captain= captainMapper.findCaptain(tjid);
        if(captain==null){
            UserCommon userCommon=new UserCommon();
            User user= captainMapper.findUser(tjid);
            userCommon.setTjid(user.getTjid());
            userCommon.setName(user.getName());
            userCommon.setIdcard(user.getIdcard());
            return new JsonResult<>(1,"返回用户公共信息",userCommon);
        }else{
            return new JsonResult<>(2,"该用户已注册，请重新输入会员号");
        }
    }

    public String insertCaptain(Integer captaintjid,String mobile,String phone1,String phone2,String phone3,Integer agenttjid,String companyname){
        Captain captain= captainMapper.findCaptain(captaintjid);
        if(captain==null){
            captainMapper.insertCaptain(captaintjid,mobile,phone1,phone2,phone3,agenttjid,companyname);
            return "插入车队长信息成功！";
        }else{
            return "该会员号已注册，请重新输入会员号!";
        }
    }
    public  boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {  //判断电话号码的规范
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public JsonResult<UserCommon> selectUser1(String str){
        List<User> list= captainMapper.selectUser();
        for(User user:list){
            String[] strings=user.getUsername().split("\\.");
            if(strings[2].equals(str)){
                Integer tjid=Integer.parseInt(strings[1]);
                User user1= captainMapper.findUser(tjid);
                if(user1!=null){
                    UserCommon userCommon=new UserCommon();
                    userCommon.setName(user1.getName());
                    userCommon.setTjid(user1.getTjid());
                    userCommon.setMobile(str);
                    return new JsonResult<>(1,"ok",userCommon);
                }
            }
        }
        return new JsonResult<>(2,"输入的手机号码有误，请重新输入",null);
    }

    public JsonResult<UserCommon> selectUser2(Integer tjid){
        UserCommon userCommon=new UserCommon();
        User user= captainMapper.findUser(tjid);
        if(user!=null) {
            String[] strings=user.getUsername().split("\\.");
            userCommon.setTjid(user.getTjid());
            userCommon.setName(user.getName());
            userCommon.setMobile(strings[2]);
            return new JsonResult<>(1,"ok",userCommon);
        }
        return new JsonResult<>(2,"输入的会员号有误，请重新输入",null);
    }

    public  boolean isNumeric(String str) {   //判断一个字符串是否为数字
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
