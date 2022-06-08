package com.example.order_management.Services;

import com.example.order_management.DTOs.CustomerDTO;
import com.example.order_management.Exception.ResourceNotFoundException;
import com.example.order_management.Repository.customerRepository;
import com.example.order_management.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class customerServices {
    @Autowired
    public customerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerDTO getCustomerById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("customer", "id", id));
        return mapToDTO(customer);

    }


    public ResponseEntity<CustomerDTO> addCustomer(CustomerDTO customerDTO) {

        Customer customer = mapToEntity(customerDTO);
        Customer newCustomer = customerRepository.save(customer);
        CustomerDTO customerResponse = mapToDTO(newCustomer);
        return new ResponseEntity<CustomerDTO>(customerResponse, HttpStatus.CREATED);


    }


    public CustomerDTO updateCustomer(CustomerDTO customerDTO, long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("customer", "id", id);
        });

        customer.setFirst_name(customerDTO.getFirst_name());
        customer.setLast_name(customerDTO.getLast_name());
        customer.setBornAt(customerDTO.getBornAt());
        Customer updatedCustomer = customerRepository.save(customer);
        return mapToDTO(updatedCustomer);
    }


    // convert Entity into DTO
    private CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomer_id(customer.getCustomer_id());
        customerDTO.setFirst_name(customer.getFirst_name());
        customerDTO.setLast_name(customer.getLast_name());
        customerDTO.setBornAt(customer.getBornAt());

        return customerDTO;
    }

    // convert DTO to entity
    private Customer mapToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomer_id(customerDTO.getCustomer_id());
        customer.setFirst_name(customerDTO.getFirst_name());
        customer.setLast_name(customerDTO.getLast_name());
        customer.setBornAt(customerDTO.getBornAt());
        return customer;
    }


}
