package dev.codebrunch.productservice.controllerAdvice;

import dev.codebrunch.productservice.dtos.ExceptionDto;
import dev.codebrunch.productservice.dtos.ProductNotFoundExceptionDto;
import dev.codebrunch.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductServiceExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntimeException(){
            ExceptionDto exceptionDto = new ExceptionDto();
            exceptionDto.setMessage("Internal Server Error");
            exceptionDto.setResolutionDetails("We are working on it. Please try again later");
            return new ResponseEntity<>(exceptionDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException exception) {
        ProductNotFoundExceptionDto exceptionDto = new ProductNotFoundExceptionDto();
        exceptionDto.setProductId(exception.getProductId());
        exceptionDto.setMessage("Product not found");
        exceptionDto.setResolution("Product with id " + exception.getProductId() + " not found. Please check the id and try again");
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
