package com.somaditya.product.controller;

import com.somaditya.product.model.Product;
import com.somaditya.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        
        if (!product.isPresent()) {
            log.error("ID " + id + "does not exist.");
            ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(product.get());
    }
    
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product productUpdate)
            throws Exception {
        Product product = productService.findById(id).orElseThrow(() ->
                new ResourceAccessException("Product with id " + id + " not found!"));
        
        product.setProductName(productUpdate.getProductName());
        product.setDescription(productUpdate.getDescription());
        product.setPrice(productUpdate.getPrice());
        product.setUpdatedAt(new Date());
        
        final Product updatedProduct = productService.save(product);
        
        return ResponseEntity.ok(updatedProduct);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!productService.findById(id).isPresent()) {
            log.error("ID " + id + "does not exist.");
            ResponseEntity.badRequest().build();
        }
        
        productService.deleteById(id);
        
        return ResponseEntity.ok().build();
    }
}
