package com.harsha.Ecommerce.Service;

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

        categoryRespository.save(category);

    }


    public String deleteCategory(Long categoryId){
        Category category = categoryRespository.findById(categoryId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        categoryRespository.delete(category);
        return "Category with CategoryId "+categoryId+" is deleted.";
    }


@Override
    public Category updateCategory(Category category,Long categoryId){

        Category savedCategory = categoryRespository.findById(categoryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        category.setCategoryId(categoryId);
        savedCategory = categoryRespository.save(category);
        return savedCategory;
    }

}
