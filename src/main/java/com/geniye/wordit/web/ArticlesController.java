package com.geniye.wordit.web;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.geniye.wordit.core.models.Article;
import com.geniye.wordit.core.models.User;
import com.geniye.wordit.db.ArticleRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.*;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
  @Autowired
  ArticleRepository articleRepository;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity getArticles(@RequestParam(value = "limit", defaultValue = "10") int limit,
                                   @RequestParam(value = "offset", defaultValue = "0") int offset,
                                   @RequestParam(value = "author", required = false) String author,
                                   @RequestParam(value = "favorited", required = false) String favoritedBy,
                                   @AuthenticationPrincipal User user) {
    List<Article> result = this.articleRepository.getArticles(user, author, favoritedBy, false);
    return ResponseEntity.ok(new HashMap<String, Object>(){{
      put("articles", result);
    }});
  }

  @RequestMapping(path = "/feed")
  public ResponseEntity getFeed(@RequestParam(value = "limit", defaultValue = "10") int limit,
                                @RequestParam(value = "offset", defaultValue = "0") int offset,
                                @AuthenticationPrincipal User user) {
    List<Article> result = this.articleRepository.getArticles(user, null, null, true);
    return ResponseEntity.ok(new HashMap<String, Object>(){{
      put("articles", result);
    }});
  }

  @RequestMapping(path = "/{slug}", method = RequestMethod.GET)
  public ResponseEntity getArticleBySlug(@PathVariable("slug") String slug, @AuthenticationPrincipal User user) {
    Optional<Article> article = this.articleRepository.getArticleBySlug(user, slug);
    if (article.isPresent()) {
      return ResponseEntity.status(200).body(new HashMap<String, Object>(){{
        put("article", article.get());
      }});
    } else {
      return ResponseEntity.status(404).body(null); // not found
    }
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Article> createArticle(@Valid @RequestBody CreateArticleBody body,
                              @AuthenticationPrincipal User user,
                              BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new RuntimeException(bindingResult.toString());
    }
    Article article = new Article(
      ensureUniqueSlug(body.getTitle()),
      body.getTitle(),
      body.getDescription(),
      body.getBody()
    );
    this.articleRepository.createArticle(user, article);
    return ResponseEntity.status(200).body(this.articleRepository.getArticleById(user, article.getId()).get());
  }

  @RequestMapping(path = "/{slug}", method = RequestMethod.PUT)
  public ResponseEntity updateArticle(@Valid @RequestBody UpdateArticleBody body,
                                               @PathVariable("slug") String slug,
                                               @AuthenticationPrincipal User user) {
    Article article = this.articleRepository.getArticleBySlug(user, slug).get();
    if (article != null) {
      article.update(body.getTitle(), body.getDescription(), body.getBody());
      int rowsAffected = this.articleRepository.updateArticle(user, article);
      if (rowsAffected == 0) {
        throw new RuntimeException("invalid args");
      }
      Article result = article;
      return ResponseEntity.status(200).body(new HashMap<String, Article>(){{
        put("article", result);
      }});
    } else {
      return ResponseEntity.status(404).body(null);
    }
  }

  @RequestMapping(path = "/{slug}", method = RequestMethod.DELETE)
  public ResponseEntity deleteArticle(@PathVariable("slug") String slug,
                                      @AuthenticationPrincipal User user) {
    int rowsAffected = this.articleRepository.deleteArticle(user, slug);
    if (rowsAffected == 0) {
      throw new RuntimeException("invalid request");
    }
    return ResponseEntity.status(200).body(null);
  }

  @RequestMapping(path = "/{slug}/favorite", method = RequestMethod.POST)
  public ResponseEntity favoriteArticle(@PathVariable("slug") String slug,
                                      @AuthenticationPrincipal User user) {
    int rowsAffected = this.articleRepository.favoriteArticle(user, slug);
    if (rowsAffected == 0) {
      throw new RuntimeException("invalid request");
    }
    return ResponseEntity.status(200).body(null);
  }

  @RequestMapping(path = "/{slug}/favorite", method = RequestMethod.DELETE)
  public ResponseEntity unfavoriteArticle(@PathVariable("slug") String slug,
                                        @AuthenticationPrincipal User user) {
    int rowsAffected = this.articleRepository.unfavoriteArticle(user, slug);
    if (rowsAffected == 0) {
      throw new RuntimeException("invalid request");
    }
    return ResponseEntity.status(200).body(null);
  }

  @RequestMapping(path = "/{slug}/comments", method = RequestMethod.GET)
  public ResponseEntity getArticleComments(@PathVariable("slug") String slug) {
    return ResponseEntity.status(200).body(new HashMap<String, Object>(){{
      put("comments", Collections.emptyList());
    }});
  }

  private String ensureUniqueSlug(String title) {
    String[] parts = title.toLowerCase().split("\\s+");
    // TODO - Ensure that slug is not taken!
    String slug = String.join("-", parts);
    int attempts = 0;
    while (this.articleRepository.getArticleBySlug(null, slug).isPresent() && attempts++ < 3) {
      slug = slug + Integer.toString(attempts);
    }
    return slug;
  }
}

@JsonRootName("article")
@Getter
class CreateArticleBody {
  @NotBlank
  private String title;
  private String description;
  @NotBlank
  private String body;
}

@JsonRootName("article")
@Getter
class UpdateArticleBody {
  private String title = "";
  private String description = "";
  private String body = "";
}