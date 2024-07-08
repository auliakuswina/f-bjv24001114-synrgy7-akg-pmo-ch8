package com.example.batch7.ch8.service.impl;

import com.example.batch7.ch8.entity.Order;
import com.example.batch7.ch8.repository.OrderRepository;
import com.example.batch7.ch8.service.OrderService;
import com.example.batch7.ch8.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceImplOrder implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Response response;

    @Override
    public Map list() {
        return null;
    }

    @Override
    public Map edit(Order request) {
        try {
            if(response.chekNull(request.getId())){
                return  response.error("Id is required.",402);
            }

            Optional<Order> getId = orderRepository.findById(request.getId());
            if(!getId.isPresent()){
                return response.error("id not found", 404);
            }

            Order edit = getId.get();
            edit.setDestination_address(request.getDestination_address());

            return response.sukses(orderRepository.save(edit));
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

            Optional<Order> getId = orderRepository.findById(id);
            if(!getId.isPresent()){
                return response.error("id not found", 404);
            }
            orderRepository.deleteById(id);
            return response.sukses(null);
        } catch (Exception e) {
            return response.error(e.getMessage(), 500);
        }
    }

    @Override
    public Map save(Order request) {
        if(response.chekNull(request.getDestination_address())) {
            return response.error("Destination address is required.", 402);
        }

        Date now = new Date();

        request.setOrder_time(now);

        return response.sukses(orderRepository.save(request));
    }

    @Override
    public Map getById(Long id) {
        Map map = new HashMap();
        Optional<Order> getId = orderRepository.findById(id);
        if(!getId.isPresent()){
            return response.error("id not found", 404);
        }
        return response.sukses(getId.get());
    }

    @Override
    public Map pagination(int page, int size) {
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Order> list = orderRepository.getAllDataPage(show_data);
        return response.sukses(list);
    }
}
