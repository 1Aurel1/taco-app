package com.taco.services;

import com.taco.repository.IngredientRepository;
import com.taco.entities.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServices {

    private final IngredientRepository ingredientRepository;

    //create ingredient
    public Ingredient createIngredient(Ingredient ingredients){
        return ingredientRepository.save(ingredients);
    }

    //update ingredient
    public Ingredient updateIngredients(Ingredient ingredients){
        return ingredientRepository.save(ingredients);
    }

    //read all availavle ingredients
    public List<Ingredient> getAllAvailableIngredients(){
        return ingredientRepository.findAll();
    }

    //read ingredients by name
    public List<Ingredient> readIngredientsByName(String text){
        return ingredientRepository.findByIngredientsName(text);
    }

    //delete ingredient
    public void deleteIngredientById(long id){
        ingredientRepository.deleteById(id);

    }
}
