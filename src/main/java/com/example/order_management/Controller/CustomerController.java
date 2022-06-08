package com.example.order_management.Controller;

import com.example.order_management.DTOs.CustomerDTO;
import com.example.order_management.model.Customer;
import com.example.order_management.Services.customerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/customers")
@RestController
public class CustomerController {

    @Autowired
    customerServices customerServices;

    @GetMapping("")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok().body(customerServices.getAllCustomers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(customerServices.getCustomerById(id));
    }


    @PostMapping("")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO CustomerDTO) {
        return customerServices.addCustomer(CustomerDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO CustomerDTO
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(customerServices.updateCustomer(CustomerDTO, id), HttpStatus.CREATED);
    }



}
