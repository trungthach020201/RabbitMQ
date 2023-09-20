package com.study.service;

import com.study.config.RabbitMQConfig;
import com.study.dto.ProductEvent;
import com.study.entity.Product;
import com.study.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final RabbitTemplate template;
    public Product createProduct(ProductEvent productEvent){
        Product productDO = productRepository.save(productEvent.getProduct());
        ProductEvent event = new ProductEvent("CreateProduct",productDO);
        template.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, RabbitMQConfig.ROUTING_KEY, event);
        System.out.println("The new product id: "+event.getProduct().getId());
        return productDO;
    }

    public Product updateProduct(ProductEvent productEvent, Long id) throws ChangeSetPersister.NotFoundException {
        Product updateProduct = productRepository.findById(id).orElseThrow(()-> new ChangeSetPersister.NotFoundException());
        Product product = productEvent.getProduct();
        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setDescription(product.getDescription());
        Product productDO = productRepository.save(updateProduct);
        ProductEvent event = new ProductEvent("UpdateProduct",productDO);
        template.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, RabbitMQConfig.ROUTING_KEY, event);
        return productDO;
    }
}
