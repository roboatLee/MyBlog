package com.lee.controller;

import com.lee.entity.Tag;
import com.lee.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private ITagService tagService;
    @RequestMapping("/getTags")
    public List<Tag> getTags(){
        return  tagService.list();
    }
}
