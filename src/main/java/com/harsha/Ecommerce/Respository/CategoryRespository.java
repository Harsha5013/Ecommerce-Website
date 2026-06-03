package com.harsha.Ecommerce.Respository;

import com.harsha.Ecommerce.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRespository extends JpaRepository<Category,Long> {
}
