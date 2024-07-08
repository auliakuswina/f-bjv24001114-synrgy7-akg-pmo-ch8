package com.example.batch7.ch8.controller;

import com.example.batch7.ch8.entity.Product;
import com.example.batch7.ch8.service.ProductService;
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
@RequestMapping("/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    public Response response;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping(value = {"/list"})
    public ResponseEntity<Map> getListProduct() {
        return new ResponseEntity<Map>(response.sukses(productService.pagination(0, 1)), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public Map saveProduct(@RequestBody Product request) {
        Map map = new HashMap();
        try {
            logger.info("Request:", request);
            map = productService.save(request);
            return map;
        } catch (Exception e) {
            logger.info("Error save : ", e.getMessage());
            return map;
        }
    }

    @PutMapping(value = "/edit/{id}")
    public Map editProduct(@PathVariable Long id, @RequestBody Product request) {
        Map map = new HashMap();
        try {
            request.setId(id);
            logger.info("Request:", request);
            map = productService.edit(request);
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
            map = productService.getById(id);
            return new ResponseEntity<Map>(response.sukses(map), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Error get : ", e.getMessage());
            return new ResponseEntity<Map>(response.error(null, 500), HttpStatus.REQUEST_TIMEOUT);
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Map> deleteProduct(@PathVariable Long id) {
        try {
            logger.info("Request : ", id);
            productService.delete(id);
            return new ResponseEntity<Map>(response.sukses(null), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Error save : ", e.getMessage());
            return new ResponseEntity<Map>(response.error(null, 500), HttpStatus.OK);
        }
    }
}
