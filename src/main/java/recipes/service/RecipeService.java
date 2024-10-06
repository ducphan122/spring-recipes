package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import recipes.exception.NoSuchRecipeException;
import recipes.mapper.RecipeMapper;
import recipes.model.RecipeDTO;
import recipes.repository.RecipeRepository;
import recipes.repository.entity.Recipe;
import recipes.repository.entity.User;
import recipes.repository.UserRepository;
import recipes.exception.NoSuchUserException;

@Component
public class RecipeService {
  @Autowired
  private RecipeRepository recipeRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RecipeMapper recipeMapper;

  @Autowired
  private UserService userService;

  private boolean isAuthor(Long recipeId, Long userId) {
    return recipeRepository.existsByIdAndAuthorId(recipeId, userId);
  }

  public RecipeDTO getRecipe(Long id) throws NoSuchRecipeException {
    Optional<Recipe> recipe = recipeRepository.findById(id);
    if (recipe.isPresent()) {
      Recipe fetchedRecipe = recipe.get();
      return recipeMapper.toRecipeDTO(fetchedRecipe);
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
    String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        .getUsername();
    User author = userRepository.findByEmail(username)
        .orElseThrow(NoSuchUserException::new);
    recipeDTO.setDate(LocalDateTime.now());

    Recipe recipe = recipeMapper.toRecipe(recipeDTO, author);
    Recipe savedRecipe = recipeRepository.save(recipe);
    return recipeMapper.toRecipeDTO(savedRecipe);
  }

  public RecipeDTO updateRecipe(Long id, RecipeDTO recipeDTO) throws NoSuchRecipeException {
    String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        .getUsername();
    Long userId = userService.findByEmail(username).getId();

    if (!isAuthor(id, userId)) {
      throw new AccessDeniedException("You are not the author of this recipe");
    }

    Recipe existingRecipe = recipeRepository.findById(id)
        .orElseThrow(NoSuchRecipeException::new);

    Recipe updatedRecipe = recipeMapper.toRecipe(recipeDTO, existingRecipe.getAuthor());
    updatedRecipe.setId(existingRecipe.getId());
    updatedRecipe.setDate(LocalDateTime.now());

    Recipe savedRecipe = recipeRepository.save(updatedRecipe);
    return recipeMapper.toRecipeDTO(savedRecipe);
  }

  public void deleteRecipe(Long id) throws NoSuchRecipeException {
    String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        .getUsername();
    Long userId = userService.findByEmail(username).getId();

    if (!isAuthor(id, userId)) {
      throw new AccessDeniedException("You are not the author of this recipe");
    }
    Optional<Recipe> recipe = recipeRepository.findById(id);
    if (recipe.isPresent()) {
      recipeRepository.deleteById(id);
    } else {
      throw new NoSuchRecipeException();
    }
  }
}
