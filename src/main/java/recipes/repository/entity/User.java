package recipes.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String email;
  @Column
  private String password;
  @Column
  private LocalDateTime date;

  public User(String email, String password) {
    this.email = email;
    this.password = password;
    this.date = LocalDateTime.now();
  }
}
