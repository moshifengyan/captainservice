package com.captain.mapper;


import com.captain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * Created by zhangcanhui on 2018/3/9.
 */
public class UserProvider {
    @Autowired
    UserService userService;

    //    public String findUser(Integer tjid,String name,String idcard,Integer status){
//        StringBuffer sql=new StringBuffer("select * from user where tjid like'"+tjid+"%'");
//        if(!name.equals("")){
//            sql.append(" and name like'"+name+"%'");
//        }
//        if(!idcard.equals("")){
//            sql.append(" and idcard='"+idcard+"'");
//        }
//        sql.append(" and status like'"+status+"%'");
//        return sql.toString();
//
//    }
    public String findUserAndCaptain(Integer tjid, String name, String idcard, Integer lockstatus, String agent, Integer unionid, Integer limit, Integer offset) {
        StringBuffer sql = new StringBuffer("select u.tjid,u.name,u.idcard,u.regtime,c.lockstatus," +
                "c.mobile,c.phone1,c.phone2,c.phone3,c.agenttjid,c.unionid from tb_user u inner join tb_captain c " +
                " on u.tjid=c.tjid  where 1=1");
        if (!StringUtils.isEmpty(tjid)) {
            sql.append(" and u.tjid like'%" + tjid + "%'");
        }
        if (!StringUtils.isEmpty(name)) {
            sql.append(" and name like'" + name + "%'");
        }
        if (!StringUtils.isEmpty(idcard)) {
            sql.append(" and idcard like'" + idcard + "%'");
        }
        if (!StringUtils.isEmpty(lockstatus)) {
            sql.append(" and c.lockstatus=" + lockstatus + "");
        }

        if (!StringUtils.isEmpty(agent)) {
            if (userService.isNumeric(agent) == true) {
                int agenttjid = Integer.parseInt(agent);
                sql.append(" and c.agenttjid like'%" + agenttjid + "%'");
            } else {
                sql.append(" and a.name like'%" + agent + "%'");
            }
        }
        if (!StringUtils.isEmpty(unionid)) {
            sql.append(" and unionid =" + unionid + "");
        }
        sql.append(" limit " + offset + "," + limit + "");   //分页显示  select * from table limit offset,limit where 1=1;
        return sql.toString();
    }

    public String selectUserAndCaptain(Integer captaintjid) {
        StringBuffer sql = new StringBuffer("select u.tjid,u.name,u.idcard,u.regtime,u.status," +
                "c.mobile,c.phone1,c.phone2,c.phone3,c.agenttjid,c.companyname,c.unionid from tb_user u inner join " +
                "tb_captain c on u.tjid=c.captaintjid where c.captaintjid=#{captaintjid}");
        return sql.toString();
    }

    public String updateCaptain(Integer captaintjid, String mobile, String phone1, String phone2, String phone3, String companyname) {
        StringBuffer sql = new StringBuffer("update tb_captain set ");
        if (!StringUtils.isEmpty(mobile)) {
            sql.append(" mobile='" + mobile + "'");
        }
        if (!StringUtils.isEmpty(phone1)) {
            sql.append(", phone1='" + phone1 + "'");
        }
        if (!StringUtils.isEmpty(phone2)) {
            sql.append(", phone2='" + phone2 + "'");
        }
        if (!StringUtils.isEmpty(phone3)) {
            sql.append(", phone3='" + phone3 + "'");
        }
        if (!StringUtils.isEmpty(companyname)) {
            sql.append(",companyname='" + companyname + "'");
        }
        sql.append("where captaintjid='" + captaintjid + "'");
        return sql.toString();
    }

}
