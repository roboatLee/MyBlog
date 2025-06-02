package com.lee.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lee
 * @since 2025-06-02
 */
@Getter
@Setter
@ToString
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论主键
     */
    private Integer id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论者
     */
    private Integer userId;

    /**
     * 评论的文章外键
     */
    private Integer articleId;
}
