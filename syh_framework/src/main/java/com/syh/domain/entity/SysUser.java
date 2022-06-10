package com.syh.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;
    /**
     *
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    private String nickname;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private Date created;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updated;

    //状态（0正常，1停用）
    private String status;

    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

}