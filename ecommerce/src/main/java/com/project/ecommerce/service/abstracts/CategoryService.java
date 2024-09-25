package com.project.ecommerce.service.abstracts;

import java.util.List;

import com.project.ecommerce.model.Category;

public interface CategoryService {
	Category getCategoryById(Long id);
	Category getCategoryByName(String name);
	List<Category> getAllCategories();
	Category addCategory(Category category);
	Category updateCategory(Category category,Long id);
	void deleteCategoryById(Long id);
}
