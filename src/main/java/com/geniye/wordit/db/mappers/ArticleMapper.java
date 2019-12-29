package com.geniye.wordit.db.mappers;

import com.geniye.wordit.core.models.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ArticleMapper {
  List<Article> getArticles(@Param("currentUser") Long currentUser,
                            @Param("slug") String slug,
                            @Param("id") Long id);

  void createArticle(@Param("currentUser") Long authorId, @Param("article") Article article);
}
