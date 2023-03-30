package com.qhyu.cloud.service;

import com.qhyu.cloud.model.SkyworthUser;

/**
 * All rights Reserved, Designed By http://xnky.travelsky.net/ <br>
 * Title：UserService <br>
 * Package：com.qhyu.cloud.service <br>
 * Copyright © 2023 xnky.travelsky.net Inc. All rights reserved. <br>
 * Company：Aviation Cares Of Southwest Chen Du LTD  <br>
 * @author 于琦海 <br>
 * date 2023年 03月24日 11:36 <br>
 * @version v1.0 <br>
 */
public interface UserService {

    SkyworthUser getUserInfoById(String id);

    void updateId(String id);
}
