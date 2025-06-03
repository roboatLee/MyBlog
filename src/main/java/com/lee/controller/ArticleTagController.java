package com.lee.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.entity.ArticleTag;
import com.lee.entity.Tag;
import com.lee.service.IArticleTagService;
import com.lee.service.ITagService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lee
 * @since 2025-06-03
 */
@RestController
@RequestMapping("/articleTag")
public class ArticleTagController {
    @Autowired
    private IArticleTagService articleTagService;
    @Autowired
    private ITagService tagService;
    @GetMapping("/getTagsByArticle/{id}")
    public List<Tag> getTagsByArticle(
            @PathVariable Integer id
    ){
        QueryWrapper<ArticleTag> queryWrapperT = new QueryWrapper<>();
        queryWrapperT.eq("article_id", id);
        List<ArticleTag> articleTags = articleTagService.list(queryWrapperT);

        List<Tag> tags = new ArrayList<>();
        for (ArticleTag articleTag : articleTags){
            Tag tag = tagService.getById(articleTag.getTagId());
            tags.add(tag);
        }
        return tags;
    }


}
