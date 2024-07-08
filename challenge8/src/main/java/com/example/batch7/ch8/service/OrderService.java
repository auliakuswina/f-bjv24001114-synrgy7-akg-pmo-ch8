package com.example.batch7.ch8.service;

import com.example.batch7.ch8.entity.Order;

import java.util.Map;

public interface OrderService {
    Map list();

    Map edit(Order request);
    Map delete(Long id);
    Map save(Order request);

    Map getById(Long id);

    Map pagination(int page, int size);
}
