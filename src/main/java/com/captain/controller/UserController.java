package com.captain.controller;

import com.captain.entity.gen.Captain;
import com.captain.entity.gen.JsonResult;
import com.captain.entity.gen.User;
import com.captain.entity.show.CaptainCommon;
import com.captain.entity.show.UserCommon;
import com.captain.mapper.CaptainMapper;
import com.captain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhangcanhui on 2018/3/5.
 */
@EnableTransactionManagement
@RestController       //@RestController=@Controller +@ResponseBody
@RequestMapping("/captain")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CaptainMapper captainMapper;
//    @RequestMapping("/select")
//    public User selectUser(@RequestParam("tjid") Integer tjid,@RequestParam("name") String name){
//        return userService.selectUser(tjid,name);
//    }
//    @RequestMapping("/select1")
//    public List<Result> findUser(@RequestParam("tjid") Integer tjid, @RequestParam("name") String name, @RequestParam("idcard")String idcard, @RequestParam("status")Integer status){
//        return userService.findUser(tjid,name,idcard,status);
//    }
//    @RequestMapping("/select2")
//    public List<UserAndCaptain> findUserAndCaptain(Integer tjid,String name,String idcard,Integer status,Integer agenttjid,Integer unionid){
//        return userMapper.findUserAndCaptain(tjid,name,idcard,status,agenttjid,unionid);
//    }
    @RequestMapping("/selectuser")      // 输入会员号或手机号查会员组合信息
    public JsonResult<UserCommon> selectUser(String str){
        if(str.length()==6){
            Integer tjid=Integer.parseInt(str);
            return userService.selectUser2(tjid);
        }else{
            return userService.selectUser1(str);
        }
    }

    @RequestMapping("/selectcaptaincommon") //查询车队长公共信息接口
    public CaptainCommon selectCaptainCommon(Integer captaintjid){
        CaptainCommon captainCommon= userService.selectCaptainCommon(captaintjid);
        return captainCommon;
    }

    @RequestMapping("/updatecaptain")  //动态更新车队长信息接口
    public JsonResult updateCaptain(Integer captaintjid,String mobile,String phone1,String phone2,String phone3,String companyname){
        //对参数的有效性进行检测（对tjid合法性进行检测，对手机号合法性（正则表达式）进行检测，对厂商名称进行检测）
        //if(tjid<1）返回错误信息
        if(captaintjid!=0&&(captaintjid<1||captaintjid>999999)){
            return new JsonResult(2,"输入会员号有误，请重新输入",null);
        }
        if(userService.isChinaPhoneLegal(mobile)==false){
            return new JsonResult(2,"输入会电话号码有误，请重新输入",null);
        }
        captainMapper.updateCaptain(captaintjid,mobile,phone1,phone2,phone3,companyname);
        return new JsonResult(1,"修改车队长信息成功","ok");
    }

    @RequestMapping("/selectusercommon")  //查询用户公共信息接口
    public JsonResult<UserCommon> selectUserCommon(Integer tjid){
        return userService.selectUserCommon(tjid);
    }

    @RequestMapping("/insertcaptain")   //注册(新增)车队长信息接口
    public String insertCaptain(Integer captaintjid,String mobile,String phone1,String phone2,String phone3,Integer agenttjid,String companyname){
        //数据表里面的值如果没写就设置一个默认值比如String phone1="";
        return userService.insertCaptain(captaintjid,mobile,phone1,phone2,phone3,agenttjid,companyname);
    }

    @RequestMapping("/selectcaptain")   //查询车队长信息接口
    public JsonResult<List> selectCaptain(Integer tjid,String name,String idcard,Integer lockstatus,String agent,Integer unionid,Integer limit,Integer offset) {
        //对参数的有效性进行检测（对tjid合法性进行检测，对手机号合法性（正则表达式）进行检测，对代理商名称进行检测）
        //if(tjid<1）返回错误信息
        if(tjid!=0&&(tjid<1||tjid>999999)){
            return new JsonResult<>(2,"输入会员号有误，请重新输入",null);
        }
        return userService.selectCaptain(tjid,name,idcard,lockstatus,agent,unionid,limit,offset);
    }

    @RequestMapping("/resetpassword")  //重置密码接口
    public String resetPassword(Integer tjid){
        User user= captainMapper.findUser(tjid);
        captainMapper.update(user);
        return "重置密码成功";
    }
    @RequestMapping("/statuslock")  //车队长账户锁定接口
    public Integer statusLock(Integer captaintjid){
        Integer lockstatus;    //车队长账户锁定状态
        Captain captain= captainMapper.findCaptain(captaintjid);
        if(captain.getLockstatus()==1){
            lockstatus=2;
        }else{
            lockstatus=1;
        }
        return lockstatus;
    }

//    @Transactional     //数据库事务回滚
//    @RequestMapping("/user/delete")
//    public long deletebyid(int id){
//        userMapper.delete(id);
//        int i = 3/0;
//        User user = new User();
//        user.setUsername("1111");
//        user.setIdcard("");
//        user.setStatus(1);
//        userMapper.insert(user);
//        return 1;
//    }
}
