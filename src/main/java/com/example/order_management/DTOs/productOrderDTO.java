package com.example.order_management.DTOs;

import com.example.order_management.model.Order;
import com.example.order_management.model.Product;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class productOrderDTO {

    private Long order_id;
    private Long product_id;
    private Integer quantity;
    private Double price;
    private Integer vat;
}
