package com.syh.domain.vo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    /**
     *
     */
    @TableId
    private Long id;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 根评论
     */
    private Long rootId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 所回复的目标评论的userid
     */
    private Long toComUserId;

    /**
     * 回复目标评论id
     */
    private Long toComId;

    /**
     *
     */
    private Long createBy;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Long updateBy;

    /**
     *
     */
    private Date updateTime;

    private String username;

    private String toComUserName;

    private List<CommentVo> children;
}
