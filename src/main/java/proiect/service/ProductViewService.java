package proiect.service;

import proiect.entity.Product;
import proiect.entity.ProductView;

import java.util.List;
import java.util.Optional;

public interface ProductViewService {
    List<ProductView> findAll();

    Optional<ProductView> findByProduct(Product product);

    ProductView save(ProductView productView);

    void incrementAndSave(Optional<ProductView> productViewOptional, Product product);
}
