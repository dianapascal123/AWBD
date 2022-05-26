package proiect.service;

import proiect.entity.PerfumeOrder;
import proiect.entity.Product;
import proiect.entity.User;

import java.util.HashMap;

public interface OrderService {
    PerfumeOrder createOrder(HashMap<Long, Product> productHashMap, User user);

    long count();
}
