package recipes.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import recipes.model.RecipeDTO;
import recipes.model.AuthorDTO;
import recipes.repository.entity.Recipe;
import recipes.repository.entity.User;

@Component
@AllArgsConstructor
public class RecipeMapper {

  public RecipeDTO toRecipeDTO(Recipe recipe) {
    AuthorDTO authorDTO = new AuthorDTO(recipe.getAuthor().getId(), recipe.getAuthor().getEmail());
    return new RecipeDTO(recipe.getId(), recipe.getName(), recipe.getDescription(),
        new ArrayList<>(recipe.getIngredients()), new ArrayList<>(recipe.getDirections()), recipe.getCategory(),
        recipe.getDate(), authorDTO);
  }

  public Recipe toRecipe(RecipeDTO recipeDTO, User author) {
    return new Recipe(recipeDTO.getName(), recipeDTO.getDescription(),
        new ArrayList<>(recipeDTO.getIngredients()), new ArrayList<>(recipeDTO.getDirections()),
        recipeDTO.getCategory(), recipeDTO.getDate(), author);
  }
}