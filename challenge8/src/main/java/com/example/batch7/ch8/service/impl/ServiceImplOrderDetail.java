package com.example.batch7.ch8.service.impl;

import com.example.batch7.ch8.entity.OrderDetail;
import com.example.batch7.ch8.repository.OrderDetailRepository;
import com.example.batch7.ch8.service.OrderDetailService;
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
public class ServiceImplOrderDetail implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private Response response;

    @Override
    public Map list() {
        return null;
    }

    @Override
    public Map edit(OrderDetail request) {
        try {
            if(response.chekNull(request.getId())){
                return  response.error("Id is required.",402);
            }

            Optional<OrderDetail> getId = orderDetailRepository.findById(request.getId());
            if(!getId.isPresent()){
                return response.error("id not found", 404);
            }

            OrderDetail edit = getId.get();
            edit.setQuantity(request.getQuantity());
            edit.setTotal_price(request.getTotal_price());

            return response.sukses(orderDetailRepository.save(edit));
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

            Optional<OrderDetail> getId = orderDetailRepository.findById(id);
            if(!getId.isPresent()){
                return response.error("id not found", 404);
            }
            orderDetailRepository.deleteById(id);
            return response.sukses(null);
        } catch (Exception e) {
            return response.error(e.getMessage(), 500);
        }
    }

    @Override
    public Map save(OrderDetail request) {
        if(response.chekNull(request.getTotal_price())) {
            return response.error("Total price is required.", 402);
        }
        if(response.chekNull(request.getQuantity())) {
            return response.error("Quantity is required.", 402);
        }

        return response.sukses(orderDetailRepository.save(request));
    }

    @Override
    public Map getById(Long id) {
        Map map = new HashMap();
        Optional<OrderDetail> getId = orderDetailRepository.findById(id);
        if(!getId.isPresent()){
            return response.error("id not found", 404);
        }
        return response.sukses(getId.get());
    }

    @Override
    public Map pagination(int page, int size) {
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());
        Page<OrderDetail> list = orderDetailRepository.getAllDataPage(show_data);
        return response.sukses(list);
    }
}
