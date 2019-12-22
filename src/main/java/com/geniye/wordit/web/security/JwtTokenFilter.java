package com.geniye.wordit.web.security;

import com.geniye.wordit.core.TokenService;
import com.geniye.wordit.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
  @Autowired
  UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
    /**
     * TODO - next line is hot fix. Spring uses jsessionid to maintain session, and even if
     * Authorization header is omitted, if users send valid header previously, it will be recognized.
     * This way is ensured that context is clear.
     * TODO - FIND A BETTER WAY!
     */
    SecurityContextHolder.clearContext();
    this.extractToken(httpServletRequest).ifPresent(token -> {
      this.tokenService.extractUsername(token).ifPresent(username -> {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
          userRepository.findByUsername(username).ifPresent(user -> {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user, // TODO - is it required ? What is used for?
                null,
                Collections.emptyList()
            );
            /**
             * TODO - Do I need next line?
             * authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             */
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(auth);
          });
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
