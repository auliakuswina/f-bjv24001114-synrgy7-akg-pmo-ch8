package com.example.batch7.ch8.service.impl;

import com.example.batch7.ch8.entity.Merchant;
import com.example.batch7.ch8.repository.MerchantRepository;
import com.example.batch7.ch8.service.MerchantService;
import com.example.batch7.ch8.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceImplMerchant implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private Response response;

    @Override
    public Map list() {
        return null;
    }

    @Override
    public Map edit(Merchant request) {
        try {
            if(response.chekNull(request.getId())){
                return  response.error("Id is required.",402);
            }

            Optional<Merchant> getId = merchantRepository.findById(request.getId());
            if(!getId.isPresent()){
                return response.error("id not found", 404);
            }

            Merchant edit = getId.get();
            edit.setMerchant_location(request.getMerchant_location());
            edit.setMerchant_name(request.getMerchant_name());

            return response.sukses(merchantRepository.save(edit));
        }catch (Exception e){
            return response.error(e.getMessage(), 500);
        }
    }

    @Override
    public Map delete(Long id) {
        try {
            if(response.chekNull(id)){
                return  response.error("Id is required.",402);
            }

            Optional<Merchant> getId = merchantRepository.findById(id);
            if(!getId.isPresent()){
                return response.error("id not found", 404);
            }
            merchantRepository.deleteById(id);
            return response.sukses(null);
        } catch (Exception e) {
            return response.error(e.getMessage(), 500);
        }
    }

    @Override
    public Map save(Merchant request) {
        if(response.chekNull(request.getMerchant_name())) {
            return response.error("Merchant name is required.", 402);
        }
        if(response.chekNull(request.getMerchant_location())) {
            return response.error("Merchant location is required.", 402);
        }

        return response.sukses(merchantRepository.save(request));
    }

    @Override
    public Map getById(Long id) {
        Map map = new HashMap();
        Optional<Merchant> getId = merchantRepository.findById(id);
        if(!getId.isPresent()){
            return response.error("id not found", 404);
        }
        return response.sukses(getId.get());
    }

    @Override
    public Map pagination(int page, int size) {
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Merchant> list = merchantRepository.getAllDataPage(show_data);
        return response.sukses(list);
    }
}
