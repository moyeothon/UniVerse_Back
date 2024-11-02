package com.moyeothon.universe.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moyeothon.universe.global.jwt.JwtService;
import com.moyeothon.universe.global.jwt.filter.JwtAuthenticationProcessingFilter;
import com.moyeothon.universe.global.login.filter.JsonUsernamePasswordAuthenticationFilter;
import com.moyeothon.universe.global.login.handler.LoginFailureHandler;
import com.moyeothon.universe.global.login.handler.LoginSuccessJWTProvideHandler;
import com.moyeothon.universe.repository.MemberRepository;
import com.moyeothon.universe.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final LoginService loginService;
  private final ObjectMapper objectMapper;
  private final MemberRepository memberRepository;
  private final JwtService jwtService;

  private static final String SIGNUP_URL = "/api/members";
  private static final String LOGIN_URL = "/api/members/login";

  private static final String[] AUTH_WHITELIST = {
      SIGNUP_URL,
      LOGIN_URL,
      "/api/movies"
  };

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .httpBasic(HttpBasicConfigurer::disable)
        .formLogin(FormLoginConfigurer::disable)
        .csrf(CsrfConfigurer::disable)
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .sessionManagement(
            configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/swagger-ui", "/swagger-ui/*", "/v3/api-docs", "/v3/api-docs/*").permitAll()
            .requestMatchers(AUTH_WHITELIST).permitAll()
            .anyRequest().authenticated()
        )
        .addFilterAfter(jsonUsernamePasswordLoginFilter(), LogoutFilter.class)
        .addFilterAfter(jwtAuthenticationProcessingFilter(),
            JsonUsernamePasswordAuthenticationFilter.class)
        .httpBasic(withDefaults());
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(loginService);

    return new ProviderManager(provider);
  }

  @Bean
  public LoginSuccessJWTProvideHandler loginSuccessJWTProvideHandler(){
    return new LoginSuccessJWTProvideHandler(jwtService, memberRepository);//변경
  }

  @Bean
  public LoginFailureHandler loginFailureHandler(){
    return new LoginFailureHandler();
  }

  @Bean
  public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordLoginFilter(){
    JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordLoginFilter = new JsonUsernamePasswordAuthenticationFilter(objectMapper);

    jsonUsernamePasswordLoginFilter.setAuthenticationManager(authenticationManager());

    jsonUsernamePasswordLoginFilter.setAuthenticationSuccessHandler(loginSuccessJWTProvideHandler());

    jsonUsernamePasswordLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());//변경
    return jsonUsernamePasswordLoginFilter;
  }

  @Bean
  public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter(){
    JwtAuthenticationProcessingFilter jsonUsernamePasswordLoginFilter = new JwtAuthenticationProcessingFilter(jwtService, memberRepository);

    return jsonUsernamePasswordLoginFilter;
  }
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin("http://localhost:3000"); // 허용할 출처
    configuration.addAllowedOrigin("https://localhost:3000"); // 허용할 출처
    configuration.addAllowedOrigin("http://moyeothon.limikju.com:80"); // 허용할 출처
    configuration.addAllowedOrigin("https://moyeothon.limikju.com:80"); // 허용할 출처
    configuration.addAllowedOrigin("http://moyeothon.limikju.com:3000"); // 허용할 출처
    configuration.addAllowedOrigin("https://moyeothon.limikju.com:3000"); // 허용할 출처
    configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
    configuration.addAllowedHeader("*"); // 모든 헤더 허용
    configuration.setAllowCredentials(true); // 자격 증명 허용

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}