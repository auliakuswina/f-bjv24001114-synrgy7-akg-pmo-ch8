package com.example.batch7.ch8.controller;

import com.example.batch7.ch8.entity.OrderDetail;
import com.example.batch7.ch8.service.OrderDetailService;
import com.example.batch7.ch8.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/order-detail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    public Response response;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping(value = "/list")
    public ResponseEntity<Map> getListOrderDetail() {
        return new ResponseEntity<Map>(response.sukses(orderDetailService.pagination(0, 1)), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public Map saveOrderDetail(@RequestBody OrderDetail request) {
        Map map = new HashMap();
        try {
            logger.info("Request:", request);
            map = orderDetailService.save(request);
            return map;
        } catch (Exception e) {
            logger.info("Error save : ", e.getMessage());
            return map;
        }
    }

    @PutMapping(value = "/edit/{id}")
    public Map editOrderDetail(@PathVariable Long id, @RequestBody OrderDetail request) {
        Map map = new HashMap();
        try {
            request.setId(id);
            logger.info("Request:", request);
            map = orderDetailService.edit(request);
            return map;
        } catch (Exception e) {
            logger.info("Error save : ", e.getMessage());
            return map;
        }
    }

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<Map> getById(@PathVariable Long id) {
        Map map = new HashMap();
        try {
            map = orderDetailService.getById(id);
            return new ResponseEntity<Map>(response.sukses(map), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Error get : ", e.getMessage());
            return new ResponseEntity<Map>(response.error(null, 500), HttpStatus.REQUEST_TIMEOUT);
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Map> deleteOrderDetail(@PathVariable Long id) {
        try {
            logger.info("Request : ", id);
            orderDetailService.delete(id);
            return new ResponseEntity<Map>(response.sukses(null), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Error save : ", e.getMessage());
            return new ResponseEntity<Map>(response.error(null, 500), HttpStatus.OK);
        }
    }
}
