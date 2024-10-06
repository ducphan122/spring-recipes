package recipes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.authentication.ProviderManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private final CustomUserDetailsService customUserDetailsService;

  public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
    this.customUserDetailsService = customUserDetailsService;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/register", "/actuator/shutdown").permitAll()
            .requestMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated())
        .csrf(csrf -> csrf
            .ignoringRequestMatchers("/h2-console/**")
            .disable())
        .headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions.sameOrigin()))
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public RecipeAuthenticationProvider recipeAuthenticationProvider(
      PasswordEncoder passwordEncoder) throws Exception {
    return new RecipeAuthenticationProvider(customUserDetailsService, passwordEncoder);
  }

  @Bean
  public AuthenticationManager authenticationManager(RecipeAuthenticationProvider recipeAuthenticationProvider)
      throws Exception {
    return new ProviderManager(recipeAuthenticationProvider);
  }
}