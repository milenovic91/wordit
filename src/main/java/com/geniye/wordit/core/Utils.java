package com.geniye.wordit.core;

import org.springframework.security.core.Authentication;

public class Utils {
  public static String usernameFromAuthentication(Authentication auth) {
    return auth == null ? null : auth.getName();
  }
}
