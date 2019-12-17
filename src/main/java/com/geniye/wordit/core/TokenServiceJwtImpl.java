package com.geniye.wordit.core;

import com.geniye.wordit.core.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.Optional;

@Service
public class TokenServiceJwtImpl implements TokenService {
  private String key = "milos";
  @Override
  public String generateToken(User user) {
    return Jwts.builder()
      .setSubject(user.getUsername())
      // TODO - enable expiration
      //.setExpiration(this.getExpirationDate())
      .signWith(SignatureAlgorithm.HS512, this.key)
      .compact();
  }

  /**
   * This method validates token. It will also throw ExpiredJwtException if token expired.
   * @param token
   * @return Optinal
   */
  @Override
  public Optional<String> extractUsername(String token) {
    Jws<Claims> claims = Jwts.parser().setSigningKey(this.key).parseClaimsJws(token);
    return Optional.ofNullable(claims.getBody().getSubject());
  }

  private Date getExpirationDate() {
    return new Date(System.currentTimeMillis() + 60 * 1000); // 1 minute
  }
}
