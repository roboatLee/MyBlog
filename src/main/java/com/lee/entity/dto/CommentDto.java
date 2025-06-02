package com.lee.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author KitenLee
 * * @date 2025/6/2
 */
@Data
public class CommentDto {
    /**
     * 评论内容
     */
    private String content;

    /**
     * 昵称
     */
    private String nikename;
    /**
     * 头像位置
     */
    private  String avatarPosition;
    /**
     * id
     */
    private Integer userId;
}
