package com.lee.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.entity.Article;
import com.lee.entity.Comment;
import com.lee.entity.User;
import com.lee.entity.Users.TokenUserInfoDto;
import com.lee.entity.dto.ArticleDTO;
import com.lee.service.IArticleService;
import com.lee.service.ICommentService;
import com.lee.service.IUserService;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lee
 * @since 2025-06-01
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICommentService commentService;
    @RequestMapping("/loadDataList")
    public List<Article> loadDataList(){
        return articleService.list();
    }

    @RequestMapping("/getArticleById/{id}")
    public ArticleDTO getArticleById(
            @PathVariable(value = "id") Integer id
    ){
        System.out.println(id);
        ArticleDTO articleDTO = new ArticleDTO();
        Article article =  articleService.getById(id);
        User user = userService.getById(article.getUserId());
        articleDTO.setHtmlContent(article.getHtmlContent());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setMarkdownContent(article.getMarkdownContent());
        articleDTO.setUserName(user.getNikename());
        articleDTO.setCreateTime(article.getCreateTime());
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", id);
        articleDTO.setComments(commentService.list(queryWrapper));
        return articleDTO;
    }

    @RequestMapping("/getArticleByUserId/{id}")
    public List<Article> getArticleByUserId(
            @PathVariable(value = "id") Integer id
    ){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        return articleService.list(queryWrapper);
    }
    @RequestMapping("/deleteById/{id}")
    public boolean  deleteById(
            @PathVariable(value = "id") Integer id
    ){
        return articleService.removeById(id);
    }

    @RequestMapping("/addArticle")
    public void addArticle(
            @RequestBody ArticleDTO articleDTO,
            @RequestHeader String  userInfoJson
            ) throws JsonProcessingException {
        System.out.println(userInfoJson);
        ObjectMapper objectMapper = new ObjectMapper();
        TokenUserInfoDto tokenUserInfoDto = objectMapper.readValue(userInfoJson, TokenUserInfoDto.class);


        Article article = new Article();
        article.setHtmlContent(articleDTO.getHtmlContent());
        article.setMarkdownContent(articleDTO.getMarkdownContent());
        article.setTitle(articleDTO.getTitle());
        article.setUserId(tokenUserInfoDto.getUserId());
        article.setCreateTime(LocalDateTime.now());
        articleService.save(article);
    }

    @RequestMapping("/updateArticle/{id}")
    public void updateArticle(
            @RequestBody ArticleDTO articleDTO,
            @PathVariable Integer id
    ){
        Article article = new Article();
        article.setId(id);
        article.setTitle(articleDTO.getTitle());
        article.setMarkdownContent(articleDTO.getMarkdownContent());
        article.setHtmlContent(articleDTO.getHtmlContent());
        article.setUpdateTime(LocalDateTime.now());
        articleService.updateById(article);
    }

    @RequestMapping("/searchArticle/{keyword}")
    public List<Article> searchArticle(
            @PathVariable String keyword
    ){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",keyword);
        return articleService.list(queryWrapper);
    }

    @RequestMapping("/editPersonal")
    public void editPersonal(
        String nikename,
        String personalSignature,
        String avaterPosition

    ){

    }
}
