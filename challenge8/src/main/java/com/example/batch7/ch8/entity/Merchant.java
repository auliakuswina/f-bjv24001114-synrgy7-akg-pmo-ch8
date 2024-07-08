package com.example.batch7.ch8.entity;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@Data
@Entity
@Table(name = "merchant")
@Where(clause = "deleted_date is null")
public class Merchant extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String merchant_name;

    public String merchant_location;

    public boolean open;
}
