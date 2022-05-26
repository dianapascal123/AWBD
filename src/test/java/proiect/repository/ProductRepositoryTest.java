package proiect.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import proiect.entity.Product;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("h2")
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void findPage() {
        Pageable firstPage = PageRequest.of(0, 2);
        Page<Product> allProducts = productRepository.findAll(firstPage);
        Assert.assertTrue(allProducts.getNumberOfElements() == 1);
    }

    @Test
    public void findById() {
        Optional<Product> product = productRepository.findById(Long.valueOf(5));
        Assert.assertTrue(product.isPresent());
    }
}
