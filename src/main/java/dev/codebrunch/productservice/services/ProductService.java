package dev.codebrunch.productservice.services;

import dev.codebrunch.productservice.exceptions.CategoryNotFoundException;
import dev.codebrunch.productservice.exceptions.ProductNotFoundException;
import dev.codebrunch.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(Product product) throws CategoryNotFoundException;

    void deleteProduct(Long productId);

    Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize);

}
