package com.example.order_management.DTOs;

import com.example.order_management.model.Product;
import lombok.Data;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    private Long stock_id;
    private Long product_id;
    private int quantity;
    private Date updateAt;


}
