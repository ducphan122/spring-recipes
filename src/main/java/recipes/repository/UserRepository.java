package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import recipes.repository.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Checks if a user with the given email exists.
   * 
   * @param email the email to check
   * @return true if a user with the email exists, false otherwise
   */
  boolean existsByEmail(@NonNull String email);

  /**
   * Finds a user by their email address.
   * 
   * @param email the email to search for
   * @return an Optional containing the user if found, or empty if not found
   */
  Optional<User> findByEmail(@NonNull String email);

  /**
   * Saves a user entity.
   * 
   * @param user the user to save
   * @return the saved user
   */
  @NonNull
  <S extends User> S save(@NonNull S user);

}
