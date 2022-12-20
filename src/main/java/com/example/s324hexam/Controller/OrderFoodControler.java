package com.example.s324hexam.Controller;

import com.example.s324hexam.Model.OrderFood;
import com.example.s324hexam.Service.OrderFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderFoodControler {

    private final OrderFoodService orderFoodService;

    //get all
    @GetMapping
    public ResponseEntity<List<OrderFood>> getAll() {
        return new ResponseEntity<>(orderFoodService.getAllOrders(), HttpStatus.OK);
    }
    //get by id
    @GetMapping("{id}")
    public ResponseEntity<OrderFood> getById(@PathVariable Long id) {
       //find by id and calulate price
        return new ResponseEntity<>(orderFoodService.getOrderById(id), HttpStatus.OK);
    }
    //calculate price
    @GetMapping("{id}/price")
    public ResponseEntity<Integer> getPrice(@PathVariable Long id) {
        //find by id and calulate price
        return new ResponseEntity<>(orderFoodService.calculateTotalPrice(id)
                , HttpStatus.OK);
    }
    //creat
    @PostMapping
    public ResponseEntity<OrderFood> save(@RequestBody OrderFood orderFood) {
        //cheack if the order is more than 10000 grams not allowed
        if(orderFoodService.cheakWeight(orderFood.getDelivery().getId()))
            return new ResponseEntity<>(orderFoodService.createOrder(orderFood), HttpStatus.OK);
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    //edit
    @PutMapping("{id}")
    public ResponseEntity<OrderFood> update(@PathVariable Long id, @RequestBody OrderFood orderFood) {
        if(id==null || id <=0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderFoodService.updateOrder(id, orderFood), HttpStatus.OK);
    }
    //delete
    @DeleteMapping("{id}")
    public ResponseEntity<?> del(@PathVariable Long id) {
        if(id==null || id <=0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        orderFoodService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //find by keyword
    @GetMapping(params = "keyword")
    public ResponseEntity<List<OrderFood>> findByName(@RequestParam String keyword) {
        return new ResponseEntity<>(orderFoodService.searchOrderDate(keyword), HttpStatus.OK);
    }




}
