package com.study.dto;

import com.study.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductStatus {
    private Product product;
    private String status;//progress,completed
    private String message;
}