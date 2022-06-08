package com.example.order_management.DTOs;
import lombok.*;

import javax.persistence.Column;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long product_id ;
    private String name ;
    private double price ;
    private String slug ;
    private String reference ;
    private Integer vat ;

}
