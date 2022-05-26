package proiect.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ActiveProfiles;
import proiect.entity.Category;
import proiect.repository.CategoryRepository;
import proiect.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("h2")
public class CategoryServiceTest {
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ProductRepository productRepository;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository, productRepository);
    }

    @Test
    public void findAll() {
        List<Category> categoriesRet = new ArrayList<>();
        Category category = new Category();
        category.setId(2L);
        category.setTitle("Test category");
        category.setProducts(null);
        categoriesRet.add(category);

        when(categoryRepository.findAll()).thenReturn(categoriesRet);
        List<Category> categories = categoryRepository.findAll();
        assertEquals(categories.size(), 1);
        assertEquals(categories, categoriesRet);
    }

}
