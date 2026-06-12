package com.harsha.Ecommerce.service;

import com.harsha.Ecommerce.model.Category;
import com.harsha.Ecommerce.payload.CategoryDTO;
import com.harsha.Ecommerce.payload.CategoryResponse;

import java.util.List;


public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO,Long categoryId);
}
