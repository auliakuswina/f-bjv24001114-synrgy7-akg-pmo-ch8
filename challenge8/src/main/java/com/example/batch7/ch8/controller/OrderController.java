package com.example.batch7.ch8.controller;

import com.example.batch7.ch8.entity.Order;
import com.example.batch7.ch8.service.InvoiceService;
import com.example.batch7.ch8.service.OrderService;
import com.example.batch7.ch8.utils.Response;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private HttpServletResponse httpResponse;

    @Autowired
    public Response response;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping(value = {"/list"})
    public ResponseEntity<Map> getListOrder() {
        return new ResponseEntity<Map>(response.sukses(orderService.pagination(0, 1)), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public Map saveOrder(@RequestBody Order request) {
        Map map = new HashMap();
        try {
            logger.info("Request:", request);
            map = orderService.save(request);
            return map;
        } catch (Exception e) {
            logger.info("Error save : ", e.getMessage());
            return map;
        }
    }

    @PutMapping(value = "/edit/{id}")
    public Map editOrder(@PathVariable Long id, @RequestBody Order request) {
        Map map = new HashMap();
        try {
            request.setId(id);
            logger.info("Request:", request);
            map = orderService.edit(request);
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
            map = orderService.getById(id);
            return new ResponseEntity<Map>(response.sukses(map), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Error get : ", e.getMessage());
            return new ResponseEntity<Map>(response.error(null, 500), HttpStatus.REQUEST_TIMEOUT);
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Map> deleteOrder(@PathVariable Long id) {
        try {
            logger.info("Request : ", id);
            orderService.delete(id);
            return new ResponseEntity<Map>(response.sukses(null), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Error save : ", e.getMessage());
            return new ResponseEntity<Map>(response.error(null, 500), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/generate-invoice")
    public void generateInvoice(HttpServletResponse httpResponse) throws IOException {
        try {
            File invoiceFile = invoiceService.generateInvoice();
            if (invoiceFile == null || !invoiceFile.exists()) {
                httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                httpResponse.getWriter().write("Invoice generation failed");
                return;
            }

            httpResponse.setContentType("application/pdf");
            httpResponse.setHeader("Content-Disposition", "attachment; filename=invoice.pdf");

            FileInputStream fileInputStream = new FileInputStream(invoiceFile);
            OutputStream outputStream = httpResponse.getOutputStream();
            IOUtils.copy(fileInputStream, outputStream);

            outputStream.flush();
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            logger.error("Error generate invoice : ", e);
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().write("An error occurred while generating invoice");
        }
    }

    @PostMapping(value = "/generate-merchant")
    public void generateMerchant(HttpServletResponse httpResponse) throws IOException {
        try {
            File invoiceFile = invoiceService.generateReportingMerchat();
            if (invoiceFile == null || !invoiceFile.exists()) {
                httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                httpResponse.getWriter().write("Merchant generation failed");
                return;
            }

            httpResponse.setContentType("application/pdf");
            httpResponse.setHeader("Content-Disposition", "attachment; filename=merchant.pdf");

            FileInputStream fileInputStream = new FileInputStream(invoiceFile);
            OutputStream outputStream = httpResponse.getOutputStream();
            IOUtils.copy(fileInputStream, outputStream);

            outputStream.flush();
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            logger.error("Error generate invoice : ", e);
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.getWriter().write("An error occurred while generating invoice");
        }
    }

}
