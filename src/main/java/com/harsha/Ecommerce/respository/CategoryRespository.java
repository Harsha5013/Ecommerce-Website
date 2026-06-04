package com.harsha.Ecommerce.respository;

import com.harsha.Ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRespository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String categoryName);
}
