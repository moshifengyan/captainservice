package com.captain.service;

import com.captain.entity.show.NeedCheck;

import java.util.List;
import java.util.Map;

/**
 * Created by 代天 on 2018/3/8.
 */
public interface UnionCheckService {

//    List<NeedCheck> needCheck(Integer unionId);
    Map<String,String> unionIdsAndNames();
    Long[] countUnionMember(String unionId);
}
