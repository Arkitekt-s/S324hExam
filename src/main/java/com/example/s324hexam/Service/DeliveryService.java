package com.example.s324hexam.Service;

import com.example.s324hexam.Model.Delivery;
import com.example.s324hexam.Repo.DeliverRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryService {
    private final DeliverRepo deliverRepo;
    //getALL
    public List<Delivery> getAllDeliveries() {
        return deliverRepo.findAll();
    }
    //getByID
    public Delivery getDeliveryById(Long id) {
        return deliverRepo.findById(id).orElseThrow(() -> new IllegalStateException("Delivery with id " + id + " does not exist"));
    }
    //creat
    public Delivery createDelivery(Delivery delivery) {
        return deliverRepo.save(delivery);
    }
    //update
    public Delivery updateDelivery(Long id, Delivery delivery) {
        deliverRepo.findById(id).orElseThrow(() -> new IllegalStateException("Delivery with id " + id + " does not exist"));
        return deliverRepo.save(delivery);
    }
    //delete
    public void deleteDelivery(Long id) {
        deliverRepo.deleteById(id);
    }
    //check time is not in past
    public boolean checkTime(Delivery reservation) {
        if (reservation.getDate().isBefore(LocalDate.now())) {
            throw new IllegalStateException("Date is in the past");
        }
        return true;
    }





}
