package com.lee.entity.vo;

import lombok.Data;

/**
 * @author KitenLee
 * * @date 2025/6/3
 */
@Data
public class ResponseVo<T> {
    private String status;
    private Integer code;
    private String info;
    private T data;

}
