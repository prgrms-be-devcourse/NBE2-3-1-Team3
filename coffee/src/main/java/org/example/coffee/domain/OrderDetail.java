package org.example.coffee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_detail")
@NoArgsConstructor
@Getter
@Setter
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "coffee_id", nullable = false)
    private Coffee coffee;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
