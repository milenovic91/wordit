package com.geniye.wordit.core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Profile {
  private String username;
  private String bio;
  private String image;
  private boolean following;
}
