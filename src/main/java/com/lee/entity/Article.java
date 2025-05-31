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
 * @since 2025-05-31
 */
@Getter
@Setter
@ToString
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * html内容
     */
    private String htmlContent;

    /**
     * UserId外键
     */
    private String userId;

    /**
     * markdown内容
     */
    private String markdownContent;

    /**
     * 1:草稿 2：已发布
     */
    private Byte status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更改时间
     */
    private LocalDateTime updateTime;
}
