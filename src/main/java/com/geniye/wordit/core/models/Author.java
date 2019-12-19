package com.geniye.wordit.core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Author {
  private String username;
  private boolean following;
}
