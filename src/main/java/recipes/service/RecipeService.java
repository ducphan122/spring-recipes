package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import recipes.exception.NoSuchRecipeException;
import recipes.mapper.RecipeMapper;
import recipes.model.RecipeDTO;
import recipes.repository.RecipeRepository;
import recipes.repository.entity.Recipe;

@Component
public class RecipeService {
  @Autowired
  private RecipeRepository recipeRepository;
  @Autowired
  private RecipeMapper recipeMapper;

  public RecipeDTO getRecipe(Long id) throws NoSuchRecipeException {
    Optional<Recipe> recipe = recipeRepository.findById(id);
    if (recipe.isPresent()) {
      return recipeMapper.toRecipeDTO(recipe.get());
    } else {
      throw new NoSuchRecipeException();
    }
  }

  public List<RecipeDTO> searchRecipes(String category, String name) {
    List<Recipe> recipes;
    if (category != null && name != null) {
      recipes = recipeRepository.findByCategoryIgnoreCaseAndNameContainingIgnoreCaseOrderByDateDesc(category, name);
    } else if (category != null) {
      recipes = recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    } else if (name != null) {
      recipes = recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    } else {
      recipes = new ArrayList<>();
    }
    return Optional.of(recipes)
        .filter(list -> !list.isEmpty())
        .map(list -> list.stream()
            .map(recipeMapper::toRecipeDTO)
            .toList())
        .orElse(new ArrayList<>());
  }

  public RecipeDTO saveRecipe(RecipeDTO recipeDTO) {
    recipeDTO.setDate(LocalDateTime.now());
    return recipeMapper.toRecipeDTO(recipeRepository.save(recipeMapper.toRecipe(recipeDTO)));
  }

  public RecipeDTO updateRecipe(Long id, RecipeDTO recipeDTO) throws NoSuchRecipeException {
    Recipe existingRecipe = recipeRepository.findById(id)
        .orElseThrow(NoSuchRecipeException::new);

    Recipe updatedRecipe = recipeMapper.toRecipe(recipeDTO);
    updatedRecipe.setId(existingRecipe.getId());
    updatedRecipe.setDate(LocalDateTime.now());
    Recipe savedRecipe = recipeRepository.save(updatedRecipe);

    return recipeMapper.toRecipeDTO(savedRecipe);
  }

  public void deleteRecipe(Long id) throws NoSuchRecipeException {
    Optional<Recipe> recipe = recipeRepository.findById(id);
    if (recipe.isPresent()) {
      recipeRepository.deleteById(id);
    } else {
      throw new NoSuchRecipeException();
    }
  }
}
