package com.taco.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    private double price;

    // lidhje Many - Many me Taco entity
    //kam perdorur Set sepse performon me mire se List ne lidhjet Many - Many
    @ManyToMany
    @JoinTable(name = "taco_ingredients",
            joinColumns = @JoinColumn(name ="ingredient_id"),
            inverseJoinColumns = @JoinColumn (name = "taco_id"))
    private Set<Taco> tacos ;

    //lidhje Many - One me User entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}

