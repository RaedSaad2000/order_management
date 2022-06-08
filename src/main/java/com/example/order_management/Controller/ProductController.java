package com.example.order_management.Controller;

import com.example.order_management.DTOs.ProductDTO;
import com.example.order_management.Services.ProductServices;
import com.example.order_management.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductController {
    @Autowired
    ProductServices productServices;

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productServices.getAllProducts());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getproductById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(productServices.getProductById(id));
    }


    @PostMapping("")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return productServices.addProduct(productDTO);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") long id) {
        productServices.deleteProductById(id);
        return new ResponseEntity<>("product has been Deleted successfully.", HttpStatus.ACCEPTED);
    }
}
