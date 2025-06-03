package com.lee.entity.dto;

import com.lee.entity.Comment;
import com.lee.entity.Tag;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author KitenLee
 * * @date 2025/6/1
 */
@Data
public class ArticleDTO {
    private Integer id;
    private String title;
    private String htmlContent;
    private String markdownContent;
    private String userName;
    private LocalDateTime createTime;
    private List<CommentDto> comments;
    private  List<Tag> tags;
}
