package org.example.coffee.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cart")
@NoArgsConstructor
public class Cart {
    @Id
    private int cartId;
    @Column(nullable = false)
    private String email;
    @Column (nullable = false)
    private int coffeeId;
    @Column (nullable = false)
    private int quantity;
}
