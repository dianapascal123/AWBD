package proiect.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import proiect.configuration.SecurityH2Config;
import proiect.entity.Product;
import proiect.service.ProductService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest()

@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @MockBean
    Model model;

    @Test
    @WithMockUser(roles = "CLIENT")
    public void getProductById() throws Exception {
        Long id = 5l;
        Product product = new Product();
        product.setId(id);
        product.setTitle("test");
        product.setCategory(null);
        product.setBrand("test");
        product.setDescription("test");
        product.setFragranceTopNotes("test");
        product.setPrice(100F);
        product.setVolume("test");
        product.setYear(2022);

        when(productService.findById(id)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/product/{id}", "5"))
                .andExpect(status().isOk())
                .andExpect(view().name("/home/view"))
                .andExpect(model().attribute("product", product));
    }
}
