package com.example.s324hexam.Repo;

import com.example.s324hexam.Model.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface OrderFoodRepo extends JpaRepository<OrderFood, Long> {
    // find the delivery by id
    @Query(value = "SELECT o FROM OrderFood o WHERE o.delivery.van.brand = ?1")
    List<OrderFood> findByDelivery(String keyword);
}

