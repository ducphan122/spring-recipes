package recipes.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
  @JsonIgnore
  private Long id;

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Description is required")
  private String description;

  @NotEmpty(message = "Ingredients list cannot be empty")
  private List<String> ingredients;

  @NotEmpty(message = "Directions list cannot be empty")
  private List<String> directions;

  @NotBlank(message = "Category is required")
  private String category;

  private LocalDateTime date;

  private AuthorDTO author;
}