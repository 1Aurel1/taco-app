package com.taco.controller;

import com.taco.entities.Order;
import com.taco.services.OrdersServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrdersServices ordersServices;

    //get all available orders
    @GetMapping
    public List<Order> getAllOrders(){
        return ordersServices.getAllOrders();
    }


    //put new order
    @PostMapping
    public ResponseEntity<Order> addNewOrder(@RequestBody Order orders){
        //uri eshte nje String ose adres e nje objecti ne web
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("order/").toUriString());
        orders.setId(0);
        return ResponseEntity.created(uri).body(ordersServices.createNewOrder(orders));
    }

    //update order
    @PutMapping
    public Order updateOrder(@RequestBody Order orders){
        return ordersServices.updateOrder(orders);
    }
}
