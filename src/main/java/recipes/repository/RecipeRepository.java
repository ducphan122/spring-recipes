package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import recipes.repository.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
  // JpaRepository provides basic CRUD operations
}
