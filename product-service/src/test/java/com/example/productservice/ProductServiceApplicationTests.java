package com.example.productservice;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;


    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {

        ProductRequest productRequest = getProductRequest();
        String valueAsString = objectMapper.writeValueAsString(productRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Assertions.assertEquals(1, productRepository.findAll().size());
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        insertTestData();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private void insertTestData() {
        Product product1 = Product.builder()
                .name("Test1")
                .description("Test1")
                .price(BigDecimal.ONE)
                .build();

        Product product2 = Product.builder()
                .name("Test2")
                .description("Test2")
                .price(BigDecimal.ONE)
                .build();
        productRepository.save(product1);
        productRepository.save(product2);
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("Iphone 13")
                .description("Iphone 13")
                .price(BigDecimal.valueOf(1200))
                .build();
    }

}
