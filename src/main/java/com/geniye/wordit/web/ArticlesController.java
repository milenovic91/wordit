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
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
  @Autowired
  ArticleRepository articleRepository;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity getArticles(@RequestParam(value = "limit", defaultValue = "10") int limit,
                                   @RequestParam(value = "offset", defaultValue = "0") int offset,
                                   @RequestParam(value = "author", required = false) String author,
                                   @AuthenticationPrincipal User user) {
    List<Article> result = this.articleRepository.getArticles(user, null);
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