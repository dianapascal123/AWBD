package proiect.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import proiect.entity.PerfumeOrder;
import proiect.entity.Product;
import proiect.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("h2")
@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void count() {
        long cnt = orderRepository.count();
        Assert.assertEquals(1, cnt);
    }

    @Test
    public void save() {
        PerfumeOrder order = new PerfumeOrder();

        User user = userRepository.findById(Long.valueOf(1)).get();
        Product product = productRepository.findById(Long.valueOf(5)).get();
        List<Product> products = new ArrayList<>();
        products.add(product);

        order.setUser(user);
        order.setProducts(products);
        order.setOrderDate(new Date());

        PerfumeOrder savedOrder = orderRepository.save(order);
        Optional<PerfumeOrder> checkedOrder = orderRepository.findById(savedOrder.getId());
        Assert.assertTrue(checkedOrder.isPresent());
    }
}
