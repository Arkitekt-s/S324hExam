package com.example.s324hexam.Service;




import com.example.s324hexam.Model.Delivery;
import com.example.s324hexam.Model.OrderFood;
import com.example.s324hexam.Model.Product;
import com.example.s324hexam.Model.Van;
import com.example.s324hexam.Repo.OrderFoodRepo;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderFoodTest {
    //mock is for isolate repo class and its not connected to database
    @Mock
    private OrderFoodRepo orderFoodRepo;
    private OrderFoodService orderFoodService;

    //BEFORE EACH test case this method is run
    @BeforeEach
    public void setUp() {
        orderFoodService = new OrderFoodService(orderFoodRepo);

    }


    @Test
    public void getAllOrders() {


        Product product = new Product();
        product.setPrice(1);
        product.setWeight(20);
        product.setName("Name");


        Delivery delivery = new Delivery();
        delivery.setDate(LocalDate.of(2024, 01, 01));
        delivery.setVan(Van.builder().build());
        delivery.setDestination("Destination");


        Van van = new Van();
        van.setCapacity(1);
        van.setModel("Model");
        van.setBrand("Brand");
        van.setCapacity(12);
        Van van2 = new Van();
        van2.setCapacity(12000);
        van2.setBrand("benz");
        van2.setModel("b2");


        // given
        var given = List.of(
                new OrderFood(1L, 1, product, delivery),
                new OrderFood(2L, 2, product, delivery),
                new OrderFood(3L, 3, product, delivery)


        );
        //when is defines the method call that is mocked from the class
        when(orderFoodRepo.findAll()).thenReturn(given);
        var actual = orderFoodService.getAllOrders();

        // then
        assertThat(actual.size()).isEqualTo(3);
    }

    @Test
    public void getById() {
        //given
        var given = new OrderFood(1L, 1, new Product(), new Delivery());
        //when
        when(orderFoodRepo.findById(1L)).thenReturn(java.util.Optional.of(given));
        //then
        var actual = orderFoodService.getOrderById(1L);
        assertThat(actual.getId()).isEqualTo(1l);
    }


    //test for delete
    @Test
    public void delete() {
        doNothing().when(orderFoodRepo).deleteById((Long) any());
        orderFoodService.deleteOrder(123L);
        verify(orderFoodRepo).deleteById((Long) any());

    }

    //test create
    @Test
    public void createOrder() {
        //given
        var given = new OrderFood(1L, 1, new Product(), new Delivery());
        //when
        when(orderFoodRepo.save(given)).thenReturn(given);
        //then
        var actual = orderFoodService.createOrder(given);
        assertThat(actual.getId()).isEqualTo(1l);
    }

    //test update
    @Test
    public void updateOrder() {
        //given
        var given = new OrderFood(1L, 1, new Product(), new Delivery());
        //when
        when(orderFoodRepo.findById(1l)).thenReturn(java.util.Optional.of(given));
        when(orderFoodRepo.save(given)).thenReturn(given);

        //then
        var actual = orderFoodService.updateOrder(1l, given);
        assertThat(actual.getId()).isEqualTo(1l);
        assertThat(actual.getQuantity()).isEqualTo(1);

    }

    //test SearchOrder
    @Test
    public void searchOrder() {
        //given
        var given = List.of(
                new OrderFood(1L, 1, new Product(), new Delivery()),
                new OrderFood(2L, 2, new Product(), new Delivery()),
                new OrderFood(3L, 3, new Product(), new Delivery())
        );

        //when
        when(orderFoodRepo.findByDelivery("Brand")).thenReturn(given);
        //then
        var actual = orderFoodService.searchOrderDate("Brand");
        assertThat(actual.size()).isEqualTo(3);

    }
}









