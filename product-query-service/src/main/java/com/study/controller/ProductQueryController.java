package com.study.controller;

import com.study.entity.Product;
import com.study.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductQueryController {
    private final ProductService productService;
    @GetMapping("/all")
    public List<Product> getAll (){
        return productService.getAllProduct();
    }

}