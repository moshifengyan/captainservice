package com.captain.service.Impl;

import com.captain.entity.gen.Union;
import com.captain.entity.gen.UnionMemberExample;
import com.captain.entity.show.NeedCheck;
import com.captain.mapper.UnionMapper;
import com.captain.mapper.UnionMemberMapper;
import com.captain.service.UnionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 代天 on 2018/3/8.
 */
@Service
public class UnionCheckServiceImpl implements UnionCheckService {

    @Autowired
    UnionMapper unionMapper;

    @Autowired
    UnionMemberMapper unionMemberMapper;
    @Override
    public Map<String,String> unionIdsAndNames(){
        Map<String,String> idsAndNames = new HashMap<>();
        List<Union> unions = unionMapper.getAllUnion();
        for (Union un: unions) {
           idsAndNames.put(un.getId(),un.getName());
        }
        return idsAndNames;
    }

    @Override
    public Long[] countUnionMember(String unionId){
        UnionMemberExample unionMemberExample1 = new UnionMemberExample();
        UnionMemberExample unionMemberExample2 = new UnionMemberExample();
        Byte status1 = 0;//待审核成员
        Byte status2 = 1;//正式成员
        unionMemberExample1.createCriteria().andUnionidEqualTo(unionId).andStatusEqualTo(status1);
        unionMemberExample2.createCriteria().andUnionidEqualTo(unionId).andStatusEqualTo(status2);
        Long offical = unionMemberMapper.countByExample(unionMemberExample2);//正式成员数量
        Long needCon = unionMemberMapper.countByExample(unionMemberExample1);//待审核成员数量
        Long []memberNum = new Long [3];
        memberNum[0]=offical;//正式成员数量
        memberNum[1]=needCon;//待审核成员数量
        memberNum[2]=offical+needCon;//所有成员数量
        return memberNum;
    }
//    @Override
//    public List<NeedCheck> needCheck(Integer unionId){
//        List<NeedCheck> needChecks = new ArrayList<>();
//       List<Captain> captainList =  captainMapper.getCaptainListByUnionId(unionId);
//        if(captainList != null){
//            for (Captain ca: captainList) {
//                NeedCheck needCheck = new NeedCheck();
//                needCheck.setName(ca.getCompanyname());
//                needCheck.setMobile(ca.getMobile());
//                needCheck.setAddress(ca.getAddress());
//                needCheck.setJoinuninodate(ca.getJoinuniondate());
//                needCheck.setLockstatus(ca.getLockstatus());
//                needCheck.setId(ca.getId());
//                needChecks.add(needCheck);
//            }
//        }
//        return needChecks;
    }

//    @Override
//    public List<Union>getUnionByName(){
//        UnionExample unionExample = new UnionExample();
//
//    }

