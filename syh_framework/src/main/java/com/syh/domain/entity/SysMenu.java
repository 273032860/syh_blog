package com.syh.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表
 *
 * @TableName sys_menu
 */
@TableName(value = "sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父菜单ID,一级菜单为0
     */
    private Long parentId;

    /**
     *
     */
    private String name;

    /**
     * 菜单URL
     */
    private String path;

    /**
     * 授权(例：sys:user:list)
     */
    private String perms;

    /**
     * 类型,0：目录;1：菜单;2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     *
     */
    private Date created;

    /**
     *
     */
    private Date updated;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
