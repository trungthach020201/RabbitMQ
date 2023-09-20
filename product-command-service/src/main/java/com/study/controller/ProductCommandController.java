package com.study.controller;

import com.study.config.RabbitMQConfig;
import com.study.dto.Order;
import com.study.dto.ProductEvent;
import com.study.dto.ProductStatus;
import com.study.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductCommandController {
    private final ProductService productService;
    @PostMapping("/create")
    public ResponseEntity createProduct(@RequestBody ProductEvent productEvent) {
        return ResponseEntity.ok(productService.createProduct(productEvent));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@RequestBody ProductEvent productEvent, @PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(productService.updateProduct(productEvent,id));
    }
}