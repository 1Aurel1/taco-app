package com.taco.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",nullable = false,length = 50)
    @Size(min = 3,message = "The first name should contain at least 3 letters")
    private String firstName;

    @Column(name = "last_name",nullable = false,length = 50)
    @Size(min = 3,message = "The last name should contain at least 3 letters")
    private String lastName;

    private int age;

    private String gender;

    @Email()
    @Column(nullable = false,unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    //lidhja Many - One me Role Entitet

    //nuk mendoj se na duhet lidhje e dy-anshme ne entitetet User-Role pasi nga roli
    //nuk do kerkojme info dhe kjo lidhje e nje-anshme performon
    //shum mire per CRUD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    // One - Many me Ingredient Entitet

    @OneToMany(mappedBy = "user")
    private List<Ingredient> ingredient;

    // Lidhja One - Many me Tako Entitet

    @OneToMany(mappedBy = "user")
    private List<Taco> tacos;

    //Lidhja One - One me Card Entitet

    @OneToOne(cascade = CascadeType.ALL)
    private Card card;

    // Lidhja Many - Many me Oders entity
//kam perdorur Set sepse performon me mire se List ne lidhjet Many - Many
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_orders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> orders = new HashSet<>();

}
