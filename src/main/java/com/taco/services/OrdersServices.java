package com.taco.services;

import com.taco.repository.OrderRepository;
import com.taco.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServices {

    private final OrderRepository orderRepository;

    //Create a new Order
    public Order createNewOrder(Order orders){
        orders.setId(0);
        return orderRepository.save(orders);
    }
    // Read All Orders from Db
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    // Update Order
    public Order updateOrder(Order orders){
        return orderRepository.save(orders);
    }

}
