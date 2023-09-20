package com.study.service;

import com.study.config.RabbitMQConfig;
import com.study.dto.ProductEvent;
import com.study.entity.Product;
import com.study.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void processProudctEvent (ProductEvent productEvent){
        Product product = productEvent.getProduct();
        System.out.println("Message recieved to : " + productEvent);
        if (productEvent.getEventtype().equals("CreateProduct")){
            productRepository.save(productEvent.getProduct());
        }
        if (productEvent.getEventtype().equals("UpdateProduct")){
            Product updateProduct = productRepository.findById(product.getId()).get();
            updateProduct.setName(product.getName());
            updateProduct.setDescription(product.getDescription());
            updateProduct.setPrice(product.getPrice());
            productRepository.save(updateProduct);
        }
    }
}
