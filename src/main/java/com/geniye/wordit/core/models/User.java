package com.geniye.wordit.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
  private long id;
  private String username;
  private String password;
  private String email;
  private String bio;
  private String image;
}
