package com.example.demoenversaudited;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/product/{id}/{price}")
    @Transactional
    public String updatePrice(@PathVariable Long id, @PathVariable Double price) {
        try {
            Optional<Product> product = productRepository.findById(id);
            product.ifPresent(p -> p.setPrice(price));
            productRepository.save(product.get());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return "Done";
    }

}
