package com.geniye.wordit.db.mappers;

import com.geniye.wordit.core.models.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ArticleMapper {
  List<Article> getArticles(@Param("currentUser") Long currentUser,
                            @Param("slug") String slug,
                            @Param("id") Long id,
                            @Param("author") String author,
                            @Param("favoritedBy") String favoritedBy,
                            @Param("feed") boolean feed);

  void createArticle(@Param("currentUser") Long authorId, @Param("article") Article article);

  int updateArticle(@Param("currentUser") Long user, @Param("article") Article article);

  int deleteArticle(@Param("currentUser") Long user, @Param("slug") String slug);

  int favoriteArticle(@Param("currentUser") Long user, @Param("slug") String slug);

  int unfavoriteArticle(@Param("currentUser") Long user, @Param("slug") String slug);
}
