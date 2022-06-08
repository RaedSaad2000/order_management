package com.example.order_management.Services;

import com.example.order_management.DTOs.productOrderDTO;
import com.example.order_management.Exception.ResourceNotFoundException;
import com.example.order_management.Repository.OrderRepository;
import com.example.order_management.Repository.ProductRepository;
import com.example.order_management.Repository.productOrderRepository;
import com.example.order_management.model.ProductOrder;
import com.example.order_management.model.ProductOrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productOrderServices {

    @Autowired
    productOrderRepository productOrderRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    public List<ProductOrder> getAllProductOrders() {
        return productOrderRepository.findAll();
    }

    public productOrderDTO getProductOrderById(long id) {
        ProductOrder productOrder = productOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product_order", "id", id));
        return mapToDTO(productOrder);

    }



    private productOrderDTO mapToDTO(ProductOrder productOrder) {
        productOrderDTO productOrderDTO = new productOrderDTO();
        productOrderDTO.setOrder_id(productOrder.getOrder().getOrder_id());
        productOrderDTO.setProduct_id(productOrder.getProduct().getProduct_id());
        productOrderDTO.setPrice(productOrder.getPrice());
        productOrderDTO.setQuantity(productOrder.getQuantity());
        productOrderDTO.setVat(productOrder.getVat());

        return productOrderDTO;
    }

}
