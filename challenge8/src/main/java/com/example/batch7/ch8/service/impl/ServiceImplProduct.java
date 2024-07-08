package com.example.batch7.ch8.service.impl;

import com.example.batch7.ch8.entity.Product;
import com.example.batch7.ch8.repository.ProductRepository;
import com.example.batch7.ch8.service.ProductService;
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
public class ServiceImplProduct implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Response response;

    @Override
    public Map list() {
        return null;
    }

    @Override
    public Map edit(Product request) {
        try {
            if(response.chekNull(request.getId())){
                return response.error("Id is required.",402);
            }

            Optional<Product> getId = productRepository.findById(request.getId());
            if(!getId.isPresent()){
                return response.error("id not found", 404);
            }

            Product edit = getId.get();
            edit.setProduct_name(request.getProduct_name());
            edit.setPrice(request.getPrice());

            return response.sukses(productRepository.save(edit));
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

            Optional<Product> getId = productRepository.findById(id);
            if(!getId.isPresent()){
                return response.error("id not found", 404);
            }
            productRepository.deleteById(id);
            return response.sukses(null);
        } catch (Exception e) {
            return response.error(e.getMessage(), 500);
        }
    }

    @Override
    public Map save(Product request) {
        if(response.chekNull(request.getProduct_name())) {
            return response.error("Product name is required.", 402);
        }
        if(response.chekNull(request.getPrice())) {
            return response.error("Price is required.", 402);
        }

        return response.sukses(productRepository.save(request));
    }

    @Override
    public Map getById(Long id) {
        Map map = new HashMap();
        Optional<Product> getId = productRepository.findById(id);
        if(!getId.isPresent()){
            return response.error("id not found", 404);
        }
        return response.sukses(getId.get());
    }

    @Override
    public Map pagination(int page, int size) {
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Product> list = productRepository.getAllDataPage(show_data);
        return response.sukses(list);
    }
}
