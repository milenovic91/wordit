package com.geniye.wordit.core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Author {
  private long id;
  private String username;
  private String bio;
  private String image;
  private boolean following;
}
