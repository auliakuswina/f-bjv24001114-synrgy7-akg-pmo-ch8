package com.example.batch7.ch8.entity;

import com.example.batch7.ch8.entity.oauth.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
@Where(clause = "deleted_date is null")
public class Order extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date order_time;

    @Column(name = "destination_address", columnDefinition = "TEXT")
    public String destination_address;

    public boolean completed;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;
}
