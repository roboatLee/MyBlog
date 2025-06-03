package com.lee.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.entity.*;
import com.lee.entity.Users.TokenUserInfoDto;
import com.lee.entity.dto.ArticleDTO;
import com.lee.entity.dto.CommentDto;
import com.lee.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    private IFollowService followService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private IArticleTagService articleTagService;
    @RequestMapping("/loadDataList")
    public List<ArticleDTO> loadDataList(){
        List<Article> articles = articleService.list();
        List<ArticleDTO> articleDTOS = new ArrayList<>();

        for(Article article : articles){
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setTitle(article.getTitle());

            QueryWrapper<ArticleTag> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("article_id", article.getId());
            List<ArticleTag> articleTags = articleTagService.list(queryWrapper);
            List<Tag> tags = new ArrayList<>();
            for (ArticleTag articleTag : articleTags){
                Tag tag = tagService.getById(articleTag.getTagId());
                tags.add(tag);
            }
            articleDTO.setTags(tags);
            articleDTO.setCreateTime(article.getCreateTime());
            articleDTO.setId(article.getId());

            articleDTOS.add(articleDTO);
        }
        return articleDTOS;
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
        List<Comment> comments = commentService.list(queryWrapper);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments){
            CommentDto precomment = new CommentDto();
            precomment.setContent(comment.getContent());
            User commentUser = userService.getById(comment.getUserId());
            precomment.setNikename(commentUser.getNikename());
            precomment.setAvatarPosition(commentUser.getAvatarPosition());
            precomment.setUserId(comment.getUserId());
            commentDtos.add(precomment);
        }
        articleDTO.setComments(commentDtos);

        QueryWrapper<ArticleTag> queryWrapperT = new QueryWrapper<>();
        queryWrapperT.eq("article_id", article.getId());
        List<ArticleTag> articleTags = articleTagService.list(queryWrapperT);

        List<Tag> tags = new ArrayList<>();
        for (ArticleTag articleTag : articleTags){
            Tag tag = tagService.getById(articleTag.getTagId());
            tags.add(tag);
        }
        articleDTO.setTags(tags);

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

        Integer articleId = article.getId();
        ArticleTag articleTag = new ArticleTag();
        for (Tag tag : articleDTO.getTags()){
            ArticleTag articleTag1 = new ArticleTag();
            articleTag1.setArticleId(articleId);
            articleTag1.setTagId(tag.getId());
            articleTagService.save(articleTag1);
        }
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
        System.out.println();
//        List<ArticleTag> articleTags = new ArrayList<>();

        for ( Tag tag : articleDTO.getTags()){
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(id);
            articleTag.setTagId(tag.getId());
            articleTagService.save(articleTag);

        }
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

    @RequestMapping("/addConcern")
    public void addConcer(
            Integer userId,
            Integer fanId
    ){
        Follow follow = new Follow();
        follow.setUserId(userId);
        follow.setFanId(fanId);
        follow.setCreateTime(LocalDateTime.now());
        follow.setUpdateTime(LocalDateTime.now());
        followService.save(follow);
        return;
    }

    @RequestMapping("/getConcern")
    public List<User> getConcern(
            Integer userId
    ){

        List<User> users = new ArrayList<>();
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fan_id", userId);
        List<Follow> follows = followService.list(queryWrapper);

        for(Follow follow : follows){
            User user = new User();
            user = userService.getById(follow.getUserId());
            users.add(user);
        }
        return users;
    }
}
