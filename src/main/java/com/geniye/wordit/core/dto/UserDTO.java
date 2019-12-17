package com.geniye.wordit.core.dto;

import com.geniye.wordit.core.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
  private long id;
  private String username;
  private String password;
  private String email;
  private String bio;
  private String image;
  private String token;

  public UserDTO(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.email = user.getEmail();
    this.bio = user.getBio();
    this.image = user.getImage();
  }
}
