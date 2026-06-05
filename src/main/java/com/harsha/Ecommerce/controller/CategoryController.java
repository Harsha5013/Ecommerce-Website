package com.harsha.Ecommerce.controller;

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
     public ResponseEntity<CategoryResponse> getAllCategories(){
         CategoryResponse categoryResponse = categoryService.getAllCategories();
         return new ResponseEntity<CategoryResponse>(categoryResponse,HttpStatus.OK);
     }

     @PostMapping("api/public/categories")
     public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
         CategoryDTO savedCategoryDTO=categoryService.createCategory(categoryDTO);
         return new ResponseEntity<>(savedCategoryDTO,HttpStatus.CREATED);
     }

     @DeleteMapping("api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
             String status = categoryService.deleteCategory(categoryId);
             return new ResponseEntity<>(status, HttpStatus.OK);

    }
    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable Long categoryId){

             Category savedCategory= categoryService.updateCategory(category,categoryId);
             return new ResponseEntity<>("Category updated "+category,HttpStatus.OK);

    }
}
