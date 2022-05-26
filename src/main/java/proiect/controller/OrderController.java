package proiect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proiect.entity.Product;
import proiect.entity.User;
import proiect.service.OrderService;
import proiect.service.ProductService;
import proiect.service.UserService;

import java.util.HashMap;
import java.util.Optional;

@Controller
public class OrderController {
    private HashMap<Long, Product> cartProducts = new HashMap<>();

    @Autowired
    private final ProductService productsService;

    @Autowired
    private final UserService usersService;

    @Autowired
    private final OrderService ordersService;

    public OrderController(ProductService productsService, UserService usersService, OrderService ordersService) {
        this.productsService = productsService;
        this.usersService = usersService;
        this.ordersService = ordersService;
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") Long productId) throws Exception {
        Optional<Product> optionalProduct = productsService.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new Exception("Product not found");
        } else {
            Product product = optionalProduct.get();
            cartProducts.put(product.getId(), product);
            return "redirect:/cart";
        }
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        model.addAttribute("cartProducts", cartProducts);
        return "/home/cart";
    }

    @PostMapping("/cart/removeItem")
    public String removeItemFromCart(@RequestParam("productId") Long productId) throws Exception {
        Optional<Product> optionalProduct = productsService.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new Exception("Product not found");
        } else {
            Product product = optionalProduct.get();
            cartProducts.remove(product.getId());
            return "redirect:/cart";
        }
    }

    @PostMapping("/order")
    public String saveOrder(@AuthenticationPrincipal UserDetails currentUser) {
        User user = usersService.findUser(currentUser.getUsername());
        ordersService.createOrder(cartProducts, user);
        return "redirect:/finalizeOrder";
    }

    @GetMapping("/finalizeOrder")
    public String finalizeOrder() {
        return "/home/finalize_order";
    }
}
