package com.naga.spring.dbservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDTO {

    private long productId;
    private String productName;
    private String productDescription;
    private double productPrice;

}
