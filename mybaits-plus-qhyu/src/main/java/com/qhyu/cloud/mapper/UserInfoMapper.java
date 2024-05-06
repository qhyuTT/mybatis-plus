package com.qhyu.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qhyu.cloud.model.SkyworthUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cursor.Cursor;


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
//@CacheNamespace
public interface UserInfoMapper extends BaseMapper<SkyworthUser> {

    IPage<SkyworthUser> getAll(IPage<SkyworthUser> page);

    //@Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = 100)
    Cursor<SkyworthUser> getAll();

    void updateId(@Param("id") String id,@Param("flag") int flag);
}
