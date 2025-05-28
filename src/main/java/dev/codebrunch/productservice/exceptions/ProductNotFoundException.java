package dev.codebrunch.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends RuntimeException {
    private Long productId;
    public ProductNotFoundException(String id) {
        super(id);
        this.productId = Long.parseLong(id);
    }
}
