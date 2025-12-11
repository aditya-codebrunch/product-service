package dev.codebrunch.productservice;

import lombok.Value;

import java.io.Serializable;


@Value
public class ProductDto implements Serializable {
    String title;
    Double price;
    String description;
}
