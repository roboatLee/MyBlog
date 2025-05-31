package com.lee.controller;

import com.lee.entity.Article;
import com.lee.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lee
 * @since 2025-05-31
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;
    @RequestMapping("/loadDataList")
    public List<Article> loadDataList(){
        return articleService.list();

    }

}
