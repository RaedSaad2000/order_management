package com.example.order_management.Controller;

import com.example.order_management.DTOs.productOrderDTO;
import com.example.order_management.Services.productOrderServices;
import com.example.order_management.model.ProductOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/productorder")
@RestController
public class ProductOrderController {


    @Autowired
    productOrderServices productOrderServices;

    @GetMapping("")
    public ResponseEntity<List<ProductOrder>> getAllProductOrders() {
        return ResponseEntity.ok().body(productOrderServices.getAllProductOrders());
    }






}
