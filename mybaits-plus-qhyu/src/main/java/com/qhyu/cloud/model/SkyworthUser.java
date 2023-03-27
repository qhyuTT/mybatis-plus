package com.qhyu.cloud.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * All rights Reserved, Designed By http://xnky.travelsky.net/ <br>
 * Title：SkyworthUser <br>
 * Package：com.qhyu.cloud.model <br>
 * Copyright © 2023 xnky.travelsky.net Inc. All rights reserved. <br>
 * Company：Aviation Cares Of Southwest Chen Du LTD  <br>
 * @author 于琦海 <br>
 * date 2023年 03月24日 11:32 <br>
 * @version v1.0 <br>
 */
@Data
@TableName("skyworth_user")
public class SkyworthUser {

    @TableId(value = "ID")
    private String id;

    /** 用户账号 */
    @TableField(value = "ACCOUNT")
    private String account;

    /** 密码 */
    @TableField(value = "PASSWORD")
    private String password;

    /** 姓名 */
    @TableField(value = "NAME")
    private String name;

    /** 部门 格式：1,2*/
    @TableField(value = "DEPARTMENT")
    private String department;

    /** 职位 */
    @TableField(value = "POSITION")
    private String position;

    /**头像url*/
    @TableField(value = "AVATAR_URL")
    private String avatarUrl;

    /**邮件地址*/
    @TableField(value = "EMAIL")
    private String email;

    /** 电话 */
    @TableField(value = "PHONE")
    private String phone;

    /** 备注 */
    @TableField(value = "REMARK")
    private String remark;

    /** 员工在当前开发者企业账号范围内的唯一标识，系统生成，固定值，不会改变 */
    @TableField(value = "UNIONID")
    private String unionid;

    /** 性别 */
    @TableField(value = "SEX")
    private String sex;

    /** 最后登录时间 */
    @TableField(value = "LAST_LOGIN_TIME")
    private String lastLoginTime;

    /** 账号状态 1 启用、0 禁用*/
    @TableField(value = "STATUS")
    private String status;

    /** 创建时间 */
    @TableField(value = "CREATETIME")
    private Date createTime;

    /** 最近修改时间 */
    @TableField(value = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 是否首次登陆(0否,1是)
     */
    @TableField(value = "IS_FIRST_LOGIN")
    private Integer isFirstLogin;
}
