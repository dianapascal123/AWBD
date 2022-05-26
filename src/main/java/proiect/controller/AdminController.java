package proiect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import proiect.entity.Category;
import proiect.entity.ProductView;
import proiect.service.CategoryService;
import proiect.service.OrderService;
import proiect.service.ProductViewService;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    ProductViewService productViewService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderService orderService;

    @Autowired
    public AdminController(ProductViewService productViewService, CategoryService categoryService, OrderService orderService) {
        this.productViewService = productViewService;
        this.categoryService = categoryService;
        this.orderService = orderService;
    }

    @GetMapping("/admin")
    public String productsViews(Model model) {
        List<ProductView> productViews = productViewService.findAll();
        long ordersCount = orderService.count();
        List<Category> categories = categoryService.findAll();

        model.addAttribute("productsViews", productViews);
        model.addAttribute("ordersCount", ordersCount);
        model.addAttribute("categories", categories);

        return "/home/admin";
    }

    @RequestMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable String id){
        categoryService.deleteById(Long.valueOf(id));
        return "redirect:/admin";
    }
}
