package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.entity.Category;
import proiect.entity.Product;
import proiect.repository.CategoryRepository;
import proiect.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    CategoryRepository categoryRepository;
    ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            throw new RuntimeException("Product not found!");
        }

        Category category = optionalCategory.get();
        List<Product> products = category.getProducts();
        for (Product product : products) {
            product.setCategory(null);
        }
        categoryRepository.deleteById(id);
    }
}
