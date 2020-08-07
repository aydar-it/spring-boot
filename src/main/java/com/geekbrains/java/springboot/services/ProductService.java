package com.geekbrains.java.springboot.services;

import com.geekbrains.java.springboot.models.Product;
import com.geekbrains.java.springboot.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product find(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("No results for id " + id));
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductWithMaxPriceFiltering(Integer maxPrice) {
        List<Product> list = this.getAll();
        if (maxPrice == null) {
            return list;
        }
        list.removeIf(e -> e.getPrice() > maxPrice);
        return list;
    }

    public long getProductCount() {
        return productRepository.count();
    }
}
