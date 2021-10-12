package com.taco.services;

import com.taco.repository.TacoRepository;
import com.taco.entities.Taco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TacoServices {

    private final TacoRepository tacoRepository;


    //create Taco
    public Taco createTaco(Taco taco){
        return tacoRepository.save(taco);
    }

    // get all available tacos
    public List<Taco> getAllTacos(){
        return tacoRepository.findAll();
    }


    //update Taco from Db
    public Taco updateTaco(Taco taco){
         return tacoRepository.save(taco);
    }

    //delete Taco by Id
    public void deleteTacoById(Long id){
        tacoRepository.deleteById(id);
    }
}
