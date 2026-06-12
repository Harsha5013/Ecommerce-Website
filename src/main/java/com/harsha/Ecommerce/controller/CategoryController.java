package com.harsha.Ecommerce.controller;

import com.harsha.Ecommerce.config.AppConstants;
import com.harsha.Ecommerce.payload.CategoryDTO;
import com.harsha.Ecommerce.payload.CategoryResponse;
import com.harsha.Ecommerce.service.CategoryService;
import com.harsha.Ecommerce.model.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

     @Autowired
     private CategoryService categoryService;


     @GetMapping("api/public/categories")
     public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                              @RequestParam(name = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
                                                              @RequestParam(name = "sortCategoriesBy",defaultValue = AppConstants.sortCategoriesBy,required = false) String sortBy,
                                                              @RequestParam(name = "sortOrder",defaultValue = AppConstants.sortOrder,required = false)String sortOrder){
         CategoryResponse categoryResponse = categoryService.getAllCategories( pageNumber, pageSize,sortBy,sortOrder);
         return new ResponseEntity<CategoryResponse>(categoryResponse,HttpStatus.OK);
     }

     @PostMapping("api/public/categories")
     public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
         CategoryDTO savedCategoryDTO=categoryService.createCategory(categoryDTO);
         return new ResponseEntity<>(savedCategoryDTO,HttpStatus.CREATED);
     }

     @DeleteMapping("api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
             CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
             return new ResponseEntity<>(deletedCategory, HttpStatus.OK);

    }
    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable Long categoryId){

             CategoryDTO savedCategory= categoryService.updateCategory(categoryDTO,categoryId);
             return new ResponseEntity<>(savedCategory,HttpStatus.OK);

    }
}
