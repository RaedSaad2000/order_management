package com.example.order_management.Services;


import com.example.order_management.DTOs.OrderDTO;
import com.example.order_management.Exception.ResourceNotFoundException;
import com.example.order_management.Repository.OrderRepository;
import com.example.order_management.Repository.customerRepository;

import com.example.order_management.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class orderServices {
    @Autowired
    public OrderRepository orderRepository ;
    @Autowired
    public customerRepository customerRepository ;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public OrderDTO getOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("order", "id", id));
        return mapToDTO(order);

    }

    public ResponseEntity<OrderDTO> addOrder(OrderDTO orderDTO) {

        Order order = mapToEntity(orderDTO);
        Order newOrder = orderRepository.save(order);
        OrderDTO orderResponse = mapToDTO(order);
        return new ResponseEntity<OrderDTO>(orderResponse, HttpStatus.CREATED);


    }


    public OrderDTO updateOrder(OrderDTO orderDto, long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("order", "id", id);
        });

        order.setOrderAt(orderDto.getOrderAt());
        order.setCustomer(customerRepository.getById(orderDto.getCustomer_id()));
        Order updatedOrder = orderRepository.save(order);
        return mapToDTO(order);
    }


    public void deleteOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("order", "id", id));
        orderRepository.delete(order);
    }


    // convert Entity into DTO
    private OrderDTO mapToDTO(Order order){
        OrderDTO orderDto = new OrderDTO();
        orderDto.setOrder_id(order.getOrder_id());
        orderDto.setOrderAt(order.getOrderAt());
        orderDto.setCustomer_id(order.getCustomer().getCustomer_id());
        return orderDto;
    }

    // convert DTO to entity
    private Order mapToEntity(OrderDTO orderDto){
        Order order = new Order();
        order.setOrderAt(orderDto.getOrderAt());
        order.setCustomer(customerRepository.getById(orderDto.getCustomer_id()));
        return order;
    }
}
