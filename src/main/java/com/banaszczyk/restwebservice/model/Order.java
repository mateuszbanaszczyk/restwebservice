package com.banaszczyk.restwebservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<QuantityProduct> quantityProducts;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "final_price")
    private BigDecimal price;

}

