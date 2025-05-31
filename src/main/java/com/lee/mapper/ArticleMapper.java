package com.lee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2025-05-31
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
