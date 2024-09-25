package com.project.ecommerce.service.abstracts;

import java.util.List;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.request.AddProductRequest;
import com.project.ecommerce.request.UpdateProductRequest;

public interface ProductService {
	Product addProduct(AddProductRequest request);
	List<Product> getAllProducts();
	Product getProductById(Long id);
	void deleteProductById(Long id);
	Product updateProduct(UpdateProductRequest request,Long id);
	List<Product> getProductsByCategoryName(String categoryName);
	List<Product> getProductsByBrand(String brand);
	List<Product> getProductsByCategoryAndBrand(String categoryName,String brand);
	List<Product> getProductsByName(String name);
	List<Product> getProductsByBrandAndName(String brand,String name);
	Long countProductsByBrandAndName(String brand,String name);
}
