package com.taco.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    private Long id;

    //lidhje One - One me User

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    // lidhje One - Many me tacon

    @OneToMany(mappedBy = "card",
            cascade = {CascadeType.REFRESH,CascadeType.DETACH
    ,CascadeType.MERGE})
    private List<Taco> taco;



}
