package com.odev1.business.abstracts;

import com.odev1.entities.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity save(Product product);

    ResponseEntity search(String q);

    ResponseEntity getAll();

    ResponseEntity update(Product product);

    ResponseEntity delete(String id);

    ResponseEntity sortedPrice(int price);
}
