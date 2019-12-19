package com.geniye.wordit.db.mappers;

import com.geniye.wordit.core.models.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
  List<Article> getArticles(@Param("slug") String slug);
}
