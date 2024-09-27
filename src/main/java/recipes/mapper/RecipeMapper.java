package recipes.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import recipes.model.RecipeDTO;
import recipes.repository.entity.Recipe;

@Component
@AllArgsConstructor
public class RecipeMapper {
  public RecipeDTO toRecipeDTO(Recipe recipe) {
    return new RecipeDTO(recipe.getId(), recipe.getName(), recipe.getDescription(),
        new ArrayList<>(recipe.getIngredients()), new ArrayList<>(recipe.getDirections()));
  }

  public Recipe toRecipe(RecipeDTO recipeDTO) {
    return new Recipe(recipeDTO.getName(), recipeDTO.getDescription(),
        new ArrayList<>(recipeDTO.getIngredients()), new ArrayList<>(recipeDTO.getDirections()));
  }
}