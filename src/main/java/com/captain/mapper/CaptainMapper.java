package com.captain.mapper;

import com.captain.entity.gen.Agent;
import com.captain.entity.gen.Captain;
import com.captain.entity.gen.User;
import com.captain.entity.show.UserAndCaptain;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zhangcanhui on 2018/3/23.
 */
@Mapper
public interface CaptainMapper {
    @Select("select * from tb_user  where tjid=#{tjid}")
    User findUser(Integer tjid);

    @Select("select username from tb_user")
    List<User> selectUser();

    @Select("select * from tb_captain where captaintjid =#{captaintjid}")
    Captain findCaptain(Integer captaintjid);

    @Select("select * from tb_agent where tjid=#{tjid}")
    Agent findAgent(Integer tjid);

    @SelectProvider(type =UserProvider.class,method = "findUserAndCaptain")
    List<UserAndCaptain> findUserAndCaptain(Integer tjid, String name, String idcard, Integer lockstatus, String agent, Integer unionid, Integer limit, Integer offset);

    @SelectProvider(type = UserProvider.class,method = "selectUserAndCaptain")
    UserAndCaptain selectUserAndCaptain(Integer captaintjid);

    @UpdateProvider(type = UserProvider.class,method = "updateCaptain")
    void updateCaptain(Integer captaintjid, String mobile,String phone1,String phone2,String phone3,String companyname);

    @Select("insert into tb_captain(captaintjid,mobile,phone1,phone2,phone3,agenttjid,companyname,name,idcard)" +
            "values(#{captaintjid},#{mobile},#{phone1},#{phone2},#{phone3},#{agenttjid},#{companyname}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertCaptain(@Param("captaintjid") Integer captaintjid,@Param("mobile") String mobile,@Param("phone1") String phone1,@Param("phone2") String phone2,@Param("phone3") String phone3,
                       @Param("agenttjid") Integer agenttjid,@Param("companyname") String companyname);

    @Update("update tb_user set password='e10adc3949ba59abbe56e057f20f883e' where tjid=#{tjid}")
    void update(User user);

    @Insert("insert into tb_user(username,idcard,status) values(#{username},#{idcard},#{status}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(User user);
}
