package org.example.coffee.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "order_table") // 테이블 이름 매핑
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

   // @Column(name = "address", nullable = false)
    //private String address;

    //@Column(name = "postcode", nullable = false)
    //private String postcode;



}
