package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.entity.Product;
import proiect.entity.ProductView;
import proiect.repository.ProductViewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductViewServiceImpl implements ProductViewService{
    ProductViewRepository productViewRepository;

    @Autowired
    public ProductViewServiceImpl(ProductViewRepository productViewRepository) {
        this.productViewRepository = productViewRepository;
    }


    @Override
    public List<ProductView> findAll() {
        return productViewRepository.findAll();
    }

    @Override
    public Optional<ProductView> findByProduct(Product product) {
        return productViewRepository.findByProduct(product);
    }

    @Override
    public ProductView save(ProductView productView) {
        return productViewRepository.save(productView);
    }

    @Override
    public void incrementAndSave(Optional<ProductView> productViewOptional, Product product) {
        ProductView productView;
        if (productViewOptional.isPresent()) {
            productView = productViewOptional.get();
            int counter = productView.getViewsNumber();
            counter = counter + 1;
            productView.setViewsNumber(counter);
        } else {
            productView = new ProductView();
            productView.setViewsNumber(1);
            productView.setProduct(product);
        }
         save(productView);
    }
}
