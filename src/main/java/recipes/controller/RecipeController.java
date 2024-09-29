package recipes.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;

import recipes.model.RecipeDTO;
import recipes.service.RecipeService;
import recipes.exception.NoSuchRecipeException;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
  @Autowired
  RecipeService recipeService;

  @GetMapping("/{id}")
  public ResponseEntity<RecipeDTO> getRecipe(@PathVariable("id") Long id) {
    try {
      RecipeDTO result = recipeService.getRecipe(id);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (NoSuchRecipeException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/new")
  public ResponseEntity<Map<String, Long>> saveRecipe(@Valid @RequestBody RecipeDTO recipeDTO) {
    try {
      RecipeDTO result = recipeService.saveRecipe(recipeDTO);
      return new ResponseEntity<>(Map.of("id", result.getId()), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<RecipeDTO> updateRecipe(@PathVariable("id") Long id, @Valid @RequestBody RecipeDTO recipeDTO) {
    try {
      RecipeDTO updatedRecipe = recipeService.updateRecipe(id, recipeDTO);
      return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
    } catch (NoSuchRecipeException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRecipe(@PathVariable("id") Long id) {
    try {
      recipeService.deleteRecipe(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (NoSuchRecipeException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
