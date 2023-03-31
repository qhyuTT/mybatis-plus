package com.qhyu.cloud.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By http://xnky.travelsky.net/ <br>
 * Title：AirBase <br>
 * Package：com.qhyu.cloud.model <br>
 * Copyright © 2023 xnky.travelsky.net Inc. All rights reserved. <br>
 * Company：Aviation Cares Of Southwest Chen Du LTD  <br>
 * @author 于琦海 <br>
 * date 2023年 03月31日 15:51 <br>
 * @version v1.0 <br>
 */
@Data
@TableName("skyworth_air_base")
public class AirBase implements Serializable {
    private static final long serialVersionUID = -746010809972598747L;

    @TableId(value = "id")
    private String id;
    /**机型*/
    @TableField(value = "air_type")
    private String airType;
    /**基地code*/
    @TableField(value = "base_code")
    private String baseCode;
    /**基地*/
    @TableField(value = "base_remark")
    private String baseRemark;
    /**省份*/
    @TableField(value = "province")
    private String province;
    /**是否默认*/
    @TableField(value = "is_default")
    private int isDefault;
}
