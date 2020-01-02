package com.geniye.wordit.web;

import com.geniye.wordit.core.models.Profile;
import com.geniye.wordit.core.models.User;
import com.geniye.wordit.db.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/profiles")
public class ProfilesController {
  @Autowired
  ProfileRepository profileRepository;

  @RequestMapping(path = "/{username}", method = RequestMethod.GET)
  public ResponseEntity getProfile(@PathVariable("username") String username, @AuthenticationPrincipal User user) {
    return this.handleProfileResponse(user, username);
  }

  @RequestMapping(path = "/{username}/follow", method = RequestMethod.POST)
  public ResponseEntity follow(@PathVariable("username") String username, @AuthenticationPrincipal User user) {
    this.profileRepository.follow(user, username);
    return this.handleProfileResponse(user, username);
  }

  @RequestMapping(path = "/{username}/follow", method = RequestMethod.DELETE)
  public ResponseEntity unfollow(@PathVariable("username") String username, @AuthenticationPrincipal User user) {
    this.profileRepository.unfollow(user, username);
    return this.handleProfileResponse(user, username);
  }

  private ResponseEntity handleProfileResponse(User user, String username) {
    Profile p = this.profileRepository.getByUsername(user, username).get();
    if (p != null) {
      return ResponseEntity.ok(new HashMap<String, Profile>() {{
        put("profile", p);
      }});
    } else {
      return ResponseEntity.status(404).body(null);
    }
  }
}
