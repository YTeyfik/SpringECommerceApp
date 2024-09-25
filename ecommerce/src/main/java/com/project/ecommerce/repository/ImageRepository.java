package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.model.Image;

public interface ImageRepository extends JpaRepository<Image,Long>{

}
