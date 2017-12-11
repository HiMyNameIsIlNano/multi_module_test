package com.daniele.myapp.web.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.daniele.myapp.web.security.AuthenticationTokenFilter;
import com.daniele.myapp.web.security.EntryPointUnauthorizedHandler;
import com.daniele.mybackend.userProfile.service.LoginService;
import com.daniele.mybackend.userProfile.service.SecurityService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Inject
  private EntryPointUnauthorizedHandler unauthorizedHandler;

  @Inject
  private LoginService loginService;

  @Inject
  private SecurityService securityService;

  @Inject
  public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder
      .userDetailsService(loginService)
        .passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
    AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
    authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
    return authenticationTokenFilter;
  }

  @Bean
  public SecurityService securityService() {
    return this.securityService;
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .csrf()
        .disable()
      .exceptionHandling()
        .authenticationEntryPoint(unauthorizedHandler)
        .and()
      .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
      .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .antMatchers("/auth/**").permitAll()
        .anyRequest().authenticated();

    // Custom JWT based authentication
    httpSecurity
      .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
  }

}