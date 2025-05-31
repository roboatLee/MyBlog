package com.lee.entity.Users;

import lombok.Data;

import java.io.Serializable;

/**
 * @author KitenLee
 * * @date 2025/5/31
 */
@Data
public class TokenUserInfoDto implements Serializable {
    private String token;
    private Integer userId;
    private String nikeName;
}
