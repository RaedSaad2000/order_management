package com.example.order_management.Services;

import com.example.order_management.DTOs.ProductDTO;
import com.example.order_management.Exception.ResourceNotFoundException;
import com.example.order_management.Repository.ProductRepository;
import com.example.order_management.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {
    @Autowired
    public ProductRepository productRepository ;


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public ProductDTO getProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        return mapToDTO(product);

    }

    public ResponseEntity<ProductDTO> addProduct(ProductDTO productDTO) {

        Product product = mapToEntity(productDTO);
        Product newProduct = productRepository.save(product);
        ProductDTO productResponse = mapToDTO(newProduct);
        return new ResponseEntity<ProductDTO>(productResponse, HttpStatus.CREATED);


    }



    public void deleteProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        productRepository.delete(product);
    }


    // convert Entity into DTO
    private ProductDTO mapToDTO(Product product){
        ProductDTO productDto = new ProductDTO();
        productDto.setProduct_id(product.getProduct_id());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setSlug(product.getSlug());
        productDto.setVat(product.getVat());
        productDto.setReference(product.getReference());
        return productDto;
    }

    // convert DTO to entity
    private Product mapToEntity(ProductDTO productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setSlug(productDto.getSlug());
        product.setVat(productDto.getVat());
        product.setReference(productDto.getReference());
        return product;
    }
}
