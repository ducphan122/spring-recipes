package recipes.repository.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "recipes")
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private String description;

  @Column
  private List<String> ingredients;

  @Column
  private List<String> directions;

  @Column
  private String category;

  @Column
  private LocalDateTime date;

  public Recipe(String name, String description, List<String> ingredients, List<String> directions, String category,
      LocalDateTime date) {
    this.name = name;
    this.description = description;
    this.ingredients = ingredients;
    this.directions = directions;
    this.category = category;
    this.date = date;
  }
}
