package com.study.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.study.config.CustomDeserializer;
import com.study.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {
    @JsonDeserialize(using = CustomDeserializer.class)
    private String eventtype;
    private Product product;
}