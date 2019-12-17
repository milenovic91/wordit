package com.geniye.wordit.web.security;

import com.geniye.wordit.core.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {
  @Autowired
  private TokenService tokenService;

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
    this.extractToken(httpServletRequest).ifPresent(token -> {
      this.tokenService.extractUsername(token).ifPresent(username -> {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
          Authentication auth = new UsernamePasswordAuthenticationToken(
              null, // TODO - is it required ? What is used for?
              null,
              Collections.emptyList()
          );
          /**
           * TODO - Do I need next line?
           * authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
           */
          SecurityContextHolder.getContext().setAuthentication(auth);
        }
      });
    });

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  private Optional<String> extractToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    String token = null;
    if (header != null) {
      if (header.startsWith("Bearer ")) {
        token = header.split(" ")[1];
      }
    }
    return Optional.ofNullable(token);
  }
}
