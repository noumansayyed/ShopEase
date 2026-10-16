package shopease.service.impl;

import org.springframework.stereotype.Service;

import shopease.entity.Product;
import shopease.exception.ProductNotFoundException;
import shopease.repository.ProductRepository;
import shopease.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listActiveProducts() {
        return productRepository.findByActiveTrue();
    }

    @Override
    public Product getActiveProductById(Long id) {
        return productRepository.findById(id)
                .filter(Product::isActive)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
    }
}