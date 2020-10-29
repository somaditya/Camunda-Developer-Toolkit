package com.somaditya.product.controller;

import com.somaditya.product.model.Product;
import com.somaditya.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    
    // TODO should update existing record instead of adding new copy
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product) {
        if (!productService.findById(id).isPresent()) {
            log.error("ID " + id + "does not exist.");
            ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(productService.save(product));
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
