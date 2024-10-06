package recipes.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

@Log4j2
public class RecipeAuthenticationProvider extends DaoAuthenticationProvider {

  public RecipeAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
    setUserDetailsService(userDetailsService);
    setPasswordEncoder(passwordEncoder);
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    log.info("Authentication attempt for user: {}", authentication.getName());
    try {
      Authentication result = super.authenticate(authentication);
      log.info("Authentication {} for user: {}", result.isAuthenticated() ? "successful" : "failed",
          authentication.getName());
      return result;
    } catch (AuthenticationException e) {
      log.warn("Authentication failed for user: {}", authentication.getName());
      throw e;
    }
  }

}