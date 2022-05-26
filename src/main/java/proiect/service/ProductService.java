package proiect.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import proiect.entity.Product;

import java.util.Optional;

public interface ProductService {
     Page<Product> findAllProductsPageable(Pageable pageable);

     Optional<Product> findById(Long id);
}
