package com.geniye.wordit.web;

import com.geniye.wordit.core.Utils;
import com.geniye.wordit.core.models.Profile;
import com.geniye.wordit.core.models.User;
import com.geniye.wordit.db.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {
  @Autowired
  ProfileRepository profileRepository;

  @RequestMapping(path = "/{username}", method = RequestMethod.GET)
  public Profile getProfile(@PathVariable("username") String username, @AuthenticationPrincipal User user) {
    return this.profileRepository.getByUsername(user, username).get();
  }
}
