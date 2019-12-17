package com.geniye.wordit.db.mappers;

import com.geniye.wordit.core.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleRepository {
  @Autowired
  private ArticleMapper articleMapper;

  public List<Article> getArticles() {
    return this.articleMapper.getArticles();
  }
}
