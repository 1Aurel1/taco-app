package com.taco.repository;

import com.taco.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("select i from Ingredient i where i.name = :text")
    List<Ingredient> findByIngredientsName(@Param("text") String text);
}
