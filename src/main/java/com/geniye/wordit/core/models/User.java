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

  public User update(String email, String password, String bio, String image) {
    if (!"".equals(email)) {
      this.setEmail(email);
    }
    if (!"".equals(password)) {
      this.setPassword(password);
    }
    if (!"".equals(bio)) {
      this.setBio(bio);
    }
    if (!"".equals(image)) {
      this.setImage(image);
    }
    return this;
  }
}
