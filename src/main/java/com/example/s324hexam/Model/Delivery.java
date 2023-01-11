package com.example.s324hexam.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


//Entity means that this class is a table in the database
@Entity
//Getter and Setter are used to generate getters and setters for the fields
    @Getter
    @Setter
    @ToString
//NoArgsConstructor and AllArgsConstructor are used to generate constructors
    @NoArgsConstructor
    @AllArgsConstructor
//Builder is used for testing
    @Builder

    public class Delivery {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Setter(AccessLevel.NONE)
        private Long id;

        @Column(nullable = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate date;


        @Column(nullable = false, length = 255)
        private String warehouse;

        @Column(nullable = false, length = 255)
        private String destination;

        //fetch eager means fetch immediately
        //CascadeType.PERSIST means if a delivery is deleted, all the orders that contain that delivery will be deleted as well
        //cascade remove means that if a delivery is deleted, all the orders that contain that delivery will be deleted as well
        @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
        @JoinColumn(name = "delivery_id", referencedColumnName = "id")
        @JsonIgnore
        //HashSet doesn't allow duplicates values
        private Set<OrderFood> orders = new HashSet<>();

        @ManyToOne()
        @JoinColumn(name = "van_id", referencedColumnName = "id")
        private Van van;

    }

