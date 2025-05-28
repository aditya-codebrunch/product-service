package dev.codebrunch.productservice.services;

import dev.codebrunch.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    boolean deleteProduct(Long productId);

    Product updateProduct(Long productId, Product product);

    Product partialUpdateProduct(Long productId, Product product);

}
