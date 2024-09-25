package com.project.ecommerce.service.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.ecommerce.exceptions.AlreadyExistsException;
import com.project.ecommerce.exceptions.CategoryNotFoundException;
import com.project.ecommerce.model.Category;
import com.project.ecommerce.repository.CategoryRepository;
import com.project.ecommerce.service.abstracts.CategoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService{
	
	private CategoryRepository categoryRepository;
	
	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(()-> new CategoryNotFoundException("Category not found!"));
	}

	@Override
	public Category getCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category addCategory(Category category) {
		return Optional.of(category).filter(c->!categoryRepository.existsByName(c.getName()))
				.map(categoryRepository :: save).orElseThrow(()-> new AlreadyExistsException(category.getName()+" already exists!"));
	}

	@Override
	public Category updateCategory(Category category,Long id) {
		return Optional.ofNullable(getCategoryById(id)).map(oldCategory -> {
			oldCategory.setName(category.getName());
			return categoryRepository.save(oldCategory);
		}).orElseThrow(()-> new CategoryNotFoundException("Category not found!"));
	}

	@Override
	public void deleteCategoryById(Long id) {
		categoryRepository.findById(id).ifPresentOrElse(categoryRepository :: delete,
				()->{throw new CategoryNotFoundException("Category not found!");});
	}

}
