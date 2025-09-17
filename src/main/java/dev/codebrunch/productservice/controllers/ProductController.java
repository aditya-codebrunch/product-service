package dev.codebrunch.productservice.controllers;

import dev.codebrunch.productservice.dtos.ExceptionDto;
import dev.codebrunch.productservice.dtos.ProductNotFoundExceptionDto;
import dev.codebrunch.productservice.exceptions.CategoryNotFoundException;
import dev.codebrunch.productservice.exceptions.ProductNotFoundException;
import dev.codebrunch.productservice.models.Product;
import dev.codebrunch.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntimeException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Internal Server Error from Controller");
        exceptionDto.setResolutionDetails("We are working on it. Please try again later");
        return new ResponseEntity<>(exceptionDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException exception) {
        ProductNotFoundExceptionDto exceptionDto = new ProductNotFoundExceptionDto();
        exceptionDto.setProductId(exception.getProductId());
        exceptionDto.setMessage("Product not found, this is from Controller");
        exceptionDto.setResolutionDetails("Product with id " + exception.getProductId() + " not found. Please check the id and try again");
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    private ProductService productService;

    public ProductController(@Qualifier("realStoreProductService") ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {

        Product product = this.productService.getSingleProduct(productId);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){

        List<Product> products = this.productService.getAllProducts();
        return products;
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product) throws CategoryNotFoundException {
        productService.createProduct(product);
        return new Product();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) {
        return new Product();
    }

    @PatchMapping("/{id}")
    public Product partialUpdateProduct(@PathVariable("id") Long productId, @RequestBody Product product) {
        return new Product();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long productId) {
        return  ResponseEntity.ok().build();
    }

}
