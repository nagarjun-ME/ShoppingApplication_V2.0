package com.naga.spring.dbservice.restcontroller;

import com.naga.spring.dbservice.converter.ProductDtoConverter;
import com.naga.spring.dbservice.dto.ProductDTO;
import com.naga.spring.dbservice.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/db/products/")
public class ProductRestController {

    private final  Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductDtoConverter converter;
    @Autowired
    private ProductService productService;

    @HystrixCommand
    @GetMapping("/all")
    public ResponseEntity <List <ProductDTO>> readAllProducts()
    {
        log.info("Inside read all products ");
        return ResponseEntity.ok().body(converter.convertToDto(productService.getAllProduct()));
    }

    @HystrixCommand
    @GetMapping("/{pId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("pId") long id)
    {
        log.info(" Finding product with id :{}", id);
        return ResponseEntity.ok().body(converter.convertToDto(productService.getProductById(id)));
    }

    @HystrixCommand
    @PostMapping("/add")
    public ResponseEntity < ProductDTO > createProduct(@RequestBody ProductDTO productDto) {
        log.info(" Adding the Product with id :{}", productDto.getProductId());
        return ResponseEntity.ok().body(converter.convertToDto(productService.createProduct(converter.convertToEntity(productDto))));
    }

    @HystrixCommand
    @PutMapping("/edit/{id}")
    public ResponseEntity < ProductDTO > updateProduct(@PathVariable long id, @RequestBody ProductDTO productDto) {
        productDto.setProductId(id);
        log.info(" Updating product with id :{}", id);
        return ResponseEntity.ok().body(converter.convertToDto(productService.updateProduct(converter.convertToEntity(productDto))));
    }

    @HystrixCommand
    @DeleteMapping("/rmv/{id}")
    public HttpStatus deleteProduct(@PathVariable long id) {
        log.info(" Removing product with id :{}", id);
        productService.deleteProduct(id);
        log.info(" Product id :{} has been removed", id);
        return HttpStatus.OK;
    }

}
