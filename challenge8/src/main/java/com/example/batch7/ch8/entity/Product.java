package com.example.batch7.ch8.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@Data
@Entity
@Table(name = "product")
@Where(clause = "deleted_date is null")
public class Product extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String product_name;

    public Integer price;

    public boolean open;

    @JsonIgnore
    @ManyToOne(targetEntity = Merchant.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant_id;
}
