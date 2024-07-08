package com.example.batch7.ch8.service;

import com.example.batch7.ch8.entity.OrderDetail;

import java.util.Map;

public interface OrderDetailService {
    Map list();

    Map edit(OrderDetail request);
    Map delete(Long id);
    Map save(OrderDetail request);

    Map getById(Long id);

    Map pagination(int page, int size);
}
