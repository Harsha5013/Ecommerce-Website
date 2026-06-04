package com.harsha.Ecommerce.service;

import com.harsha.Ecommerce.exceptions.APIException;
import com.harsha.Ecommerce.exceptions.ResourceNotFoundException;
import com.harsha.Ecommerce.model.Category;
import com.harsha.Ecommerce.payload.CategoryDTO;
import com.harsha.Ecommerce.payload.CategoryResponse;
import com.harsha.Ecommerce.respository.CategoryRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRespository categoryRespository ;

    @Autowired
    ModelMapper modelMapper;
//
    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRespository.findAll();
        if(categories.isEmpty())
            throw new APIException("no categories created.");
        List<CategoryDTO>categoryDTOS=categories.stream().map(category -> modelMapper.map(category,CategoryDTO.class)).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category savedCategory = categoryRespository.findByCategoryName(categoryDTO.getCategoryName());
        if(savedCategory!=null)
            throw new APIException("Category with the name"+categoryDTO.getCategoryName()+" already exists");
        categoryRespository.save(categoryDTO);
        return categoryDTO;

    }


    public String deleteCategory(Long categoryId){
        Category category = categoryRespository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
        categoryRespository.delete(category);
        return "Category with CategoryId "+categoryId+" is deleted.";
    }


@Override
    public Category updateCategory(Category category,Long categoryId){

        Category savedCategory = categoryRespository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","categoryId",categoryId));
        category.setCategoryId(categoryId);
        savedCategory = categoryRespository.save(category);
        return savedCategory;
    }

}
