package com.odev1.restController;

import com.odev1.business.abstracts.ProductService;
import com.odev1.entities.Product;
import com.odev1.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String q){
        return productService.search(q);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return productService.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Product product){
        return productService.update(product);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam String id) {
        return productService.delete(id);
    }

    @GetMapping("/sortedprice")
    public ResponseEntity sortedPrice(int price){
        return productService.sortedPrice(price);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exception) {
        ApiError error = new ApiError(400, "Validation error", "/product/save");
        Map<String, String> validationErrors = new HashMap<>();
        for(FieldError fieldError: exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        error.setValidationErrors(validationErrors);
        return error;
    }
}
