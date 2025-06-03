package com.lee.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lee
 * @since 2025-06-03
 */
@Getter
@Setter
@ToString
@TableName("article_tag")
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * tag_article中间表id
     */
    private Integer id;

    /**
     * 外键：article
     */
    private Integer articleId;

    /**
     * 外键： tag
     */
    private Integer tagId;
}
