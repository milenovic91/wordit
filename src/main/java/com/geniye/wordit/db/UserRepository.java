package com.geniye.wordit.db;

import com.geniye.wordit.core.models.User;
import com.geniye.wordit.db.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class UserRepository {
  private UserMapper userMapper;

  @Autowired
  public void UserRepository(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public void save(User user) {
    try {
      this.userMapper.save(user);
    } catch (Exception e) {
      // TODO - do something with exceptions
      throw e;
    }
  }

  public Optional<User> findById(int id) {
    return Optional.ofNullable(this.userMapper.findById(id));
  }

  public Optional<User> findByUsername(String username) {
    return Optional.ofNullable(this.userMapper.findByUsername(username));
  }

  public Optional<User> findByEmail(String email) {
    return Optional.ofNullable(this.userMapper.findByEmail(email));
  }
}
