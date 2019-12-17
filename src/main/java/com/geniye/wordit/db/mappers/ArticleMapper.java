package com.geniye.wordit.db.mappers;

import com.geniye.wordit.core.models.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ArticleMapper {
  @Select(
    "<script>" +
    "select * " +
    "from articles" +
    "</script>"
  )
  List<Article> getArticles();
}
