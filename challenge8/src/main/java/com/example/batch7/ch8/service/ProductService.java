package com.example.batch7.ch8.service;

import com.example.batch7.ch8.entity.Product;

import java.util.Map;

public interface ProductService {
    Map list();

    Map edit(Product request);
    Map delete(Long id);
    Map save(Product request);

    Map getById(Long id);

    Map pagination(int page, int size);
}
