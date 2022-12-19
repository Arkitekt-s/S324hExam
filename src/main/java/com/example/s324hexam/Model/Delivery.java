package com.example.s324hexam.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


    @Entity
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
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


        @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
        @JoinColumn(name = "delivery_id", referencedColumnName = "id")
        @JsonIgnore
        private Set<OrderFood> orders = new HashSet<>();

        @ManyToOne()
        @JoinColumn(name = "van_id", referencedColumnName = "id")
        private Van van;

    }

