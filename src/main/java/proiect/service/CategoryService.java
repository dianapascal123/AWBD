package proiect.service;

import proiect.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    void deleteById(Long id);
}
