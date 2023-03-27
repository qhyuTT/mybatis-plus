package com.qhyu.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qhyu.cloud.model.SkyworthUser;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * All rights Reserved, Designed By http://xnky.travelsky.net/ <br>
 * Title：UserInfoMapper <br>
 * Package：com.qhyu.cloud.mapper <br>
 * Copyright © 2023 xnky.travelsky.net Inc. All rights reserved. <br>
 * Company：Aviation Cares Of Southwest Chen Du LTD  <br>
 * @author 于琦海 <br>
 * date 2023年 03月24日 11:38 <br>
 * @version v1.0 <br>
 */
// 按照我的理解这个@Mapper可以不写，到时候再看看
@Mapper
@CacheNamespace
public interface UserInfoMapper extends BaseMapper<SkyworthUser> {

    List<SkyworthUser> getAll();
}
