package com.taco.repository;

import com.taco.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    /*Orders createNewIngredient(Orders orders);
    List<Orders> readAllOrders();
    Orders updateOrder(Orders orders);*/
}
