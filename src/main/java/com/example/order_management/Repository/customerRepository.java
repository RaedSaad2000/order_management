package com.example.order_management.Repository;

import com.example.order_management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface customerRepository extends JpaRepository<Customer,Long> {


}