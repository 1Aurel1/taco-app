package com.taco.controller;

import com.taco.entities.Taco;
import com.taco.services.TacoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tacos")
public class TacoController {

    private final TacoServices tacoServices;

    //get all available tacos
    @GetMapping
    @ResponseStatus()
    public ResponseEntity<List<Taco>> getAllTacos(){
        return ResponseEntity.ok().body(tacoServices.getAllTacos());
    }

    //get tacos by name
   /* @GetMapping("/text")
    public ResponseEntity<List<Taco>> getTacoByName(@PathVariable String text){
        return ResponseEntity.ok().body(tacoServices.getTacoByByIngredient(text));
    }*/

    //put new tacos
    @PostMapping
    public ResponseEntity<Taco> addNewTaco(@RequestBody Taco taco){
        //uri eshte nje String ose adres e nje objecti ne web
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("taco/").toUriString());
        taco.setId(0L);
        return ResponseEntity.created(uri).body(tacoServices.createTaco(taco));
    }

    //update tacos
    @PutMapping
    public Taco updateTaco(@RequestBody Taco taco){
        return tacoServices.updateTaco(taco);
    }

    //delete taco
    @DeleteMapping("/id")
    public String deleteTacotById(@PathVariable Long id){
        tacoServices.deleteTacoById(id);
        return "The deleted ingredient id is - " + id;
    }

}
