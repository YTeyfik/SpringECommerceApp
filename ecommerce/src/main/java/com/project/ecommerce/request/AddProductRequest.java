package com.project.ecommerce.request;

import java.math.BigDecimal;

import com.project.ecommerce.model.Category;

import lombok.Data;

@Data
public class AddProductRequest {
	private Long id;
	private String name;
	private String brand;
	private String description;
	private BigDecimal price;
	private int inventory;
	private Category category;
}
