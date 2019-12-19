package com.geniye.wordit.core.models;

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
}
