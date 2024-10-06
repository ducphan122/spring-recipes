package recipes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import recipes.repository.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
  boolean existsByIdAndAuthorId(Long id, Long authorId);

  List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);

  List<Recipe> findByNameContainingIgnoreCaseOrderByDateDesc(String name);

  List<Recipe> findByCategoryIgnoreCaseAndNameContainingIgnoreCaseOrderByDateDesc(String category, String name);
}
