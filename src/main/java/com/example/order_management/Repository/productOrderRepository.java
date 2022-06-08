package com.example.order_management.Repository;

import com.example.order_management.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productOrderRepository extends JpaRepository<ProductOrder,Long> {
}
