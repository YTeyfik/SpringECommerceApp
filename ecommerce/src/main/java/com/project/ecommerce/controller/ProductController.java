package com.project.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.exceptions.ResourceNotFoundException;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.request.AddProductRequest;
import com.project.ecommerce.request.UpdateProductRequest;
import com.project.ecommerce.response.ApiResponse;
import com.project.ecommerce.service.abstracts.ProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
	//@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllProducts(){
		List<Product> products=productService.getAllProducts();
		return ResponseEntity.ok(new ApiResponse("success", products));
	}
	
	@GetMapping("/by/{id}")
	public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id){
		try {
			Product products=productService.getProductById(id);
			return ResponseEntity.ok(new ApiResponse("success", products));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/by/brand-and-name")
	public ResponseEntity<ApiResponse> getProductsByBrandAndName(@RequestParam String brand,@RequestParam String name){
		try {
			List<Product> products=productService.getProductsByBrandAndName(brand, name);
			if(products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Products not found!", null));
			}
			return ResponseEntity.ok(new ApiResponse("success", products));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@GetMapping("/by/category-and-brand")
	public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@RequestParam String category,@RequestParam String brand){
		try {
			List<Product> products=productService.getProductsByCategoryAndBrand(category, brand);
			if(products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Products not found!", null));
			}
			return ResponseEntity.ok(new ApiResponse("success", products));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@GetMapping("/by/name")
	public ResponseEntity<ApiResponse> getProductsByName(@RequestParam String name){
		try {
			List<Product> products=productService.getProductsByName(name);
			if(products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Products not found!", null));
			}
			return ResponseEntity.ok(new ApiResponse("success", products));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@GetMapping("/by/brand")
	public ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam String brand){
		try {
			List<Product> products=productService.getProductsByBrand(brand);
			if(products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Products not found!", null));
			}
			return ResponseEntity.ok(new ApiResponse("success", products));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@GetMapping("/by/category")
	public ResponseEntity<ApiResponse> getProductsByCategoryName(@RequestParam String category){
		try {
			List<Product> products=productService.getProductsByCategoryName(category);
			if(products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Products not found!", null));
			}
			return ResponseEntity.ok(new ApiResponse("success", products));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest request){
		try {
			Product product=productService.addProduct(request);
			return ResponseEntity.ok(new ApiResponse("Product add successly!", product));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@PutMapping("/by/{id}/update")
	public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest request,@PathVariable Long id){
		try {
			Product product=productService.updateProduct(request, id);
			return ResponseEntity.ok(new ApiResponse("Update success!", product));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	
	@DeleteMapping("/by/{id}/delete")
	public ResponseEntity<ApiResponse> deleteProductById(@PathVariable Long id){
		try {
			productService.deleteProductById(id);
			return ResponseEntity.ok(new ApiResponse("Delete success!",id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/by/count/brand-and-Name")
	public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand,@RequestParam String name){
		try {
			Long productCount=productService.countProductsByBrandAndName(brand, name);
			return ResponseEntity.ok(new ApiResponse("Product count! ", productCount));
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse(e.getMessage(),null));
		}
	}
	
	
}
