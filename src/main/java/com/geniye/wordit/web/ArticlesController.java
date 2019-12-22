package com.geniye.wordit.web;

import com.geniye.wordit.core.models.Article;
import com.geniye.wordit.core.models.User;
import com.geniye.wordit.db.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
  @Autowired
  ArticleRepository articleRepository;

  @RequestMapping(method = RequestMethod.GET)
  public List<Article> getArticles(@RequestParam(value = "limit", defaultValue = "10") int limit,
                                   @RequestParam(value = "offset", defaultValue = "0") int offset,
                                   @RequestParam(value = "author", required = false) String author,
                                   @AuthenticationPrincipal User user) {
    return this.articleRepository.getArticles(user, null);
  }

  @RequestMapping(path = "/{slug}", method = RequestMethod.GET)
  public ResponseEntity getArticleBySlug(@PathVariable("slug") String slug, @AuthenticationPrincipal User user) {
    Optional<Article> article = this.articleRepository.getArticleBySlug(user, slug);
    if (article.isPresent()) {
      return ResponseEntity.status(200).body(article.get());
    } else {
      return ResponseEntity.status(404).body(null); // not found
    }
  }

  private String currentUser(Authentication auth) {
    return auth == null ? null : auth.getName();
  }
}
