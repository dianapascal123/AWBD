package proiect.service;

import org.springframework.stereotype.Service;
import proiect.entity.PerfumeOrder;
import proiect.entity.Product;
import proiect.entity.User;
import proiect.repository.OrderRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Service
public class OrderServiceImpl implements OrderService{
    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public PerfumeOrder createOrder(HashMap<Long, Product> productHashMap, User user) {
        PerfumeOrder order = new PerfumeOrder();
        ArrayList<Product> productsList = new ArrayList<Product>(productHashMap.values());
        order.setProducts(productsList);
        order.setUser(user);
        order.setOrderDate(new Date());
        return orderRepository.save(order);
    }

    @Override
    public long count() {
        return orderRepository.count();
    }
}
