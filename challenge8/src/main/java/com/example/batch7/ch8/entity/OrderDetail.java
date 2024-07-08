package com.example.batch7.ch8.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@Data
@Entity
@Table(name = "order_detail")
@Where(clause = "deleted_date is null")
public class OrderDetail extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public int quantity;

    public int total_price;

    @JsonIgnore
    @ManyToOne(targetEntity = Order.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order_id;

    @JsonIgnore
    @ManyToOne(targetEntity = Product.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product_id;
}
