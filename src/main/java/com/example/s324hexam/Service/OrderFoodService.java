package com.example.s324hexam.Service;

import com.example.s324hexam.Model.OrderFood;
import com.example.s324hexam.Repo.OrderFoodRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderFoodService {
    private final OrderFoodRepo orderFoodRepo;

    //getALL
    public List<OrderFood> getAllOrders() {
        return orderFoodRepo.findAll();
    }

    //getByID
    public OrderFood getOrderById(Long id) {
        return orderFoodRepo.findById(id).orElseThrow(() -> new IllegalStateException("Order with id " + id + " does not exist"));
    }

    //creat
    public OrderFood createOrder(OrderFood orderFood) {
        return orderFoodRepo.save(orderFood);
    }

    //update
    public OrderFood updateOrder(Long id, OrderFood orderFood) {
        orderFoodRepo.findById(id).orElseThrow(() -> new IllegalStateException("Order with id " + id + " does not exist"));
        return orderFoodRepo.save(orderFood);
    }
    //search find by date
    public List<OrderFood> searchOrderDate(String keyword) {
        return orderFoodRepo.findByDelivery(keyword);
    }
    //delete
    public void deleteOrder(Long id) {
        orderFoodRepo.deleteById(id);
    }

    //calculate each product price
    public int calculatePrice(OrderFood orderFood) {
       int productPrice = (orderFood.getQuantity()) * (orderFood.getProduct().getPrice());
         return productPrice;

    }
    //calculate total of weight
    public int calculateWeight(OrderFood orderFood) {
        int productWeight = (orderFood.getQuantity()) * (orderFood.getProduct().getWeight());
        return productWeight;
    }

    ////find the all same delivery id and sum the total price
    public int calculateTotalPrice(Long id) {
        List<OrderFood> orderFoodList = orderFoodRepo.findAll();
        int totalPrice = 0;
        for (OrderFood orderFood : orderFoodList) {
            if (orderFood.getDelivery().getId().equals(id)) {
                totalPrice += calculatePrice(orderFood);
            }
        }
        return totalPrice;
    }

    //calculate total weight same id and sum the total weight
    public int calculateTotalWeight(Long id) {
        List<OrderFood> orderFoodList = orderFoodRepo.findAll();
        int totalWeight = 0;
        for (OrderFood orderFood : orderFoodList) {
            if (orderFood.getDelivery().getId().equals(id)) {
                totalWeight += calculateWeight(orderFood);

            }

        }
        return totalWeight;
    }
    //cheak if the order is more than 10000 grams not allowed
    public boolean cheakWeight(Long id) {
        if (calculateTotalWeight(id) < 100000) {
            return true;
        } else {
            throw new IllegalStateException("The order is more than 10000 grams not allowed");
        }
    }






}