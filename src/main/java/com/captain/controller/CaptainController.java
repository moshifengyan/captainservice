package com.captain.controller;

import com.captain.entity.gen.JsonResult;
import com.captain.entity.show.NeedCheck;
import com.captain.entity.show.UnionCheck;
import com.captain.service.UnionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 代天 on 2018/3/8.
 */
@RestController
@RequestMapping("/union")
public class CaptainController {
    @Autowired
    UnionCheckService unionCheckService;

    @RequestMapping("/unioncheck")
    public JsonResult<List<UnionCheck>> unionCheck(){
        Map<String,String> idsAndNames =  unionCheckService.unionIdsAndNames();
        if(idsAndNames != null){
            Iterator<Map.Entry<String ,String>> entryIterator = idsAndNames.entrySet().iterator();
            List<UnionCheck> unionChecks = new ArrayList<>();
            while (entryIterator.hasNext()) {
                UnionCheck unionCheck = new UnionCheck();
                Map.Entry<String,String> entry = entryIterator.next();
                String id = entry.getKey();
                Long[] memberNum = unionCheckService.countUnionMember(id);
                unionCheck.setId(id);
                unionCheck.setOffical(memberNum[0]);
                unionCheck.setNeedcon(memberNum[1]);
                unionCheck.setAllmember(memberNum[2]);
                unionCheck.setName(entry.getValue());
                unionCheck.setType("调度联盟");
                unionChecks.add(unionCheck);
            }
            return  new JsonResult(unionChecks);
        }else
            return new JsonResult("没有查询到数据！");


    }

}



//    @RequestMapping("/unioncheck")
//    public JsonResult<List<CaptainUnion>> unionCheck(){
//      return new JsonResult(unionCheckService.AllCaptainUnion());
//    }
//
//    @RequestMapping("/needcheck")
//    public JsonResult<List<NeedCheck>> needCheckJsonResult(@RequestParam(value = "unionid") Integer unionId){
//        return new JsonResult(unionCheckService.needCheck(unionId));
//    }
//
//    @RequestMapping("/getunion")
//    public JsonResult<List<CaptainUnion>> getCaptainUnion(@RequestParam(value = "id") Integer id,@RequestParam(value = "type")String type,
//                                                      @RequestParam(value = "allcaptain") Integer allcaptain,@RequestParam(value = "offical") Integer offical){
//        CaptainUnionExample captainUnionExample = new CaptainUnionExample();
//        CaptainUnionExample.Criteria criteria = captainUnionExample.createCriteria();
//        if(id != 0){
//            criteria.andIdEqualTo(id);
//        }
//        if(!StringUtils.isEmpty(type)){
//            criteria.andTypeEqualTo(type);
//        }
//        if(allcaptain != 0){
//            criteria.andAllCaptainNumEqualTo(allcaptain);
//        }
//        if(offical!= 0){
//            criteria.andOfficalNumEqualTo(offical);
//        }
//        return new JsonResult(unionCheckService.getCaptainUnion(captainUnionExample));


