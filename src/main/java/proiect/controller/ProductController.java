package proiect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import proiect.entity.Product;
import proiect.entity.ProductView;
import proiect.service.ProductService;
import proiect.service.ProductViewService;

import java.util.Optional;

@Controller
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductViewService productViewService;

    @Autowired
    public ProductController(ProductService productService, ProductViewService productViewService) {
        this.productService = productService;
        this.productViewService = productViewService;
    }

    @GetMapping("/")
    public String home(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 50) Pageable pageable, Model model) {
        Page<Product> products = productService.findAllProductsPageable(pageable);
        model.addAttribute("products", products);

        return "/home/main";
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model) {
        Optional<Product> optionalProduct = productService.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            model.addAttribute("product", product);

            Optional<ProductView> productView = productViewService.findByProduct(product);
            productViewService.incrementAndSave(productView, product);
            return "/home/view";
        } else {
            log.debug("Product not found");
            return "redirect:/products/all";
        }
    }
}
