package shopease.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import shopease.entity.Category;
import shopease.entity.Product;
import shopease.repository.CategoryRepository;
import shopease.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataSeeder(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        // Only seed if the categories table is empty. Idempotent.
        if (categoryRepository.count() > 0) {
            return;
        }

        Category electronics = categoryRepository.save(new Category("Electronics", "electronics"));
        Category books = categoryRepository.save(new Category("Books", "books"));
        Category clothing = categoryRepository.save(new Category("Clothing", "clothing"));
        Category homeKitchen = categoryRepository.save(new Category("Home & Kitchen", "home-kitchen"));

        productRepository.saveAll(List.of(
            buildProduct("Wireless Headphones",
                "Over-ear Bluetooth headphones with active noise cancellation and 30-hour battery life.",
                new BigDecimal("129.99"), 25, "https://placehold.co/600x600?text=Headphones",
                electronics),

            buildProduct("Mechanical Keyboard",
                "Compact 75% mechanical keyboard with hot-swappable switches and RGB backlighting.",
                new BigDecimal("89.50"), 40, "https://placehold.co/600x600?text=Keyboard",
                electronics),

            buildProduct("4K Webcam",
                "Ultra HD webcam with auto-framing, dual microphones, and USB-C connectivity.",
                new BigDecimal("149.00"), 15, "https://placehold.co/600x600?text=Webcam",
                electronics),

            buildProduct("Clean Code",
                "A handbook of agile software craftsmanship by Robert C. Martin.",
                new BigDecimal("34.99"), 60, "https://placehold.co/600x600?text=Clean+Code",
                books),

            buildProduct("The Pragmatic Programmer",
                "20th anniversary edition. Your journey to mastery.",
                new BigDecimal("39.99"), 55, "https://placehold.co/600x600?text=Pragmatic",
                books),

            buildProduct("Spring Boot in Action",
                "Comprehensive guide to building production-grade Spring Boot applications.",
                new BigDecimal("44.50"), 30, "https://placehold.co/600x600?text=Spring+Boot",
                books),

            buildProduct("Cotton T-Shirt",
                "100% organic cotton, pre-shrunk, classic fit.",
                new BigDecimal("19.99"), 100, "https://placehold.co/600x600?text=T-Shirt",
                clothing),

            buildProduct("Denim Jacket",
                "Vintage-wash denim jacket with a relaxed fit.",
                new BigDecimal("79.00"), 20, "https://placehold.co/600x600?text=Jacket",
                clothing),

            buildProduct("Ceramic Coffee Mug",
                "400ml stoneware mug with a matte glaze finish.",
                new BigDecimal("14.50"), 75, "https://placehold.co/600x600?text=Mug",
                homeKitchen),

            buildProduct("Cast Iron Skillet",
                "12-inch pre-seasoned cast iron skillet.",
                new BigDecimal("45.00"), 35, "https://placehold.co/600x600?text=Skillet",
                homeKitchen)
        ));
    }

    private Product buildProduct(String name, String description, BigDecimal price,
                                 int stock, String imageUrl, Category category) {
        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        p.setStockQuantity(stock);
        p.setImageUrl(imageUrl);
        p.setCategory(category);
        p.setActive(true);
        return p;
    }
}