package com.qhyu.cloud.service.impl;

import com.qhyu.cloud.mapper.UserInfoMapper;
import com.qhyu.cloud.model.SkyworthUser;
import com.qhyu.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * All rights Reserved, Designed By http://xnky.travelsky.net/ <br>
 * Title：UserServiceImpl <br>
 * Package：com.qhyu.cloud.service.impl <br>
 * Copyright © 2023 xnky.travelsky.net Inc. All rights reserved. <br>
 * Company：Aviation Cares Of Southwest Chen Du LTD  <br>
 * @author 于琦海 <br>
 * date 2023年 03月24日 11:39 <br>
 * @version v1.0 <br>
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    //@Transactional
    public SkyworthUser getUserInfoById(String id){
        //userInfoMapper.selectById(id);
        return userInfoMapper.selectById(id);
    }

    @Override
    public void updateId(String id,int flag) {
        // 这个玩意就是一个代理对象，然后调用的时候会使用代理对象
        userInfoMapper.updateId(id,flag);
    }

}
