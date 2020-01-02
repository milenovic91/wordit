package com.geniye.wordit.db;

import com.geniye.wordit.core.models.Profile;
import com.geniye.wordit.core.models.User;
import com.geniye.wordit.db.mappers.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileRepository {
  @Autowired
  ProfileMapper profileMapper;
  @Autowired
  UserRepository userRepository;

  public Optional<Profile> getByUsername(User user, String username) {
    return Optional.ofNullable(this.profileMapper.getByUsername(user != null ? user.getId() : null, username));
  }

  public int follow(User user, String username) {
    User u = this.userRepository.findByUsername(username).get();
    if (u != null) {
      int ret = this.profileMapper.follow(user.getId(), u.getId());
      System.out.println(ret);
    }
    return 0;
  }

  public int unfollow(User user, String username) {
    User u = this.userRepository.findByUsername(username).get();
    if (u != null) {
      int ret = this.profileMapper.unfollow(user.getId(), u.getId());
      System.out.println(ret);
    }
    return 0;
  }
}
