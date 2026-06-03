package com.harsha.Ecommerce.Service;

import com.harsha.Ecommerce.Exceptions.APIException;
import com.harsha.Ecommerce.Exceptions.ResourceNotFoundException;
import com.harsha.Ecommerce.Model.Category;
import com.harsha.Ecommerce.Respository.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRespository categoryRespository ;
//
    @Override
    public List<Category> getAllCategories() {
        return categoryRespository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRespository.findByCategoryName(category.getCategoryName());
        if(savedCategory!=null)
            throw new APIException("Category with the name"+category.getCategoryName()+" already exists");
        categoryRespository.save(category);

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
