package dev.codebrunch.productservice.services;

import dev.codebrunch.productservice.dtos.FakeStoreProductDto;
import dev.codebrunch.productservice.exceptions.ProductNotFoundException;
import dev.codebrunch.productservice.models.Category;
import dev.codebrunch.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {


    private final String FAKESTORE_API_URL = "https://fakestoreapi.com/products/";
    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long id)  throws ProductNotFoundException{
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = 
                restTemplate.getForEntity(FAKESTORE_API_URL + id, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException(id.toString());
        }
        Product product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponseEntities =
                restTemplate.getForEntity(FAKESTORE_API_URL,FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreProductDtoResponseEntities.getBody();
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        return new Product();
    }

    @Override
    public boolean deleteProduct(Long id) {
        // Implementation for delete operation
        return false;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return new Product();
    }

    @Override
    public Product partialUpdateProduct(Long id, Product product) {
        return new Product();
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(fakeStoreProductDto.getImage());

        return product;
    }

}
