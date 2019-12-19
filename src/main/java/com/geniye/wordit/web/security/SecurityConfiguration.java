package com.geniye.wordit.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Bean
  JwtTokenFilter jwtTokenFilter() {
    return new JwtTokenFilter();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // TODO - Disable for production
    http.authorizeRequests()
        .antMatchers("/h2-console", "/h2-console/**").permitAll()
        .and()
        .headers().frameOptions().sameOrigin();

    http.csrf().disable()
      .authorizeRequests()
      .antMatchers("/users/**").permitAll()
      .antMatchers("/articles/**").permitAll()
      .anyRequest().authenticated();
    http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}
