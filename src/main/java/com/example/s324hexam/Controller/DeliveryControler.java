package com.example.s324hexam.Controller;

import com.example.s324hexam.Model.Delivery;
import com.example.s324hexam.Service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryControler {

    private final DeliveryService deliveryService;
    @GetMapping
    public ResponseEntity<List<Delivery>> getAll() {
        return new ResponseEntity<>(deliveryService.getAllDeliveries(), HttpStatus.OK);
    }
    //get by id
    @GetMapping("{id}")
    public ResponseEntity<Delivery> getById(@PathVariable Long id) {
        if(id==null || id <=0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(deliveryService.getDeliveryById(id), HttpStatus.OK);
    }
    //creat
    @PostMapping
    public ResponseEntity<Delivery> save(@RequestBody Delivery delivery) {
        if(deliveryService.checkTime(delivery) ) {
            return new ResponseEntity<>(deliveryService.createDelivery(delivery), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    //edit
    @PutMapping("{id}")
    public ResponseEntity<Delivery> update(@PathVariable Long id, @RequestBody Delivery delivery) {
        if(id==null || id <=0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(deliveryService.updateDelivery(id, delivery), HttpStatus.OK);
    }
    //delete
    @DeleteMapping("{id}")
    public ResponseEntity<?> del(@PathVariable Long id) {
        if(id==null || id <=0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        deliveryService.deleteDelivery(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
