package com.odev1.business.concretes;

import com.odev1.business.abstracts.ProductService;
import com.odev1.entities.Product;
import com.odev1.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductManager implements ProductService {

    final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity save(Product product) {
        Map<String,Object> hm = new LinkedHashMap<>();
        productRepository.save(product);
        hm.put("status",true);
        hm.put("result", product);

        return new ResponseEntity(hm, HttpStatus.OK);
    }

    @Override
    public ResponseEntity search(String q) {
        List<Product> productList = productRepository.findByTitleContainsIgnoreCaseOrDetailContainsIgnoreCase(q,q);
        return new ResponseEntity(productList,HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAll() {
        List<Product> productList = productRepository.findAll();
        return new ResponseEntity(productList,HttpStatus.OK);

    }

    @Override
    public ResponseEntity update(Product product) {
        Map<String,Object> hm = new HashMap<>();
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if(optionalProduct.isPresent()){
            productRepository.saveAndFlush(product);
            hm.put("message",product);
            hm.put("status",true);
        }else{
            hm.put("message", "Fail uid");
            hm.put("status", false);
        }return new ResponseEntity(hm,HttpStatus.OK);
    }

    @Override
    public ResponseEntity delete(String id) {
        Map<String,Object> hm = new HashMap<>();
        try {
            int iid = Integer.parseInt(id);
            productRepository.deleteById(iid);
            hm.put("status: " ,true);
        }catch (Exception ex){
            hm.put("message", "id request : " + id);
            hm.put("status", false);
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    @Override
    public ResponseEntity sortedPrice(int price) {
        Map<String,Object> hm = new HashMap<>();
        List<Product> productList = productRepository.findByPriceGreaterThanEqual(price);
        return new ResponseEntity(productList,HttpStatus.OK);
    }
}
