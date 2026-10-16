package shopease.service;

import shopease.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> listActiveProducts();

    Product getActiveProductById(Long id);

    /**
     * Search products by keyword and/or category.
     * Both params are optional.
     *   - keyword null/blank + category null/blank → all active products
     *   - keyword present + category absent        → name match only
     *   - keyword absent + category present        → category match only
     *   - both present                             → name match within category
     */
    List<Product> searchProducts(String keyword, String categorySlug);
}