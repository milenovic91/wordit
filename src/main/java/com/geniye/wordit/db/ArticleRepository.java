package com.geniye.wordit.db;

import com.geniye.wordit.core.models.Article;
import com.geniye.wordit.db.mappers.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleRepository {
  @Autowired
  private ArticleMapper articleMapper;

  public List<Article> getArticles(String slug) {
    return this.articleMapper.getArticles(slug);
  }

  public Optional<Article> getArticleBySlug(String slug) {
    List<Article> result = this.articleMapper.getArticles(slug);
    if (result.size() == 1) {
      return Optional.of(result.get(0));
    }
    return Optional.empty();
  }
}
