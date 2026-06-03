package com.harsha.Ecommerce.Controller;

import com.harsha.Ecommerce.Service.CategoryService;
import com.harsha.Ecommerce.Model.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CategoryController {

     @Autowired
     private CategoryService categoryService;
     @GetMapping("api/public/categories")
     public List<Category> getAllCategories(){
         return categoryService.getAllCategories();
     }

     @PostMapping("api/public/categories")
     public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
         categoryService.createCategory(category);
         return new ResponseEntity<>("category added successfully",HttpStatus.CREATED);
     }

     @DeleteMapping("api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
         try {
             String status = categoryService.deleteCategory(categoryId);
             return new ResponseEntity<>(status, HttpStatus.OK);
         } catch(ResponseStatusException e){
             return new ResponseEntity<>(e.getReason(),e.getStatusCode());
         }
    }
    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable Long categoryId){
         try{
             Category savedCategory= categoryService.updateCategory(category,categoryId);
             return new ResponseEntity<>("Category updated "+category,HttpStatus.OK);
         }catch(ResponseStatusException e){
             return new ResponseEntity<>(e.getReason(),e.getStatusCode());
         }
    }
}
