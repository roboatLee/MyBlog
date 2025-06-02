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
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关注主键
     */
    private Integer id;

    /**
     * 被关注者
     */
    private Integer userId;

    /**
     * 关注者
     */
    private Integer fanId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
