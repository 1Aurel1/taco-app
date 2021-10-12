package com.taco.entities;

import com.taco.entities.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //Lidhja Many - Many me User entity
    //kam perdorur Set sepse performon me mire se List ne lidhjet Many - Many
    @ManyToMany
    @JoinTable(name = "users_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> user;

    //Lidhja Many - Many me Taco entity
    // mappedBy = po i tregojme se kush do jet owner-i i lidhjes
    @ManyToMany
    @JoinTable(name = "tacos_orders",joinColumns =
    @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "taco_id"))
    private Set<Taco> tacos;

}


