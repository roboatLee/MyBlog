package com.lee.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author KitenLee
 * * @date 2025/6/1
 */
@Data
public class ArticleDTO {
    private String title;
    private String htmlContent;
    private String markdownContent;
    private String userName;
    private LocalDateTime createTime;
}
