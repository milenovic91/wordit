package com.geniye.wordit.db;

import com.geniye.wordit.core.models.Article;
import com.geniye.wordit.core.models.User;
import com.geniye.wordit.db.mappers.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleRepository {
  @Autowired
  private ArticleMapper articleMapper;

  public List<Article> getArticles(User user, String author, String favoritedBy, boolean feed) {
    Long userId = user != null ? user.getId() : null;
    return this.articleMapper.getArticles(userId, null, null, author, favoritedBy, feed);
  }

  public Optional<Article> getArticleBySlug(User user, String slug) {
    List<Article> result = this.articleMapper.getArticles(user != null ? user.getId() : null, slug, null, null, null, false);
    if (result.size() == 1) {
      return Optional.of(result.get(0));
    }
    return Optional.empty();
  }

  public Optional<Article> getArticleById(User user, Long id) {
    List<Article> result = this.articleMapper.getArticles(user != null ? user.getId() : null, null, id, null, null, false);
    if (result.size() == 1) {
      return Optional.of(result.get(0));
    }
    return Optional.empty();
  }

  public void createArticle(User user, Article article) {
    this.articleMapper.createArticle(user.getId(), article);
  }

  public int updateArticle(User user, Article article) {
    return this.articleMapper.updateArticle(user.getId(), article);
  }

  public int deleteArticle(User user, String slug) {
    return this.articleMapper.deleteArticle(user.getId(), slug);
  }

  public int favoriteArticle(User user, String slug) {
    return this.articleMapper.favoriteArticle(user.getId(), slug);
  }

  public int unfavoriteArticle(User user, String slug) {
    return this.articleMapper.unfavoriteArticle(user.getId(), slug);
  }
}
