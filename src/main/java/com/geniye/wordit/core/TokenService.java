package com.geniye.wordit.core;

import com.geniye.wordit.core.models.User;
import java.util.Optional;

public interface TokenService {
  public String generateToken(User user);

  public Optional<String> extractUsername(String token);
}
