package dev.codebrunch.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundExceptionDto extends ExceptionDto{
    private Long productId;

}
