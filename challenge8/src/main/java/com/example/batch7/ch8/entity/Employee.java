package com.example.batch7.ch8.entity;

import lombok.Data;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;

//@EntityScan
@Data
@Entity
@Table(name = "employee")
@Where(clause = "deleted_date is null")
public class Employee extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @Column(name = "address", columnDefinition = "TEXT")
    public String address;

    // 2016-01-01
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    public  String status = "active"; // Value Default


    //    @JsonIgnore
    @OneToOne (targetEntity = DetailKaryawan.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_detail_karyawan", referencedColumnName = "id")
    private DetailKaryawan detailKaryawan;

//    // best practice -> jika data yang ditampilkan , karena akan pengarruh performance
//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Rekening> rekening;
}

