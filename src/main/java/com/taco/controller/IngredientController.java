package com.taco.controller;

import com.taco.entities.Ingredient;
import com.taco.entities.Type;
import com.taco.services.IngredientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientServices ingredientServices;

    //get all available ingredients
    @GetMapping
    public List<Ingredient> getAllIngredients(){
        return ingredientServices.getAllAvailableIngredients();
    }

    //get ingredients by name
    @GetMapping("/text")
    public List<Ingredient> getAllIngredients(@PathVariable String text){
        return ingredientServices.readIngredientsByName(text);
    }

    //put new ingredient
    @PostMapping
    public ResponseEntity<Ingredient> addNewIngredient(@RequestBody Ingredient ingredients){
        //uri eshte nje String ose adres e nje objecti ne web
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("ingredients/").toUriString());
        ingredients.setId(0L);
        return ResponseEntity.created(uri).body(ingredientServices.createIngredient(ingredients));
    }

    //update ingredient
    @PutMapping
    public Ingredient updateIngredient(@RequestBody Ingredient ingredients){
        return ingredientServices.updateIngredients(ingredients);
    }

    //delete ingredient
    @DeleteMapping("/id")
    public String deleteIngredientById(@PathVariable int id){
        ingredientServices.deleteIngredientById(id);
        return "The deleted ingredient id is - " + id;
    }

// ------------------------------------------------------

    //Test Creating Ingredients
   /* public Set<Ingredients> ingredientsSetList() {
        Set<Ingredients> ingredients = new HashSet<>();
        ingredients.add(creatingIngredients1());
        ingredients.add(creatingIngredients2());
        ingredients.add(creatingIngredients3());
        return ingredients;
    }*/

    //ingresient 1
   /* public Ingredients creatingIngredients1(){
        //firts ingredient
        Ingredients ingredient1 = new Ingredients();
        ingredient1.setName("Cheese");
        ingredient1.setType(Type.CHEESE);
        ingredient1.setPrice(2.50);
        return ingredient1;
    }*/

    //ingredient 2
    public Ingredient creatingIngredients2(){
        //second ingredient
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("Sauce");
        ingredient2.setType(Type.SAUCE);
        ingredient2.setPrice(4);
        return ingredient2;
    }

    //Third ingredient
    public Ingredient creatingIngredients3(){
        //second ingredient
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setName("Veggietables");
        ingredient3.setType(Type.VEGGIES);
        ingredient3.setPrice(7.88);
        return ingredient3;
    }
// ----------------------------------------------------------


}
