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
@Table(name="coffee")
@NoArgsConstructor
public class Coffee {
    @Id
    private int coffeeId;
    @Column(nullable = false)
    private String coffeeName;
    @Column (nullable = false)
    private int coffeePrice;
}
