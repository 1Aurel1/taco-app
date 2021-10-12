package com.taco.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tacos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalPrice;

    // lidhje Many - Many me Ingredients
    //kam perdorur Set sepse performon me mire se List ne lidhjet Many - Many
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "taco_ingredients",joinColumns = @JoinColumn(name = "taco_id"),
                            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients;

    // lidhje  Many - One me Card
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    //lidhje Many - One me User

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //lidhje Many - Many me Order
    @ManyToMany(// mendoj se duhet cascade.Type.ALL sepse nje order pa Taco nuk egziston
             )
    @JoinTable(name = "tacos_orders",joinColumns =
    @JoinColumn(name = "taco_id"),
    inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> orders;
}
