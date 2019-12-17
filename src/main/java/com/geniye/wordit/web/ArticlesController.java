package com.geniye.wordit.web;

import com.geniye.wordit.core.models.Article;
import com.geniye.wordit.db.mappers.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
  @Autowired
  ArticleRepository articleRepository;

  @RequestMapping(method = RequestMethod.GET)
  public List<Article> getArticles(@RequestParam(value = "limit", defaultValue = "10") int limit,
                                   @RequestParam(value = "offset", defaultValue = "0") int offset,
                                   @RequestParam(value = "author", required = false) String author) {
    return this.articleRepository.getArticles();
  }
}
