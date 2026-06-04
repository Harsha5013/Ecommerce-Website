package com.harsha.Ecommerce.payload;

public class CategoryDTO {
    private String CategoryName;
    private Long CategoryId;

    public CategoryDTO() {
    }

    public CategoryDTO(String categoryName, Long categoryId) {
        CategoryName = categoryName;
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public Long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Long categoryId) {
        CategoryId = categoryId;
    }
}
