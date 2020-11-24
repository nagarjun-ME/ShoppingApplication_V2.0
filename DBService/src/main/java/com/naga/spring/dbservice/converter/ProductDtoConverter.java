package com.naga.spring.dbservice.converter;

import com.naga.spring.dbservice.dto.ProductDTO;
import com.naga.spring.dbservice.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDtoConverter {

    public ProductDTO convertToDto(Product product) {

        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setProductDescription(product.getProductDescription());
        dto.setProductName(product.getProductName());
        dto.setProductPrice(product.getProductPrice());

        return dto;

    }

    public List<ProductDTO> convertToDto(List<Product> productList) {
        return productList.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    public Product convertToEntity(ProductDTO product) {

        Product dto = new Product();
        dto.setProductId(product.getProductId());
        dto.setProductDescription(product.getProductDescription());
        dto.setProductName(product.getProductName());
        dto.setProductPrice(product.getProductPrice());

        return dto;

    }

    public List<Product> convertToEntity(List<ProductDTO> productDtoList) {
        return productDtoList.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

}
