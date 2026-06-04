package com.harsha.Ecommerce.service;

import com.harsha.Ecommerce.model.Category;
import com.harsha.Ecommerce.payload.CategoryDTO;
import com.harsha.Ecommerce.payload.CategoryResponse;

import java.util.List;


public interface CategoryService {
    CategoryResponse getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    String deleteCategory(Long categoryId);

    Category updateCategory(Category category,Long categoryId);
}
