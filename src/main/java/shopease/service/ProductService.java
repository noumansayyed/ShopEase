package shopease.service;

import shopease.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> listActiveProducts();

    Product getActiveProductById(Long id);
}