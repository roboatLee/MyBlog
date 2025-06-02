package com.lee.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2025-05-31
 */
@Getter
@Setter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 昵称
     */
    private String nikename;

    /**
     * 密码
     */
    private String  password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 个性签名
     */
    private  String personalSignature;


    /**
     * 经验
     */
    private  Integer experience;

    /**
     * 头像位置
     */
    private  Integer avatarPosition;


}
