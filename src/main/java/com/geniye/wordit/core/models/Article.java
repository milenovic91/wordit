package com.geniye.wordit.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Article {
  private long id;
  private String slug;
  private String title;
  private String description;
  private String body;
  private Date createdAt;
  private Date updatedAt;

  // Calculated props
  private Author author;
  private boolean favorited;
  private int favoritesCount;

  public Article(String slug, String title, String description, String body) {
    this.slug = slug;
    this.title = title;
    this.description = description;
    this.body = body;
  }

  public void update(String title, String description, String body) {
    if (!"".equals(title)) {
      this.setTitle(title);
    }
    if (!"".equals(description)) {
      this.setDescription(description);
    }
    if (!"".equals(body)) {
      this.setBody(body);
    }
  }
}
