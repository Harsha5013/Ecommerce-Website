package com.harsha.Ecommerce.service;

import com.harsha.Ecommerce.exceptions.APIException;
import com.harsha.Ecommerce.exceptions.ResourceNotFoundException;
import com.harsha.Ecommerce.model.Category;
import com.harsha.Ecommerce.payload.CategoryDTO;
import com.harsha.Ecommerce.payload.CategoryResponse;
import com.harsha.Ecommerce.respository.CategoryRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Category> categoryPage = categoryRespository.findAll(pageDetails);
        List<Category> categories = categoryPage.getContent();
        if(categories.isEmpty())
            throw new APIException("no categories created.");
        List<CategoryDTO>categoryDTOS=categories.stream().map(category -> modelMapper.map(category,CategoryDTO.class)).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO,Category.class);
        Category categoryFromDb = categoryRespository.findByCategoryName(categoryDTO.getCategoryName());
        if(categoryFromDb!=null)
            throw new APIException("Category with the name"+categoryDTO.getCategoryName()+" already exists");
        Category savedCategory=categoryRespository.save(category);
        return modelMapper.map(category,CategoryDTO.class);

    }


    public CategoryDTO deleteCategory(Long categoryId){
        Category category = categoryRespository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
        categoryRespository.delete(category);
        return modelMapper.map(category,CategoryDTO.class);
    }


@Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO,Long categoryId){
        Category category = modelMapper.map(categoryDTO,Category.class);
        Category categoryFromDb = categoryRespository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","categoryId",categoryId));
        category.setCategoryId(categoryId);
        Category savedCategory = categoryRespository.save(category);
        return modelMapper.map(category,CategoryDTO.class);
    }

}
