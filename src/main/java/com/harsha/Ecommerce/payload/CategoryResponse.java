package com.harsha.Ecommerce.payload;

import java.util.List;

public class CategoryResponse {
    public List<CategoryDTO> content;

    public CategoryResponse(List<CategoryDTO> content) {
        this.content = content;
    }

    public CategoryResponse() {
    }

    public List<CategoryDTO> getContent() {
        return content;
    }

    public void setContent(List<CategoryDTO> content) {
        this.content = content;
    }
}
