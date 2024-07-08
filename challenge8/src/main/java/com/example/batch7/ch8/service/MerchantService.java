package com.example.batch7.ch8.service;

import com.example.batch7.ch8.entity.Merchant;

import java.util.Map;

public interface MerchantService {
    Map list();

    Map edit(Merchant request);
    Map delete(Long id);
    Map save(Merchant request);

    Map getById(Long id);

    Map pagination(int page, int size);
}
